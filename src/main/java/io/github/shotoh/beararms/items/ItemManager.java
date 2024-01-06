package io.github.shotoh.beararms.items;

import io.github.shotoh.beararms.BearArms;

import java.util.HashMap;
import java.util.Map;

public class ItemManager {
  private final BearArms plugin;
  private final Map<String, BearArmsItem> items;

  public ItemManager(BearArms plugin) {
    this.plugin = plugin;
    this.items = new HashMap<>();
  }

  public void initialize() {
    addItem(new DesertEagle(plugin));
  }

  public void addItem(BearArmsItem item) {
    items.put(item.getId(), item);
  }

  public BearArmsItem getItem(String id) {
    return items.get(id);
  }

  public BearArms getPlugin() {
    return plugin;
  }

  public Map<String, BearArmsItem> getItems() {
    return items;
  }
}
