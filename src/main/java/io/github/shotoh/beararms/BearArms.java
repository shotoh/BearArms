package io.github.shotoh.beararms;

import cloud.commandframework.CommandManager;
import cloud.commandframework.bukkit.BukkitCommandManager;
import cloud.commandframework.execution.CommandExecutionCoordinator;
import cloud.commandframework.paper.PaperCommandManager;
import io.github.shotoh.beararms.core.BearArmsKeys;
import io.github.shotoh.beararms.core.commands.BearArmsCommand;
import io.github.shotoh.beararms.items.ItemManager;
import io.github.shotoh.beararms.listeners.PlayerListener;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Function;

public class BearArms extends JavaPlugin {
  private final BearArmsKeys keys = new BearArmsKeys(this);
  private final ItemManager itemManager = new ItemManager(this);
  private BukkitCommandManager<CommandSender> commandManager;

  @Override
  public void onEnable() {
      try {
          this.commandManager = new PaperCommandManager<>(
            this,
            CommandExecutionCoordinator.simpleCoordinator(),
            Function.identity(),
            Function.identity()
          );
      } catch (Exception e) {
          this.getLogger().severe("Failed to initialize the command manager!");
          this.getServer().getPluginManager().disablePlugin(this);
          return;
      }

      itemManager.initialize();
      new BearArmsCommand(this).registerCommands();
      this.getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
  }

  public BearArmsKeys getKeys() {
    return keys;
  }

  public ItemManager getItemManager() {
    return itemManager;
  }

  public BukkitCommandManager<CommandSender> getCommandManager() {
    return commandManager;
  }

  public void setCommandManager(BukkitCommandManager<CommandSender> commandManager) {
    this.commandManager = commandManager;
  }
}
