package dev.ojvzinn.go.bungee.commands;

import dev.ojvzinn.go.bungee.Bungee;
import dev.ojvzinn.go.bungee.commands.collections.GoCommand;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public abstract class Commands extends Command {

    public static void setupCommands() {
        new GoCommand();
    }

    public Commands(String name) {
        super(name);
        Bungee.getInstance().getProxy().getPluginManager().registerCommand(Bungee.getInstance(), this);
    }

    public abstract void executeCommand(CommandSender sender, String[] args);

    @Override
    public void execute(CommandSender sender, String[] args) {
        this.executeCommand(sender, args);
    }
}
