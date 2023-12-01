package com.advent.of.code.year2023.day01;

import java.util.List;

public class Day01 {
  public static int Part1(List<String> input) {
    return input.stream()
        .map(
            x -> {
              StringBuilder sb = new StringBuilder();
              for (int i = 0; i < x.length(); i++) {
                if (Character.isDigit(x.charAt(i))) {
                  sb.append(x.charAt(i));
                }
              }
              var temp = sb.toString();
              return String.format("%s%s", temp.charAt(0), temp.charAt(temp.length() - 1));
            })
        .mapToInt(Integer::parseInt)
        .sum();
  }

  public static int Part2(List<String> input) {
    return input.stream()
        .map(
            x -> {
              StringBuilder sb = new StringBuilder();
              for (int i = 0; i < x.length(); i++) {
                if (Character.isDigit(x.charAt(i))) {
                  sb.append(x.charAt(i));
                } else if (x.substring(i).startsWith("one")) {
                  sb.append("1");
                } else if (x.substring(i).startsWith("two")) {
                  sb.append("2");
                } else if (x.substring(i).startsWith("three")) {
                  sb.append("3");
                } else if (x.substring(i).startsWith("four")) {
                  sb.append("4");
                } else if (x.substring(i).startsWith("five")) {
                  sb.append("5");
                } else if (x.substring(i).startsWith("six")) {
                  sb.append("6");
                } else if (x.substring(i).startsWith("seven")) {
                  sb.append("7");
                } else if (x.substring(i).startsWith("eight")) {
                  sb.append("8");
                } else if (x.substring(i).startsWith("nine")) {
                  sb.append("9");
                }
              }
              var temp = sb.toString();
              return String.format("%s%s", temp.charAt(0), temp.charAt(temp.length() - 1));
            })
        .mapToInt(Integer::parseInt)
        .sum();
  }
}
