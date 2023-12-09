package com.advent.of.code.year2023.day07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Day07 {

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
    var cards =
        input.stream()
            .map(
                x -> {
                  var handType = parseHand(x);
                  return new TotalHand(determineHandType(handType.cards()), handType);
                })
            .sorted(new TotalHandComparator())
            .collect(Collectors.toList());
    var answer = 0;
    for (int i = 0; i < cards.size(); i++) {
      var card = cards.get(i);
      answer += card.hand().bid() * (i + 1);
    }
    return answer;
  }

  public static int Part2(List<String> input) {
    return -1;
  }
}

record Pair<T, S>(T first, S second) {}

enum Card {
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

enum HandType {
  HIGH_CARD,
  ONE_PAIR,
  TWO_PAIRS,
  THREE_OF_A_KIND,
  FULL_HOUSE,
  FOUR_OF_A_KIND,
  FIVE_OF_A_KIND,
}

record Hand(List<Card> cards, int bid) {}

record TotalHand(HandType handType, Hand hand) {}

class TotalHandComparator implements Comparator<TotalHand> {
  @Override
  public int compare(TotalHand a, TotalHand b) {
    if (a.handType() != b.handType()) {
      return a.handType().compareTo(b.handType());
    } else {
      for (int i = 0; i < a.hand().cards().size(); i++) {
        var cardA = a.hand().cards().get(i);
        var cardB = b.hand().cards().get(i);
        if (cardA != cardB) {
          return cardA.compareTo(cardB);
        }
      }
    }
    return 0;
  }
}
