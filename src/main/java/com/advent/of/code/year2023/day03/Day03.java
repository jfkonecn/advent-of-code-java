package com.advent.of.code.year2023.day03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day03 {

  private record Point(int x, int y) {}

  private record PotentialPartNumber(List<Point> points, int partNumber) {}

  private record EngineSchematic(
      List<PotentialPartNumber> potentialPartNumbers, Map<Point, Character> otherCharacters) {}

  private static EngineSchematic parseEngineSchematic(List<String> input) {
    var potentialPartNumbers = new ArrayList<PotentialPartNumber>();
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
            j++;
            if (j < charArray.length) {
              c = charArray[j];
            } else {
              break;
            }
          }
          var partNumber = Integer.parseInt(sb.toString());
          potentialPartNumbers.add(new PotentialPartNumber(points, partNumber));
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
    var validPartNumbers = new ArrayList<Integer>();
    var otherCharacters = engineSchematic.otherCharacters;
    for (var entry : engineSchematic.potentialPartNumbers) {
      var partNumber = entry.partNumber;

      for (var point : entry.points) {
        if (otherCharacters.containsKey(new Point(point.x + 1, point.y))
            || otherCharacters.containsKey(new Point(point.x - 1, point.y))
            || otherCharacters.containsKey(new Point(point.x, point.y + 1))
            || otherCharacters.containsKey(new Point(point.x, point.y - 1))
            || otherCharacters.containsKey(new Point(point.x - 1, point.y - 1))
            || otherCharacters.containsKey(new Point(point.x + 1, point.y - 1))
            || otherCharacters.containsKey(new Point(point.x - 1, point.y + 1))
            || otherCharacters.containsKey(new Point(point.x + 1, point.y + 1))) {

          validPartNumbers.add(partNumber);
          break;
        }
      }
    }
    return validPartNumbers.stream().mapToInt(Integer::intValue).sum();
  }

  public static int Part2(List<String> input) {
    return 0;
  }
}
