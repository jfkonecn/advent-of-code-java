package com.advent.of.code.day1;
import static com.advent.of.code.Helpers.getExampleText;
import static com.advent.of.code.Helpers.getRealText;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import org.junit.Test;

public class Day2Tests {
  @Test
  public void example1() throws IOException {
    List<String> lines = getExampleText("day2");
    assertEquals(-1, Day2.Part1(lines));
  }

  @Test
  public void real1() throws IOException {
    List<String> lines = getRealText("day2");
    assertEquals(-1, Day2.Part1(lines));
  }

  @Test
  public void example2() throws IOException {
    List<String> lines = getExampleText("day2");
    assertEquals(-1, Day2.Part2(lines));
  }

  @Test
  public void real2() throws IOException {
    List<String> lines = getRealText("day2");
    assertEquals(-1, Day2.Part2(lines));
  }
}
