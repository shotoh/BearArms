package io.github.shotoh.beararms.utils;

import io.github.shotoh.beararms.BearArms;
import io.github.shotoh.beararms.items.BearArmsItem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

public class ItemUtils {
  public static ItemStack createItem(BearArms plugin, String id) {
    return createItem(plugin, id, 1);
  }

  public static ItemStack createItem(BearArms plugin, String id, int amount) {
    BearArmsItem bearArmsItem = plugin.getItemManager().getItem(id);
    if (bearArmsItem == null) return null;
    return bearArmsItem.create(amount);
  }

  public static List<Component> stringToComponent(List<String> list) {
    return list.stream().map(s -> MiniMessage.miniMessage().deserialize("<!i><gray>" + s)).toList();
  }

  public static void addItem(Player player, ItemStack is) {
    Map<Integer, ItemStack> failed = player.getInventory().addItem(is);
    if (!failed.isEmpty()) {
      for (ItemStack failedItem : failed.values()) {
        player.getWorld().dropItem(player.getLocation(), failedItem);
      }
    }
  }

  public static BearArmsItem getItem(BearArms plugin, ItemStack is) {
    String id = KeyUtils.getStringPDC(is, plugin.getKeys().getIdKey());
    return plugin.getItemManager().getItem(id);
  }
}
