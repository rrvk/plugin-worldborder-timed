package nl.robertvankammen.worldbordertimed.config;

public enum ConfigLocation {
  CONFIG_VERSION("config.version", "Config version"),
  WORLDBORDER_MAX_SIZE("worldborder.max.size", "The maximum the world border can be"),
  WORLDBORDER_START_SIZE("worldborder.start.size", "The start size of the world border"),
  WORLDBORDER_MAX_INCREASE("worldborder.max.step.size", "How much the worldborder can be increased by for each step"),
  WORLDBORDER_TIME_SETS_IN_MINUTES("worldborder.time.steps.minute", "The (random) time steps in what the world border will be increased");

  private final String locatie;
  private final String omschrijving;

  ConfigLocation(String locatie, String omschrijving) {
    this.locatie = locatie;
    this.omschrijving = omschrijving;
  }

  public String getLocatie() {
    return locatie;
  }
}
