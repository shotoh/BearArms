package io.github.shotoh.beararms.items;

import io.github.shotoh.beararms.BearArms;
import io.github.shotoh.beararms.core.BearArmsKeys;
import io.github.shotoh.beararms.utils.ItemUtils;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public abstract class BearArmsItem {
  protected final BearArms plugin;
  protected final String id;
  protected String name;
  protected String[] lore;
  protected Material material;

  protected BearArmsItem(BearArms plugin, String id, String name, String[] lore, Material material) {
    this.plugin = plugin;
    this.id = id;
    this.name = name;
    this.lore = lore;
    this.material = material;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String[] getLore() {
    return lore;
  }

  public void setLore(String[] lore) {
    this.lore = lore;
  }

  public Material getMaterial() {
    return material;
  }

  public void setMaterial(Material material) {
    this.material = material;
  }

  public ItemStack create(int amount) {
    MiniMessage mm = MiniMessage.miniMessage();
    BearArmsKeys keys = plugin.getKeys();

    ItemStack is = new ItemStack(material, amount);
    ItemMeta im = is.getItemMeta();
    PersistentDataContainer con = im.getPersistentDataContainer();

    con.set(keys.getIdKey(), PersistentDataType.STRING, id);

    im.displayName(mm.deserialize("<!i><aqua>" + name));
    if (lore != null) {
      im.lore(ItemUtils.stringToComponent(List.of(lore)));
    }

    im.setUnbreakable(true);
    im.addItemFlags(
      ItemFlag.HIDE_UNBREAKABLE,
      ItemFlag.HIDE_DESTROYS,
      ItemFlag.HIDE_DYE,
      ItemFlag.HIDE_PLACED_ON,
      ItemFlag.HIDE_ITEM_SPECIFICS
    );

    for (Attribute attribute : Attribute.values()) {
      im.removeAttributeModifier(attribute);
    }

    is.setItemMeta(im);
    return is;
  }

  public boolean onPlayerInteractEvent(PlayerInteractEvent event) {
    return true;
  }
}
