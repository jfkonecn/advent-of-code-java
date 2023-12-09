package com.advent.of.code.year2023.day06;

import static com.advent.of.code.Helpers.getExampleText;
import static com.advent.of.code.Helpers.getRealText;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import org.junit.Test;

public class Day06Tests {
  @Test
  public void example1() throws IOException {
    List<String> lines = getExampleText("year2023", "day06");
    assertEquals(288L, Day06.Part1(lines));
  }

  @Test
  public void real1() throws IOException {
    List<String> lines = getRealText("year2023", "day06");
    assertEquals(2344708L, Day06.Part1(lines));
  }

  @Test
  public void example2() throws IOException {
    List<String> lines = getExampleText("year2023", "day06");
    assertEquals(71503L, Day06.Part2(lines));
  }

  @Test
  public void real2() throws IOException {
    List<String> lines = getRealText("year2023", "day06");
    assertEquals(30125202L, Day06.Part2(lines));
  }
}
