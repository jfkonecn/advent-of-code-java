package com.advent.of.code.year2023.day01;

import java.util.List;
import java.util.stream.Collectors;

public class Day01 {
  public static int Part1(List<String> input) {
    List<Integer> numbers = input.stream()
                                .mapToInt(Integer::parseInt)
                                .boxed()
                                .collect(Collectors.toList());
    for (Integer outter : numbers) {
      for (Integer inner : numbers) {
        if (outter != inner && outter + inner == 2020) {
          return outter * inner;
        }
      }
    }
    return -1;
  }

  public static int Part2(List<String> input) {
    List<Integer> numbers = input.stream()
                                .mapToInt(Integer::parseInt)
                                .boxed()
                                .collect(Collectors.toList());
    for (Integer first : numbers) {
      for (Integer second : numbers) {
        for (Integer third : numbers) {
          if (first + second + third == 2020) {
            return first * second * third;
          }
        }
      }
    }
    return -1;
  }
}
