package com.advent.of.code.year2020.day03;

import java.util.List;
import java.util.stream.Collectors;

public class Day03 {
  // parse as multi-datmensional array
  private static char[][] parseInput(List<String> input) {
    char[][] map = new char[input.size()][input.get(0).length()];
    for (int i = 0; i < input.size(); i++) {
      map[i] = input.get(i).toCharArray();
    }
    return map;
  }

  private static int countTrees(char[][] map, int right, int down) {
    int trees = 0;
    int x = 0;
    int y = 0;
    while (y < map.length) {
      if (map[y][x] == '#') {
        trees++;
      }
      x = (x + right) % map[0].length;
      y += down;
    }
    return trees;
  }

  public static int Part1(List<String> input) {
    char[][] map = parseInput(input);
    return countTrees(map, 3, 1);
  }

  public static int Part2(List<String> input) {
    char[][] map = parseInput(input);
    return List
        .of(countTrees(map, 1, 1), countTrees(map, 3, 1), countTrees(map, 5, 1),
            countTrees(map, 7, 1), countTrees(map, 1, 2))
        .stream()
        .reduce(1, (a, b) -> a * b);
  }
}
