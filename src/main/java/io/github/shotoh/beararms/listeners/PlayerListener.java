package io.github.shotoh.beararms.listeners;

import io.github.shotoh.beararms.BearArms;
import io.github.shotoh.beararms.items.BearArmsItem;
import io.github.shotoh.beararms.utils.ItemUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerListener implements Listener {
  private final BearArms plugin;

  public PlayerListener(BearArms plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public void onPlayerInteractEvent(PlayerInteractEvent event) {
    Player player = event.getPlayer();
    BearArmsItem item = ItemUtils.getItem(plugin, event.getItem());
    if (item != null) {
      item.onPlayerInteractEvent(event);
    }
  }
}
