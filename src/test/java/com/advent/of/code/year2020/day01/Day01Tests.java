package com.advent.of.code.year2020.day01;
import static com.advent.of.code.Helpers.getExampleText;
import static com.advent.of.code.Helpers.getRealText;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import org.junit.Test;

public class Day01Tests {
  @Test
  public void example1() throws IOException {
    List<String> lines = getExampleText("year2020", "day01");
    assertEquals(514579, Day01.Part1(lines));
  }

  @Test
  public void real1() throws IOException {
    List<String> lines = getRealText("year2020", "day01");
    assertEquals(252724, Day01.Part1(lines));
  }

  @Test
  public void example2() throws IOException {
    List<String> lines = getExampleText("year2020", "day01");
    assertEquals(241861950, Day01.Part2(lines));
  }

  @Test
  public void real2() throws IOException {
    List<String> lines = getRealText("year2020", "day01");
    assertEquals(276912720, Day01.Part2(lines));
  }
}
