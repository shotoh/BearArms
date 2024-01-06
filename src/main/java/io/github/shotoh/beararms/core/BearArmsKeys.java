package io.github.shotoh.beararms.core;

import io.github.shotoh.beararms.BearArms;
import org.bukkit.NamespacedKey;

public class BearArmsKeys {
  private final BearArms plugin;
  private final NamespacedKey idKey;

  public BearArmsKeys(BearArms plugin) {
    this.plugin = plugin;
    this.idKey = new NamespacedKey(plugin, "id");
  }

  public BearArms getPlugin() {
    return plugin;
  }

  public NamespacedKey getIdKey() {
    return idKey;
  }
}
