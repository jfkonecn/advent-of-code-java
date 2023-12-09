package com.advent.of.code.year2023.day06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day06 {
  private record Race(int time, int distance) {}

  private static List<Race> parseRaces1(List<String> input) {
    var times =
        Arrays.stream(input.get(0).replace("Time:", "").trim().replaceAll("\\s+", " ").split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
    var distances =
        Arrays.stream(
                input.get(1).replace("Distance:", "").trim().replaceAll("\\s+", " ").split(" "))
            .mapToInt(Integer::parseInt)
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

  public static int waysToWin(List<Race> races) {
    var wayToWin = new ArrayList<Integer>();
    for (var race : races) {
      var temp = 0;
      for (int chargeTime = 0; chargeTime < race.time; chargeTime++) {
        var distance = (chargeTime * race.time) - (chargeTime * chargeTime);
        if (distance > race.distance) {
          temp++;
        }
      }
      wayToWin.add(temp);
    }
    return wayToWin.stream().reduce(1, (a, b) -> a * b);
  }

  public static int Part1(List<String> input) {
    var races = parseRaces1(input);
    return waysToWin(races);
  }

  public static int Part2(List<String> input) {
    var races = parseRaces2(input);
    return waysToWin(races);
  }
}
