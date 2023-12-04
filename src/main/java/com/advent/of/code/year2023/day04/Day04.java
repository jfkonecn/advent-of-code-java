package com.advent.of.code.year2023.day04;

import java.util.Arrays;
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
  }

  public static int Part2(List<String> input) {
    var winningCounts =
        input.stream()
            .map(Day04::parseCard)
            .map(
                x -> {
                  var winningNumbers = x.winningNumbers;
                  var myNumbers = x.myNumbers;
                  return (int)
                      winningNumbers.stream()
                          .filter(y -> myNumbers.stream().anyMatch(z -> z == y))
                          .count();
                })
            .collect(Collectors.toList());
    var cardCopies = new int[winningCounts.size()];
    for (int i = 0; i < winningCounts.size(); i++) {
      var maxCardCopies = winningCounts.get(i);
      var totalCopiesForThisCard = cardCopies[i];
      cardCopies[i] += 1;
      for (int j = 0; j < totalCopiesForThisCard + 1; j++) {
        for (int k = i + 1; k < cardCopies.length && k < i + 1 + maxCardCopies; k++) {
          cardCopies[k] += 1;
        }
      }
    }
    return Arrays.stream(cardCopies).sum();
  }
}
