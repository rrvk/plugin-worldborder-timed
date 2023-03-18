package nl.robertvankammen.worldbordertimed.process;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import nl.robertvankammen.worldbordertimed.WorldborderPlugin;
import nl.robertvankammen.worldbordertimed.config.ConfigRecord;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.bukkit.Bukkit.getServer;

public class Timer {
  private final ConfigRecord configRecord;
  private final Plugin plugin;
  private boolean started;

  public Timer(ConfigRecord configRecord, WorldborderPlugin plugin) {
    this.configRecord = configRecord;
    this.plugin = plugin;
  }

  public void startTimer() {
    if (!started) {
      var atLeastOneWorldNotMax = configRecord.worldnames().stream()
        .map(getServer()::getWorld)
        .filter(Objects::nonNull)
        .anyMatch(world -> world.getWorldBorder().getSize() < configRecord.worldborderMaxSize());
      if (atLeastOneWorldNotMax) {
        started = true;
        Collections.shuffle(configRecord.worldborderTimeSet());
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, this::increaseWorldborder, configRecord.worldborderTimeSet().get(0) * 20 * 60);
      }
    }
  }

  public void stopTimer(@NotNull Player player) {
    var onlinePlayers = getServer().getOnlinePlayers();
    if (onlinePlayers.isEmpty() || (onlinePlayers.size() == 1 && onlinePlayers.contains(player))) {
      started = false;
      Bukkit.getScheduler().cancelTasks(plugin);
    }
  }

  private void resetTimer() {
    started = false;
    Bukkit.getScheduler().cancelTasks(plugin);
    startTimer();
  }

  private void increaseWorldborder() {
    var restart = new AtomicBoolean(false);
    configRecord.worldnames().stream()
      .map(getServer()::getWorld)
      .filter(Objects::nonNull)
      .forEach(world -> {
        var size = world.getWorldBorder().getSize();
        if (size + configRecord.worldborderMaxIncrease() >= configRecord.worldborderMaxSize()) {
          // max vergroten
          world.getWorldBorder().setSize(configRecord.worldborderMaxSize(), 10);
        } else {
          world.getWorldBorder().setSize(world.getWorldBorder().getSize() + configRecord.worldborderMaxIncrease(), 10);
          restart.set(true);
        }
      });
    getServer().getOnlinePlayers().forEach(player -> player.showTitle(Title.title(Component.text("World border has increased"), Component.text(""))));
    if (restart.get()) {
      resetTimer();
    }
  }

}
