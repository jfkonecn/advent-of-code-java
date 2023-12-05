package com.advent.of.code.year2023.day04;

import static com.advent.of.code.Helpers.getExampleText;
import static com.advent.of.code.Helpers.getRealText;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import org.junit.Test;

public class Day04Tests {
  @Test
  public void example1() throws IOException {
    List<String> lines = getExampleText("year2023", "day04");
    assertEquals(13, Day04.Part1(lines));
  }

  @Test
  public void real1() throws IOException {
    List<String> lines = getRealText("year2023", "day04");
    assertEquals(21158, Day04.Part1(lines));
  }

  @Test
  public void example2() throws IOException {
    List<String> lines = getExampleText("year2023", "day04");
    assertEquals(30, Day04.Part2(lines));
  }

  @Test
  public void real2() throws IOException {
    List<String> lines = getRealText("year2023", "day04");
    assertEquals(6040769, Day04.Part2(lines));
  }
}
