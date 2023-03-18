package nl.robertvankammen.worldbordertimed.listeners;

import nl.robertvankammen.worldbordertimed.config.ConfigRecord;
import nl.robertvankammen.worldbordertimed.process.Timer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static org.bukkit.Bukkit.getServer;

public class PlayerListener implements Listener {
  private final Timer timer;
  public PlayerListener(Timer timer) {
    this.timer = timer;
  }

  @EventHandler
  public void onJoin(PlayerJoinEvent event) {
    // if one player has joined a random timer will start for border increase
    timer.startTimer();
  }
  @EventHandler
  public void onLeave(PlayerQuitEvent event) {
    timer.stopTimer(event.getPlayer());
  }
}
