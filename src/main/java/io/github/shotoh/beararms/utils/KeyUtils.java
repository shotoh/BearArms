package io.github.shotoh.beararms.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class KeyUtils {
  public static String getStringPDC(ItemStack is, NamespacedKey key) {
    if (is != null && is.getItemMeta() != null) {
      PersistentDataContainer con = is.getItemMeta().getPersistentDataContainer();
      if (con.has(key, PersistentDataType.STRING)) {
        return con.get(key, PersistentDataType.STRING);
      }
    }
    return null;
  }
}
