package com.mcmod.ai;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AiHelpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    sender.sendMessage("§eMineAI Yardım:");
        sender.sendMessage("§a/api <anahtar>§f : Google AI Studio API anahtarını gir.");
        sender.sendMessage("§a/model <model_ismi>§f : Kullanılacak modeli seç. /model ile listeyi görebilirsin.");
        sender.sendMessage("§a/ai <mesaj>§f : Yapay zeka ile konuş.");
        sender.sendMessage("§a/aihelp§f : Bu yardım mesajını gösterir.");
        sender.sendMessage("");
        sender.sendMessage("Önce /api ile anahtar gir, sonra /model ile model seç, ardından /ai ile konuşmaya başla!");
        return true;
    }
}
