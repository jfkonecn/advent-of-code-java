package com.advent.of.code.year2023.day08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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

  private static int getToEndZ(AdventMap map, String currentNode) {
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

  public static long lcm_of_array_elements(int[] element_array) {
    long lcm_of_array_elements = 1;
    int divisor = 2;

    while (true) {
      int counter = 0;
      boolean divisible = false;

      for (int i = 0; i < element_array.length; i++) {

        // lcm_of_array_elements (n1, n2, ... 0) = 0.
        // For negative number we convert into
        // positive and calculate lcm_of_array_elements.

        if (element_array[i] == 0) {
          return 0;
        } else if (element_array[i] < 0) {
          element_array[i] = element_array[i] * (-1);
        }
        if (element_array[i] == 1) {
          counter++;
        }

        // Divide element_array by devisor if complete
        // division i.e. without remainder then replace
        // number with quotient; used for find next factor
        if (element_array[i] % divisor == 0) {
          divisible = true;
          element_array[i] = element_array[i] / divisor;
        }
      }

      // If divisor able to completely divide any number
      // from array multiply with lcm_of_array_elements
      // and store into lcm_of_array_elements and continue
      // to same divisor for next factor finding.
      // else increment divisor
      if (divisible) {
        lcm_of_array_elements = lcm_of_array_elements * divisor;
      } else {
        divisor++;
      }

      // Check if all element_array is 1 indicate
      // we found all factors and terminate while loop.
      if (counter == element_array.length) {
        return lcm_of_array_elements;
      }
    }
  }

  public static long Part2(List<String> input) {
    var map = parseMap(input);
    var currentNodes =
        map.nodeDic.keySet().stream().filter(x -> x.endsWith("A")).collect(Collectors.toList());

    var results = new ArrayList<Integer>();

    for (var currentNode : currentNodes) {
      var totalSteps = getToEndZ(map, currentNode);
      results.add(totalSteps);
    }

    var answer = lcm_of_array_elements(results.stream().mapToInt(i -> i).toArray());

    return answer;
  }
}
