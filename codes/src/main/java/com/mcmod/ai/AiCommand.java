package com.mcmod.ai;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.json.JSONObject;
import org.json.JSONArray;

public class AiCommand implements CommandExecutor {
    private final MCAIPlugin plugin;

    public AiCommand(MCAIPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Bu komut sadece oyuncular tarafından kullanılabilir.");
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage("Kullanım: /ai <mesaj>");
            return true;
        }
        Player player = (Player) sender;
        String apiKey = plugin.getApiKey(player);
        String model = plugin.getModel(player);
        if (apiKey == null) {
            sender.sendMessage("Önce /api ile API anahtarını giriniz.");
            return true;
        }
        String message = String.join(" ", args);
        sender.sendMessage("[AI] Yanıt bekleniyor...");
        new Thread(() -> {
            String response = GoogleAIClient.sendMessage(apiKey, model, message);
            String aiText = parseGeminiText(response);
            plugin.addChat(player, "[SEN] " + message);
            plugin.addChat(player, "[AI] " + aiText);
            String separator = "§8-----"; // Minecraft renk kodu ile koyu gri çizgi
            // Oyuncuya gönder
            sender.sendMessage(separator);
            if (aiText.startsWith("Hata:")) {
                sender.sendMessage("[AI] Hata: " + aiText);
            } else {
                sender.sendMessage("Yapay Zeka:\n" + aiText);
            }
            sender.sendMessage(separator);
            // Sunucu terminaline de yaz
            plugin.getLogger().info("-----");
            plugin.getLogger().info("AI cevabı: " + aiText);
            plugin.getLogger().info("-----");
        }).start();
        return true;

    }

    // Gemini API cevabından sadece metni çeker
    private String parseGeminiText(String json) {
        try {
            JSONObject obj = new JSONObject(json);
            JSONArray candidates = obj.getJSONArray("candidates");
            if (candidates.length() > 0) {
                JSONObject first = candidates.getJSONObject(0);
                JSONObject content = first.getJSONObject("content");
                JSONArray parts = content.getJSONArray("parts");
                if (parts.length() > 0) {
                    return parts.getJSONObject(0).getString("text");
                }
            }
        } catch (Exception e) {
            return "[AI cevabı ayrıştırılamadı]";
        }
        return "[AI cevabı yok]";
    }
}
