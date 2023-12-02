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
        .map(x -> parseGame(x))
        .filter(
            x -> {
              var totalRed = x.gameSets().stream().mapToInt(GameSet::red).max().getAsInt();
              var totalGreen = x.gameSets().stream().mapToInt(GameSet::green).max().getAsInt();
              var totalBlue = x.gameSets().stream().mapToInt(GameSet::blue).max().getAsInt();
              return totalRed <= 12 && totalGreen <= 13 && totalBlue <= 14;
            })
        .mapToInt(Game::id)
        .sum();
  }

  public static int Part2(List<String> input) {
    return input.stream()
        .map(x -> parseGame(x))
        .mapToInt(
            x -> {
              var totalRed = x.gameSets().stream().mapToInt(GameSet::red).max().getAsInt();
              var totalGreen = x.gameSets().stream().mapToInt(GameSet::green).max().getAsInt();
              var totalBlue = x.gameSets().stream().mapToInt(GameSet::blue).max().getAsInt();
              return totalRed * totalGreen * totalBlue;
            })
        .sum();
  }
}
