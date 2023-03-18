package nl.robertvankammen.worldbordertimed;

import nl.robertvankammen.worldbordertimed.config.ConfigRecord;
import nl.robertvankammen.worldbordertimed.listeners.PlayerListener;
import nl.robertvankammen.worldbordertimed.listeners.WorldListener;
import nl.robertvankammen.worldbordertimed.process.Timer;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

import java.util.List;

import static nl.robertvankammen.worldbordertimed.config.ConfigLocationEnum.*;

@DefaultQualifier(NonNull.class)
public final class WorldborderPlugin extends JavaPlugin implements Listener {
  private Timer timer;

  @Override
  public void onEnable() {
    var config = initConfig();
    this.timer = new Timer(config, this);
    this.getServer().getPluginManager().registerEvents(new WorldListener(config), this);
    this.getServer().getPluginManager().registerEvents(new PlayerListener(timer), this);
  }

  private ConfigRecord initConfig() {
    if (getConfig().get(CONFIG_VERSION.getLocatie()) == null) {
      getConfig().set(WORLDBORDER_MAX_SIZE.getLocatie(), 2000);
      getConfig().set(WORLDBORDER_START_SIZE.getLocatie(), 100);
      getConfig().set(WORLDBORDER_MAX_INCREASE.getLocatie(), 50);
      getConfig().set(WORLDBORDER_TIME_SETS_IN_MINUTES.getLocatie(), List.of(15, 30, 45, 60));
      getConfig().set(WORLDBORDER_WORLDS.getLocatie(), List.of("world"));
      getConfig().set(CONFIG_VERSION.getLocatie(), 1);
      saveConfig();
    }
    // todo add safty checks if somebody messed up the config
    var worldborderMaxSize = (Integer) getConfig().get(WORLDBORDER_MAX_SIZE.getLocatie(), Integer.class);
    var worldborderStartSize = (Integer) getConfig().get(WORLDBORDER_START_SIZE.getLocatie(), Integer.class);
    var worldborderMaxIncrease = (Integer) getConfig().get(WORLDBORDER_MAX_INCREASE.getLocatie(), Integer.class);
    var worldborderTimeSet = (List<Integer>) getConfig().get(WORLDBORDER_TIME_SETS_IN_MINUTES.getLocatie(), List.class);
    var worldborderWorlds = (List<String>) getConfig().get(WORLDBORDER_WORLDS.getLocatie(), List.class);
    return new ConfigRecord(worldborderMaxSize, worldborderStartSize, worldborderMaxIncrease, worldborderTimeSet, worldborderWorlds);
  }
}
