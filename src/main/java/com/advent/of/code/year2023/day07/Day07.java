package com.advent.of.code.year2023.day07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Day07 {

  private static List<Card1> parseCards1(String input) {
    var cards = new ArrayList<Card1>();
    for (var c : input.toCharArray()) {
      if (c == '2') {
        cards.add(Card1.TWO);
      } else if (c == '3') {
        cards.add(Card1.THREE);
      } else if (c == '4') {
        cards.add(Card1.FOUR);
      } else if (c == '5') {
        cards.add(Card1.FIVE);
      } else if (c == '6') {
        cards.add(Card1.SIX);
      } else if (c == '7') {
        cards.add(Card1.SEVEN);
      } else if (c == '8') {
        cards.add(Card1.EIGHT);
      } else if (c == '9') {
        cards.add(Card1.NINE);
      } else if (c == 'T') {
        cards.add(Card1.TEN);
      } else if (c == 'J') {
        cards.add(Card1.JACK);
      } else if (c == 'Q') {
        cards.add(Card1.QUEEN);
      } else if (c == 'K') {
        cards.add(Card1.KING);
      } else if (c == 'A') {
        cards.add(Card1.ACE);
      }
    }
    return cards;
  }

  private static List<Card2> parseCards2(String input) {
    var cards = new ArrayList<Card2>();
    for (var c : input.toCharArray()) {
      if (c == 'J') {
        cards.add(Card2.JOKER);
      } else if (c == '2') {
        cards.add(Card2.TWO);
      } else if (c == '3') {
        cards.add(Card2.THREE);
      } else if (c == '4') {
        cards.add(Card2.FOUR);
      } else if (c == '5') {
        cards.add(Card2.FIVE);
      } else if (c == '6') {
        cards.add(Card2.SIX);
      } else if (c == '7') {
        cards.add(Card2.SEVEN);
      } else if (c == '8') {
        cards.add(Card2.EIGHT);
      } else if (c == '9') {
        cards.add(Card2.NINE);
      } else if (c == 'T') {
        cards.add(Card2.TEN);
      } else if (c == 'Q') {
        cards.add(Card2.QUEEN);
      } else if (c == 'K') {
        cards.add(Card2.KING);
      } else if (c == 'A') {
        cards.add(Card2.ACE);
      }
    }
    return cards;
  }

  private static Hand1 parseHand1(String input) {
    var split = input.split(" ");
    var cards = parseCards1(split[0]);
    var bid = Integer.parseInt(split[1]);
    return new Hand1(cards, bid);
  }

  private static Hand2 parseHand2(String input) {
    var split = input.split(" ");
    var cards = parseCards2(split[0]);
    var bid = Integer.parseInt(split[1]);
    return new Hand2(cards, bid);
  }

  private static HandType determineHandType1(List<Card1> cards) {
    var dic = new HashMap<Card1, Integer>();
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

  private static HandType determineHandType2(List<Card2> cards) {
    var dic = new HashMap<Card2, Integer>();
    var jokerCount = 0;
    for (var card : cards) {
      if (card == Card2.JOKER) {
        jokerCount++;
      } else if (dic.containsKey(card)) {
        dic.put(card, dic.get(card) + 1);
      } else {
        dic.put(card, 1);
      }
    }
    // find max count of a card and add jokers to it
    var dicHasValues = dic.values().size() > 0;
    var max = dicHasValues ? 0 : jokerCount;
    var maxCard = dicHasValues ? dic.keySet().stream().findFirst().get() : Card2.JOKER;
    for (var entry : dic.entrySet()) {
      if (entry.getValue() > max) {
        max = entry.getValue();
        maxCard = entry.getKey();
      }
    }
    if (dicHasValues) {
      dic.put(maxCard, max + jokerCount);
    } else {
      dic.put(maxCard, jokerCount);
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
                  var handType = parseHand1(x);
                  return new TotalHand1(determineHandType1(handType.cards()), handType);
                })
            .sorted(new TotalHandComparator1())
            .collect(Collectors.toList());
    var answer = 0;
    for (int i = 0; i < cards.size(); i++) {
      var card = cards.get(i);
      answer += card.hand().bid() * (i + 1);
    }
    return answer;
  }

  public static int Part2(List<String> input) {
    var cards =
        input.stream()
            .map(
                x -> {
                  var handType = parseHand2(x);
                  return new TotalHand2(determineHandType2(handType.cards()), handType);
                })
            .sorted(new TotalHandComparator2())
            .collect(Collectors.toList());
    var answer = 0;
    for (int i = 0; i < cards.size(); i++) {
      var card = cards.get(i);
      answer += card.hand().bid() * (i + 1);
    }
    return answer;
  }
}

enum Card1 {
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

enum Card2 {
  JOKER,
  TWO,
  THREE,
  FOUR,
  FIVE,
  SIX,
  SEVEN,
  EIGHT,
  NINE,
  TEN,
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

record Hand1(List<Card1> cards, int bid) {}

record Hand2(List<Card2> cards, int bid) {}

record TotalHand1(HandType handType, Hand1 hand) {}

record TotalHand2(HandType handType, Hand2 hand) {}

class TotalHandComparator1 implements Comparator<TotalHand1> {
  @Override
  public int compare(TotalHand1 a, TotalHand1 b) {
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

class TotalHandComparator2 implements Comparator<TotalHand2> {
  @Override
  public int compare(TotalHand2 a, TotalHand2 b) {
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
