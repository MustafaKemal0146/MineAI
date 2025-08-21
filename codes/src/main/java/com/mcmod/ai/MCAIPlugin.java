package com.mcmod.ai;

import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.entity.Player;
import java.util.*;

public class MCAIPlugin extends JavaPlugin {
    private static MCAIPlugin instance;
    // Kişiye özel API anahtarı, model ve konuşma geçmişi
    private final Map<UUID, String> apiKeys = new HashMap<>();
    private final Map<UUID, String> models = new HashMap<>();
    private final Map<UUID, List<String>> chatHistory = new HashMap<>();

    @Override
    public void onEnable() {
        instance = this;
        getCommand("api").setExecutor(new ApiCommand(this));
        getCommand("model").setExecutor(new ModelCommand(this));
        getCommand("ai").setExecutor(new AiCommand(this));
    getCommand("aihelp").setExecutor(new AiHelpCommand());
    getLogger().info("MineAI Plugin enabled!");
    }

    public static MCAIPlugin getInstance() {
        return instance;
    }

    public String getApiKey(Player player) {
        return apiKeys.get(player.getUniqueId());
    }

    public void setApiKey(Player player, String apiKey) {
        apiKeys.put(player.getUniqueId(), apiKey);
    }

    public String getModel(Player player) {
        return models.getOrDefault(player.getUniqueId(), "gemini-2.5-pro");
    }

    public void setModel(Player player, String model) {
        models.put(player.getUniqueId(), model);
    }

    public void addChat(Player player, String entry) {
        chatHistory.computeIfAbsent(player.getUniqueId(), k -> new ArrayList<>()).add(entry);
    }

    public List<String> getChatHistory(Player player) {
        return chatHistory.getOrDefault(player.getUniqueId(), Collections.emptyList());
    }

    public void clearChatHistory(Player player) {
        chatHistory.remove(player.getUniqueId());
    }
}
