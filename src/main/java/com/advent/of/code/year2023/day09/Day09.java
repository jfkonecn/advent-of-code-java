package com.advent.of.code.year2023.day09;

import java.util.ArrayList;
import java.util.List;

public class Day09 {
  private static List<List<Integer>> parseInput(List<String> input) {
    var result = new ArrayList<List<Integer>>();
    for (String line : input) {
      String[] parts = line.split(" ");
      var row = new ArrayList<Integer>();
      for (String part : parts) {
        row.add(Integer.parseInt(part));
      }
      result.add(row);
    }
    return result;
  }

  private static int solve(List<Integer> row) {
    var cached = new int[row.size() + 1][row.size() + 1];
    for (int i = 0; i < row.size(); i++) {
      cached[0][i] = row.get(i);
    }

    for (int i = 1; i <= row.size(); i++) {
      for (int j = i; j < row.size(); j++) {
        cached[i][j] = cached[i - 1][j] - cached[i - 1][j - 1];
      }
    }

    for (int i = row.size(); i > 0; i--) {
      var lastIdx = row.size();
      cached[i - 1][lastIdx] = cached[i][lastIdx] + cached[i - 1][lastIdx - 1];
    }

    return cached[0][row.size()];
  }

  public static int Part1(List<String> input) {
    var rows = parseInput(input);
    var sum = 0;
    for (var row : rows) {
      sum += solve(row);
    }

    return sum;
  }

  public static int Part2(List<String> input) {
    return -1;
  }
}
