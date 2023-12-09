package com.advent.of.code.year2023.day08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day08 {
  enum Direction {
    LEFT,
    RIGHT
  }

  private record MapNode(String Left, String Right) {}

  private record AdventMap(List<Direction> directions, HashMap<String, MapNode> nodeDic) {}

  private static AdventMap parseMap(List<String> input) {
    var directions = new ArrayList<Direction>();
    for (var c : input.get(0).toCharArray()) {
      if (c == 'L') {
        directions.add(Direction.LEFT);
      } else if (c == 'R') {
        directions.add(Direction.RIGHT);
      }
    }
    var nodeDic = new HashMap<String, MapNode>() {};

    for (var line : input.subList(2, input.size())) {
      var parts = line.split(" = ");
      var key = parts[0];
      var split = parts[1].replace(")", "").replace("(", "").split(",");
      var left = split[0].trim();
      var right = split[1].trim();
      var node = new MapNode(left, right);
      nodeDic.put(key, node);
    }
    return new AdventMap(directions, nodeDic);
  }

  public static int Part1(List<String> input) {
    var map = parseMap(input);
    var currentNode = "AAA";
    var totalSteps = 0;
    while (!"ZZZ".equals(currentNode)) {
      for (var direction : map.directions) {
        if (direction == Direction.LEFT) {
          currentNode = map.nodeDic.get(currentNode).Left;
        } else {
          currentNode = map.nodeDic.get(currentNode).Right;
        }
        totalSteps++;
      }
    }
    return totalSteps;
  }

  private static int TotalSteps(AdventMap map, String currentNode) {
    var totalSteps = 0;
    while (!currentNode.endsWith("Z")) {
      for (var direction : map.directions) {
        if (direction == Direction.LEFT) {
          currentNode = map.nodeDic.get(currentNode).Left;
        } else {
          currentNode = map.nodeDic.get(currentNode).Right;
        }
        totalSteps++;
      }
    }
    return totalSteps;
  }

  public static int Part2(List<String> input) {
    var map = parseMap(input);
    var nodesEndingWithA = map.nodeDic.keySet().stream().filter(x -> x.endsWith("A")).toList();

    var maxSteps = 0;
    for (var node : nodesEndingWithA) {
      var totalSteps = TotalSteps(map, node);
      if (totalSteps > 0) {
        maxSteps = Math.max(maxSteps, totalSteps);
      }
    }
    return maxSteps;
  }
}
