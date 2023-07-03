package dev.ojvzinn.go.bungee.commands.collections;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import dev.ojvzinn.go.bungee.Bungee;
import dev.ojvzinn.go.bungee.commands.Commands;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class GoCommand extends Commands {

    public GoCommand() {
        super("go");
    }

    @Override
    public void executeCommand(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(TextComponent.fromLegacyText("§cEste comando é exclusivo apenas para jogadores!"));
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;

        if (args.length < 1) {
            player.sendMessage(TextComponent.fromLegacyText(Bungee.getInstance().getConfig("config").getString("help")));
            return;
        }

        ProxiedPlayer targetPlayer = Bungee.getInstance().getProxy().getPlayer(args[0]);
        if (targetPlayer == null) {
            player.sendMessage(TextComponent.fromLegacyText(Bungee.getInstance().getConfig("config").getString("offPlayer")));
            return;
        }

        if (player.getServer().getInfo() != targetPlayer.getServer().getInfo()) {
            player.connect(targetPlayer.getServer().getInfo());
        }

        new Thread(()-> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ByteArrayDataOutput output = ByteStreams.newDataOutput();
            output.writeUTF("teleport");
            output.writeUTF(player.getName());
            output.writeUTF(targetPlayer.getName());
            targetPlayer.getServer().getInfo().sendData("AetherGo", output.toByteArray());
            player.sendMessage(TextComponent.fromLegacyText(Bungee.getInstance().getConfig("config").getString("teleportSucess").replace("{target}", targetPlayer.getName())));
        }).start();
    }
}
