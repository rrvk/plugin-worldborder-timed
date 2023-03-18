package nl.robertvankammen.worldbordertimed.listeners;

import nl.robertvankammen.worldbordertimed.config.ConfigRecord;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.world.WorldLoadEvent;

public class WorldListener implements Listener {
  private final ConfigRecord configRecord;
  public WorldListener(ConfigRecord config) {
    this.configRecord = config;
  }

  @EventHandler
  public void onLoad(WorldLoadEvent event) {
    var world = event.getWorld();
    if (configRecord.worldnames().contains(world.getName())){
      if (world.getWorldBorder().getSize() > configRecord.worldborderMaxSize()){
       world.getWorldBorder().setSize(configRecord.worldborderStartSize());
      }
    }
  }
}
