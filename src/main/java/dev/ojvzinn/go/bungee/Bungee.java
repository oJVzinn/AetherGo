package dev.ojvzinn.go.bungee;

import dev.ojvzinn.go.bungee.commands.Commands;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

public class Bungee extends Plugin {

    private static Bungee instance;

    public static Bungee getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        instance = this;
        loadConfiguration("config");
    }

    @Override
    public void onEnable() {
        getProxy().registerChannel("AetherGo");

        Commands.setupCommands();

        sendMessage("O plugin iniciou com sucesso!");
    }

    public void loadConfiguration(String... filesName) {
        for (String fileName : filesName) {
            File file = new File("plugins/" + this.getDescription().getName() + "/" + fileName + ".yml");
            if (!file.exists()) {
                File folder = file.getParentFile();
                if (!folder.exists()) {
                    folder.mkdirs();
                }
                try {
                    Files.copy(Objects.requireNonNull(Bungee.getInstance().getClass().getResourceAsStream("/" + fileName + ".yml")), file.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Configuration getConfig(String configName) {
        try {
            return YamlConfiguration.getProvider(YamlConfiguration.class).load(new File("plugins/" + this.getDescription().getName() + "/" + configName + ".yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void onDisable() {
        sendMessage("O plugin desligou com sucesso!");
    }
    public void sendMessage(String message) {
        getLogger().info("§a" + message);
    }

    public void sendMessage(String message, Character color) {
        getLogger().info("§" + color + message);
    }
}
