package com.advent.of.code.year2023.day07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Day07 {
  private record Pair<T, S>(T first, S second) {}

  private enum Card {
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    KING,
    ACE
  }

  private enum HandType {
    HIGH_CARD,
    ONE_PAIR,
    TWO_PAIRS,
    THREE_OF_A_KIND,
    FULL_HOUSE,
    FOUR_OF_A_KIND,
    FIVE_OF_A_KIND,
  }

  private record Hand(List<Card> cards, int bid) {}

  private static List<Card> parseCards(String input) {
    var cards = new ArrayList<Card>();
    for (var c : input.toCharArray()) {
      if (c == '2') {
        cards.add(Card.TWO);
      } else if (c == '3') {
        cards.add(Card.THREE);
      } else if (c == '4') {
        cards.add(Card.FOUR);
      } else if (c == '5') {
        cards.add(Card.FIVE);
      } else if (c == '6') {
        cards.add(Card.SIX);
      } else if (c == '7') {
        cards.add(Card.SEVEN);
      } else if (c == '8') {
        cards.add(Card.EIGHT);
      } else if (c == '9') {
        cards.add(Card.NINE);
      } else if (c == 'T') {
        cards.add(Card.TEN);
      } else if (c == 'J') {
        cards.add(Card.JACK);
      } else if (c == 'Q') {
        cards.add(Card.QUEEN);
      } else if (c == 'K') {
        cards.add(Card.KING);
      } else if (c == 'A') {
        cards.add(Card.ACE);
      }
    }
    return cards;
  }

  private static Hand parseHand(String input) {
    var split = input.split(" ");
    var cards = parseCards(split[0]);
    var bid = Integer.parseInt(split[1]);
    return new Hand(cards, bid);
  }

  private static HandType determineHandType(List<Card> cards) {
    var dic = new HashMap<Card, Integer>();
    for (var card : cards) {
      if (dic.containsKey(card)) {
        dic.put(card, dic.get(card) + 1);
      } else {
        dic.put(card, 1);
      }
    }
    for (var entry : dic.entrySet()) {
      if (entry.getValue() == 5) {
        return HandType.FIVE_OF_A_KIND;
      } else if (entry.getValue() == 4) {
        return HandType.FOUR_OF_A_KIND;
      } else if (entry.getValue() == 3) {
        if (dic.size() == 2) {
          return HandType.FULL_HOUSE;
        } else {
          return HandType.THREE_OF_A_KIND;
        }
      } else if (entry.getValue() == 2) {
        if (dic.size() == 3) {
          return HandType.TWO_PAIRS;
        } else if (dic.size() == 4) {
          return HandType.ONE_PAIR;
        }
      }
    }
    return HandType.HIGH_CARD;
  }

  public static int Part1(List<String> input) {
    var cards = input.stream().map(x -> parseHand(x)).collect(Collectors.toList());
    return -1;
  }

  public static int Part2(List<String> input) {
    return -1;
  }
}
