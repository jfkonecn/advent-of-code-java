package com.advent.of.code.year2023.day03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Day03 {

  private record Point(int x, int y) {}

  private record EngineSchematic(
      Map<Point, Integer> potentialPartNumbers, Map<Point, Character> otherCharacters) {}

  private static EngineSchematic parseEngineSchematic(List<String> input) {
    var potentialPartNumbers = new HashMap<Point, Integer>();
    var otherCharacters = new HashMap<Point, Character>();
    for (int i = 0; i < input.size(); i++) {
      var line = input.get(i);
      var charArray = line.toCharArray();
      for (int j = 0; j < charArray.length; j++) {
        var c = charArray[j];
        if (Character.isDigit(c)) {
          var sb = new StringBuilder();
          var points = new ArrayList<Point>();
          while (Character.isDigit(c)) {
            var point = new Point(j, i);
            points.add(point);
            sb.append(c);
            c = charArray[++j];
          }
          var partNumber = Integer.parseInt(sb.toString());
          for (var point : points) {
            potentialPartNumbers.put(point, partNumber);
          }
          j--;
        } else if (c != '.') {
          var point = new Point(j, i);
          otherCharacters.put(point, c);
        }
      }
    }
    return new EngineSchematic(potentialPartNumbers, otherCharacters);
  }

  public static int Part1(List<String> input) {
    var engineSchematic = parseEngineSchematic(input);
    var validPartNumbers = new HashSet<Integer>();
    var otherCharacters = engineSchematic.otherCharacters;
    for (var entry : engineSchematic.potentialPartNumbers.entrySet()) {
      var point = entry.getKey();
      var partNumber = entry.getValue();

      if (otherCharacters.containsKey(new Point(point.x + 1, point.y))
          || otherCharacters.containsKey(new Point(point.x - 1, point.y))
          || otherCharacters.containsKey(new Point(point.x, point.y + 1))
          || otherCharacters.containsKey(new Point(point.x, point.y - 1))
          || otherCharacters.containsKey(new Point(point.x - 1, point.y - 1))
          || otherCharacters.containsKey(new Point(point.x + 1, point.y - 1))
          || otherCharacters.containsKey(new Point(point.x - 1, point.y + 1))
          || otherCharacters.containsKey(new Point(point.x + 1, point.y + 1))) {

        validPartNumbers.add(partNumber);
      }
    }
    return validPartNumbers.stream().mapToInt(Integer::intValue).sum();
  }

  public static int Part2(List<String> input) {
    return 0;
  }
}
