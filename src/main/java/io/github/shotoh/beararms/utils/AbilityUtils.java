package io.github.shotoh.beararms.utils;

import java.util.Map;
import java.util.UUID;

public class AbilityUtils {
  public static boolean checkCooldown(Map<UUID, Long> cooldowns, UUID uuid, double abilityCooldown) {
    Long cooldown = cooldowns.get(uuid);
    if (cooldown == null || cooldown < System.currentTimeMillis()) {
      setCooldown(cooldowns, uuid, abilityCooldown);
      return true;
    }
    return false;
  }

  public static long getCooldown(Map<UUID, Long> cooldowns, UUID uuid) {
    return cooldowns.get(uuid);
  }

  public static void setCooldown(Map<UUID, Long> cooldowns, UUID uuid, double abilityCooldown) {
    cooldowns.put(uuid, (long) (System.currentTimeMillis() + (abilityCooldown * 1000L)));
  }

  public static void resetCooldown(Map<UUID, Long> cooldowns, UUID uuid) {
    setCooldown(cooldowns, uuid, 0);
  }
}
