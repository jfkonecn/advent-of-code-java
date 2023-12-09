package com.advent.of.code.year2023.day06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day06 {
  private record Race(long time, long distance) {}

  private static List<Race> parseRaces1(List<String> input) {
    var times =
        Arrays.stream(input.get(0).replace("Time:", "").trim().replaceAll("\\s+", " ").split(" "))
            .mapToLong(Long::parseLong)
            .toArray();
    var distances =
        Arrays.stream(
                input.get(1).replace("Distance:", "").trim().replaceAll("\\s+", " ").split(" "))
            .mapToLong(Long::parseLong)
            .toArray();
    var races = new ArrayList<Race>();
    for (int i = 0; i < times.length; i++) {
      var race = new Race(times[i], distances[i]);
      races.add(race);
    }
    return races;
  }

  private static List<Race> parseRaces2(List<String> input) {
    var time = Integer.parseInt(input.get(0).replace("Time:", "").trim().replaceAll("\\s+", ""));
    var distance =
        Integer.parseInt(input.get(1).replace("Distance:", "").trim().replaceAll("\\s+", ""));
    var races = new ArrayList<Race>();
    races.add(new Race(time, distance));
    return races;
  }

  private static Boolean isNotRecord(Race race, long chargeTime) {
    var time = race.time;
    var distance = time * chargeTime - chargeTime * chargeTime;
    return race.distance >= distance;
  }

  private static long waysToWin(List<Race> races) {
    var wayToWin = new ArrayList<Long>();
    for (var race : races) {
      var time = race.time;
      var distance = race.distance;
      var lowerBound = (long) Math.ceil((time - Math.sqrt(time * time - 4 * distance)) / 2);
      var higherBound = (long) Math.floor((time + Math.sqrt(time * time - 4 * distance)) / 2);
      if (isNotRecord(race, lowerBound)) {
        lowerBound++;
      }
      if (isNotRecord(race, higherBound)) {
        higherBound--;
      }

      var temp = higherBound - lowerBound + 1;
      wayToWin.add(temp);
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
