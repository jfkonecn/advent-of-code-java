package com.advent.of.code.year2023.day02;

import java.util.ArrayList;
import java.util.List;

public class Day02 {
  private record GameSet(int red, int green, int blue) {}

  private record Game(int id, ArrayList<GameSet> gameSets) {}

  private static Game parseGame(String input) {
    var gameSets = new ArrayList<GameSet>();
    var id = Integer.parseInt(input.substring(input.indexOf(" ") + 1, input.indexOf(":")));
    var gameSetsStr = input.substring(input.indexOf(":") + 2);
    for (var gameSetString : gameSetsStr.split("; ")) {
      var red = 0;
      var green = 0;
      var blue = 0;
      for (var colorStr : gameSetString.split(", ")) {
        var splitColor = colorStr.split(" ");
        var total = Integer.parseInt(splitColor[0]);
        var colorName = splitColor[1];

        if (colorName.equals("red")) {
          red = total;
        } else if (colorName.equals("green")) {
          green = total;
        } else if (colorName.equals("blue")) {
          blue = total;
        }
      }
      gameSets.add(new GameSet(red, green, blue));
    }
    return new Game(id, gameSets);
  }

  public static int Part1(List<String> input) {
    return input.stream()
        .map(
            x -> {
              var game = parseGame(x);
              return "1";
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
