package com.advent.of.code.year2023.day02;

import static com.advent.of.code.Helpers.getExampleText;
import static com.advent.of.code.Helpers.getRealText;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import org.junit.Test;

public class Day02Tests {
  @Test
  public void example1() throws IOException {
    List<String> lines = getExampleText("year2023", "day02");
    assertEquals(8, Day02.Part1(lines));
  }

  @Test
  public void real1() throws IOException {
    List<String> lines = getRealText("year2023", "day02");
    assertEquals(2085, Day02.Part1(lines));
  }

  @Test
  public void example2() throws IOException {
    List<String> lines = getExampleText("year2023", "day02");
    assertEquals(2286, Day02.Part2(lines));
  }

  @Test
  public void real2() throws IOException {
    List<String> lines = getRealText("year2023", "day02");
    assertEquals(79315, Day02.Part2(lines));
  }
}
