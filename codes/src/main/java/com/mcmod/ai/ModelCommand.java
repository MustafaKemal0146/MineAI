package com.mcmod.ai;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public class ModelCommand implements CommandExecutor {
    private final MCAIPlugin plugin;
    // Metin destekli Gemini modelleri
    private static final Map<String, String> MODELS = new LinkedHashMap<>();
    static {
        MODELS.put("gemini-2.5-pro", "Gemini 2.5 Pro (Gelişmiş düşünme, metin)");
        MODELS.put("gemini-2.5-flash", "Gemini 2.5 Flash (Uyarlanabilir, metin)");
        MODELS.put("gemini-2.5-flash-lite", "Gemini 2.5 Flash-Lite (Hızlı, metin)");
        MODELS.put("gemini-live-2.5-flash-preview", "Gemini 2.5 Flash Live (Düşük gecikme, metin)");
        MODELS.put("gemini-2.0-flash", "Gemini 2.0 Flash (Yeni nesil, metin)");
        MODELS.put("gemini-2.0-flash-lite", "Gemini 2.0 Flash-Lite (Düşük gecikme, metin)");
    }

    public ModelCommand(MCAIPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Bu komut sadece oyuncular tarafından kullanılabilir.");
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage("Kullanılabilir modeller:");
            for (Map.Entry<String, String> entry : MODELS.entrySet()) {
                sender.sendMessage("- " + entry.getKey() + " : " + entry.getValue());
            }
            sender.sendMessage("Bir model seçmek için: /model <model_ismi>");
            return true;
        }
        String model = args[0];
        if (!MODELS.containsKey(model)) {
            sender.sendMessage("Geçersiz model ismi! /model yazarak mevcut modelleri görebilirsin.");
            return true;
        }
    Player player = (Player) sender;
    plugin.setModel(player, model);
    sender.sendMessage("Model başarıyla ayarlandı: " + model + " (" + MODELS.get(model) + ")");
    return true;
    }
}
