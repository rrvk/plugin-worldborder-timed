package nl.robertvankammen.worldbordertimed.config;

import java.util.List;

public record ConfigRecord(Integer worldborderMaxSize, Integer worldborderStartSize, Integer worldborderMaxIncrease,
                           List<Integer> worldborderTimeSet, List<String> worldnames) {
}
