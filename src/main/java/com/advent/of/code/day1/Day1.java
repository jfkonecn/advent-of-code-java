package com.advent.of.code.day1;

import java.util.List;

public class Day1 {
  public static int Part1(List<String> input) {
    return input.stream().mapToInt(Integer::parseInt).sum();
  }
}
