package nl.robertvankammen.worldbordertimed;

import io.papermc.paper.configuration.type.fallback.FallbackValue;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

import java.util.List;

import static nl.robertvankammen.worldbordertimed.config.ConfigLocation.*;

@DefaultQualifier(NonNull.class)
public final class WorldborderPlugin extends JavaPlugin implements Listener {
  private Integer worldborderMaxSize;
  private Integer worldborderStartSize;
  private Integer worldborderMaxIncrease;
  private List<Integer> worldborderTimeSet;
  @Override
  public void onEnable() {
    this.getServer().getPluginManager().registerEvents(this, this);
    initConfig();
  }

  private void initConfig() {
    if (getConfig().get(CONFIG_VERSION.getLocatie()) == null) {
      getConfig().set(WORLDBORDER_MAX_SIZE.getLocatie(), 2000);
      getConfig().set(WORLDBORDER_START_SIZE.getLocatie(), 100);
      getConfig().set(WORLDBORDER_MAX_INCREASE.getLocatie(), 100);
      getConfig().set(WORLDBORDER_TIME_SETS_IN_MINUTES.getLocatie(), List.of(15, 30, 45, 60));
      saveConfig();
    }
    // todo saftycheck voor null values (als iemand aan de config zit)
    worldborderMaxSize = (Integer) getConfig().get(WORLDBORDER_MAX_SIZE.getLocatie(), Integer.class);
    worldborderStartSize = (Integer) getConfig().get(WORLDBORDER_START_SIZE.getLocatie(), Integer.class);
    worldborderMaxIncrease = (Integer) getConfig().get(WORLDBORDER_MAX_INCREASE.getLocatie(), Integer.class);
    worldborderTimeSet = (List<Integer>) getConfig().get(WORLDBORDER_TIME_SETS_IN_MINUTES.getLocatie(), List.class);
  }

  private void setInitialWorldborder(){

  }
}
