package com.advent.of.code.year2023.day03;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day03 {

  private record Point(int x, int y) {}

  private record EngineSchematic(
      Map<Point, Integer> potentialPartNumbers, Map<Point, Character> otherCharacters) {}

  private static EngineSchematic parseEngineSchematic(List<String> input) {
    var potentialPartNumbers = new HashMap<Point, Integer>();
    var otherCharacters = new HashMap<Point, Character>();
    for (var line : input) {
      var charArray = line.toCharArray();
      for (int i = 0; i < charArray.length; i++) {
        var c = charArray[i];
        if (Character.isDigit(c)) {
          var partNumber = Integer.parseInt(String.valueOf(c));
          var point = new Point(i, input.indexOf(line));
          potentialPartNumbers.put(point, partNumber);
        } else if (c != '.') {
          var point = new Point(i, input.indexOf(line));
          otherCharacters.put(point, c);
        }
      }
    }
    return new EngineSchematic(potentialPartNumbers, otherCharacters);
  }

  public static int Part1(List<String> input) {
    var engineSchematic = parseEngineSchematic(input);
    return 0;
  }

  public static int Part2(List<String> input) {
    return 0;
  }
}
