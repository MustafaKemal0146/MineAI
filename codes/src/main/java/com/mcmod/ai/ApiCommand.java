package com.mcmod.ai;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ApiCommand implements CommandExecutor {
    private final MCAIPlugin plugin;

    public ApiCommand(MCAIPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Bu komut sadece oyuncular tarafından kullanılabilir.");
            return true;
        }
        if (args.length != 1) {
            sender.sendMessage("Kullanım: /api <anahtar>");
            return true;
        }
        Player player = (Player) sender;
        String apiKey = args[0];
        plugin.setApiKey(player, apiKey);
        sender.sendMessage("API anahtarı kaydedildi, bağlantı test ediliyor...");
        // Bağlantı testini ayrı thread'de yap
        new Thread(() -> {
            String testResult = testApiKey(apiKey, plugin);
            sender.sendMessage(testResult);
            plugin.getLogger().info("[MineAI] " + player.getName() + " için API anahtarı testi sonucu: " + testResult);
        }).start();
        return true;

    }

    // API anahtarı ile bağlantı testi
    private String testApiKey(String apiKey, MCAIPlugin plugin) {
        String testModel = "gemini-2.0-flash";
        String testMessage = "ping";
        String response = GoogleAIClient.sendMessage(apiKey, testModel, testMessage);
        if (response.contains("candidates") && !response.contains("Hata")) {
            return "§a[MineAI] API bağlantısı başarılı!";
        } else if (response.contains("API key not valid") || response.contains("PERMISSION_DENIED")) {
            return "§c[MineAI] API anahtarı geçersiz veya yetkisiz!";
        } else {
            return "§c[MineAI] API bağlantısı kurulamadı!";
        }
    }
}
