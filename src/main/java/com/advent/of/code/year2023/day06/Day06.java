package com.advent.of.code.year2023.day06;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day06 {
  private record Race(BigDecimal time, BigDecimal distance) {}

  private static List<Race> parseRaces1(List<String> input) {
    var times =
        Arrays.stream(input.get(0).replace("Time:", "").trim().replaceAll("\\s+", " ").split(" "))
            .map(x -> new BigDecimal(x))
            .collect(Collectors.toList());
    var distances =
        Arrays.stream(
                input.get(1).replace("Distance:", "").trim().replaceAll("\\s+", " ").split(" "))
            .map(x -> new BigDecimal(x))
            .collect(Collectors.toList());
    var races = new ArrayList<Race>();
    for (int i = 0; i < times.size(); i++) {
      var race = new Race(times.get(i), distances.get(i));
      races.add(race);
    }
    return races;
  }

  private static List<Race> parseRaces2(List<String> input) {
    var time = new BigDecimal(input.get(0).replace("Time:", "").trim().replaceAll("\\s+", ""));
    var distance =
        new BigDecimal(input.get(1).replace("Distance:", "").trim().replaceAll("\\s+", ""));
    var races = new ArrayList<Race>();
    races.add(new Race(time, distance));
    return races;
  }

  private static Boolean isNotRecord(Race race, BigDecimal chargeTime) {
    var time = race.time;
    var distance = time.multiply(chargeTime).subtract(chargeTime.multiply(chargeTime));
    return race.distance.compareTo(distance) >= 0;
  }

  private static long waysToWin(List<Race> races) {
    var wayToWin = new ArrayList<Long>();
    for (var race : races) {
      var time = race.time;
      var distance = race.distance;
      var lowerBound =
          time.subtract(
                  time.multiply(time)
                      .subtract(BigDecimal.valueOf(4).multiply(distance))
                      .sqrt(new MathContext(100)))
              .divide(BigDecimal.valueOf(2))
              .setScale(0, RoundingMode.UP);
      var higherBound =
          time.add(
                  time.multiply(time)
                      .subtract(BigDecimal.valueOf(4).multiply(distance))
                      .sqrt(new MathContext(100)))
              .divide(BigDecimal.valueOf(2))
              .setScale(0, RoundingMode.DOWN);
      if (isNotRecord(race, lowerBound)) {
        lowerBound = lowerBound.add(BigDecimal.ONE);
      }
      if (isNotRecord(race, higherBound)) {
        higherBound = higherBound.subtract(BigDecimal.ONE);
      }

      var temp = higherBound.subtract(lowerBound).add(BigDecimal.ONE);
      wayToWin.add(temp.toBigInteger().longValue());
    }
    return wayToWin.stream().reduce(1L, (a, b) -> a * b);
  }

  public static long Part1(List<String> input) {
    var races = parseRaces1(input);
    return waysToWin(races);
  }

  public static long Part2(List<String> input) {
    var races = parseRaces2(input);
    return waysToWin(races);
  }
}
