package com.advent.of.code.year2020.day03;
import static com.advent.of.code.Helpers.getExampleText;
import static com.advent.of.code.Helpers.getRealText;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import org.junit.Test;

public class Day03Tests {
  @Test
  public void example1() throws IOException {
    List<String> lines = getExampleText("year2020", "day03");
    assertEquals(7, Day03.Part1(lines));
  }

  @Test
  public void real1() throws IOException {
    List<String> lines = getRealText("year2020", "day03");
    assertEquals(171, Day03.Part1(lines));
  }

  @Test
  public void example2() throws IOException {
    List<String> lines = getExampleText("year2020", "day03");
    assertEquals(336, Day03.Part2(lines));
  }

  @Test
  public void real2() throws IOException {
    List<String> lines = getRealText("year2020", "day03");
    assertEquals(1206576000, Day03.Part2(lines));
  }
}
