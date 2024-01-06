package io.github.shotoh.beararms.items;

import com.destroystokyo.paper.ParticleBuilder;
import io.github.shotoh.beararms.BearArms;
import io.github.shotoh.beararms.utils.AbilityUtils;
import io.github.shotoh.beararms.utils.Utils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class DesertEagle extends BearArmsItem {
  private final Map<UUID, Integer> ammo;
  private final Map<UUID, Long> reloadCooldowns;
  private final Map<UUID, Long> shootCooldowns;

  public DesertEagle(BearArms plugin) {
    super(plugin, "desert_eagle", "Desert Eagle", new String[] {
      "Damage: <red>+50",
      "Capacity: <red>+7",
      "",
      "Not from around here",
      "",
      "<aqua><b>UNIQUE"
    }, Material.IRON_HORSE_ARMOR);
    this.ammo = new HashMap<>();
    this.reloadCooldowns = new HashMap<>();
    this.shootCooldowns = new HashMap<>();
  }

  @Override
  public boolean onPlayerInteractEvent(PlayerInteractEvent event) {
    if (super.onPlayerInteractEvent(event)) {
      Player player = event.getPlayer();
      if (event.getAction().isLeftClick()) {
        if (!player.getInventory().contains(Material.FLINT)) {
          Utils.sendMessage(player, "<red>You do not have flint in your inventory!");
          return false;
        } else if (AbilityUtils.checkCooldown(reloadCooldowns, player.getUniqueId(), 3)) {
          reload(player);
        }
      } else if (event.getAction().isRightClick()) {
        if (ammo.getOrDefault(player.getUniqueId(), 0) == 0) {
          Utils.playSound(player.getLocation(), "block.lever.click", 1f, 2f);
          return false;
        } else if (AbilityUtils.checkCooldown(shootCooldowns, player.getUniqueId(), 0.5)) {
          shoot(player);
        }
      }
    }
    return false;
  }

  public void reload(Player player) {
    player.getInventory().removeItem(new ItemStack(Material.FLINT, 1));
    new BukkitRunnable() {
      int ticks = 0;
      @Override
      public void run() {
        if (ticks == 0) Utils.playSound(player.getLocation(), "block.lever.click", 1f, 1f);
        if (ticks == 40) {
          Utils.playSound(player.getLocation(), "block.lever.click", 1f, 1.25f);
          ammo.put(player.getUniqueId(), 7);
          this.cancel();
          return;
        }
        ticks++;
      }
    }.runTaskTimer(plugin, 0, 1);
  }

  public void shoot(Player player) {
    ammo.put(player.getUniqueId(), ammo.get(player.getUniqueId()) - 1);
    Utils.playSound(player.getLocation(), "entity.firework_rocket.blast", 1f, 0.5f);
    Location loc = player.getEyeLocation();
    Vector dir = loc.getDirection();
    for (int i = 1; i < 25; i++) {
      Location newLoc = loc.clone().add(dir.clone().multiply(i));
      new ParticleBuilder(Particle.FIREWORKS_SPARK).extra(0).count(10).location(newLoc).receivers(25).spawn();
      List<LivingEntity> nearby = newLoc.getNearbyLivingEntities(1).stream().filter(le -> !(le instanceof Player)).toList();
      if (!nearby.isEmpty()) {
        nearby.forEach(le -> le.damage(50, player));
        break;
      }
      if (newLoc.getBlock().isSolid()) break;
    }
  }
}
