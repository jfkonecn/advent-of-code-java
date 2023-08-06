package com.advent.of.code.day04;
import static com.advent.of.code.Helpers.getExampleText;
import static com.advent.of.code.Helpers.getRealText;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import org.junit.Test;

public class Day04Tests {
  @Test
  public void example1() throws IOException, Exception {
    List<String> lines = getExampleText("day04");
    assertEquals(2L, Day04.Part1(lines));
  }

  @Test
  public void real1() throws IOException, Exception {
    List<String> lines = getRealText("day04");
    assertEquals(264L, Day04.Part1(lines));
  }

  @Test
  public void example2() throws IOException, Exception {
    List<String> lines = getExampleText("day04");
    assertEquals(0, Day04.Part2(lines));
  }

  @Test
  public void real2() throws IOException, Exception {
    List<String> lines = getRealText("day04");
    assertEquals(0, Day04.Part2(lines));
  }
}
