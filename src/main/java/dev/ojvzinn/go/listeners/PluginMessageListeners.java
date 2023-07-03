package dev.ojvzinn.go.listeners;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class PluginMessageListeners implements PluginMessageListener {

    @Override @SuppressWarnings("all")
    public void onPluginMessageReceived(String channel, Player sender, byte[] bytes) {
        if (channel.equalsIgnoreCase("AetherGo")) {
            ByteArrayDataInput output = ByteStreams.newDataInput(bytes);
            String subChannel = output.readUTF();
            switch (subChannel) {
                case "teleport": {
                    String playerName = output.readUTF();
                    String targetName = output.readUTF();
                    Player player = Bukkit.getPlayer(playerName);
                    Player targetPlayer = Bukkit.getPlayer(targetName);
                    player.teleport(targetPlayer);
                    break;
                }
            }
        }
    }

}
