package com.advent.of.code.year2023.day04;

import java.util.List;
import java.util.stream.Collectors;

public class Day04 {

  private record Card(int id, List<Integer> winningNumbers, List<Integer> myNumbers) {}

  private static Card parseCard(String input) {
    var headNumbersSplit = input.split(":");
    var id = Integer.parseInt(headNumbersSplit[0].replace("Card ", "").trim());
    var numbersSplit = headNumbersSplit[1].trim().split("\\|");
    var winningNumbers =
        List.of(numbersSplit[0].split(" ")).stream()
            .filter(x -> !x.isBlank())
            .map(x -> Integer.parseInt(x.trim()))
            .collect(Collectors.toList());
    var myNumbers =
        List.of(numbersSplit[1].split(" ")).stream()
            .filter(x -> !x.isBlank())
            .map(x -> Integer.parseInt(x.trim()))
            .collect(Collectors.toList());
    return new Card(id, winningNumbers, myNumbers);
  }

  public static int Part1(List<String> input) {
    return input.stream()
        .map(Day04::parseCard)
        .mapToInt(
            x -> {
              var winningNumbers = x.winningNumbers;
              var myNumbers = x.myNumbers;
              var winningNumbersCount =
                  winningNumbers.stream()
                      .filter(y -> myNumbers.stream().anyMatch(z -> z == y))
                      .count();
              if (winningNumbersCount > 0) {
                return (int) Math.pow(2, winningNumbersCount - 1);
              } else {
                return 0;
              }
            })
        .sum();
    // .collect(Collectors.toList());
  }

  public static int Part2(List<String> input) {
    return 0;
  }
}
