package io.github.shotoh.beararms.core.commands;

import cloud.commandframework.ArgumentDescription;
import cloud.commandframework.CommandManager;
import cloud.commandframework.bukkit.BukkitCommandManager;
import cloud.commandframework.bukkit.parsers.PlayerArgument;
import io.github.shotoh.beararms.BearArms;
import io.github.shotoh.beararms.utils.ItemUtils;
import io.github.shotoh.beararms.utils.Utils;
import org.bukkit.command.CommandSender;

public class BearArmsCommand {
  private final BearArms plugin;

  public BearArmsCommand(BearArms plugin) {
    this.plugin = plugin;
  }

  public void registerCommands() {
    BukkitCommandManager<CommandSender> manager = plugin.getCommandManager();
    manager.command(
      manager.commandBuilder("beararms", ArgumentDescription.of("Main command for the BearArms plugin"), "ba")
        .literal("item")
        .argument(PlayerArgument.of("player"))
        .literal("desert_eagle")
        .handler(context -> {
          CommandSender sender = context.getSender();
          if (!sender.isOp()) {
            Utils.sendMessage(sender, "<red>You do not have permission to use this command!");
            return;
          }
          ItemUtils.addItem(context.get("player"), ItemUtils.createItem(plugin, "desert_eagle"));
        })
    );
  }
}
