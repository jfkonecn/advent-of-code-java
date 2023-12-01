package com.advent.of.code.year2020.day02;

import java.util.List;

class PasswordRecord {
  private int min;
  private int max;
  private char letter;
  private String password;

  public PasswordRecord(int min, int max, char letter, String password) {
    this.min = min;
    this.max = max;
    this.letter = letter;
    this.password = password;
  }

  public int getMin() { return min; }

  public int getMax() { return max; }

  public char getLetter() { return letter; }

  public String getPassword() { return password; }

  public static PasswordRecord parse(String line) {
    String[] parts = line.split(" ");
    String[] minMax = parts[0].split("-");
    int min = Integer.parseInt(minMax[0]);
    int max = Integer.parseInt(minMax[1]);
    char letter = parts[1].charAt(0);
    String password = parts[2];
    return new PasswordRecord(min, max, letter, password);
  }

  public boolean isValidPart1() {
    int count = 0;
    for (char c : password.toCharArray()) {
      if (c == letter) {
        count++;
      }
    }
    return count >= min && count <= max;
  }

  public boolean isValidPart2() {
    return password.charAt(min - 1) == letter ^
        password.charAt(max - 1) == letter;
  }
}

public class Day02 {
  public static long Part1(List<String> input) {

    return input.stream()
        .map(PasswordRecord::parse)
        .filter(x -> x.isValidPart1())
        .count();
  }

  public static long Part2(List<String> input) {
    return input.stream()
        .map(PasswordRecord::parse)
        .filter(x -> x.isValidPart2())
        .count();
  }
}
