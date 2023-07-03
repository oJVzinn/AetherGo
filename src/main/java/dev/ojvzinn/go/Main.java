package dev.ojvzinn.go;

import dev.ojvzinn.go.listeners.PluginMessageListeners;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        Bukkit.getMessenger().registerIncomingPluginChannel(this, "AetherGo", new PluginMessageListeners());

        sendMessage("O plugin inicou com sucesso!");
    }

    @Override
    public void onDisable() {
        sendMessage("O plugin desligou com sucesso!");
    }

    public void sendMessage(String message) {
        Bukkit.getConsoleSender().sendMessage("§a[" + this.getDescription().getName() +"] " + message);
    }

    public void sendMessage(String message, Character color) {
        Bukkit.getConsoleSender().sendMessage("§" + color + "[" + this.getDescription().getName() + "] " + message);
    }

}
