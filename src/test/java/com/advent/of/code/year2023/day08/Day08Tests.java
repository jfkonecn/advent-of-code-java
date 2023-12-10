package com.advent.of.code.year2023.day08;

import static com.advent.of.code.Helpers.getExample2Text;
import static com.advent.of.code.Helpers.getExample3Text;
import static com.advent.of.code.Helpers.getExampleText;
import static com.advent.of.code.Helpers.getRealText;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import org.junit.Test;

public class Day08Tests {
  @Test
  public void example1a() throws IOException {
    List<String> lines = getExampleText("year2023", "day08");
    assertEquals(2, Day08.Part1(lines));
  }

  @Test
  public void example1b() throws IOException {
    List<String> lines = getExample2Text("year2023", "day08");
    assertEquals(6, Day08.Part1(lines));
  }

  @Test
  public void real1() throws IOException {
    List<String> lines = getRealText("year2023", "day08");
    assertEquals(19667, Day08.Part1(lines));
  }

  @Test
  public void example2() throws IOException {
    List<String> lines = getExample3Text("year2023", "day08");
    assertEquals(6, Day08.Part2(lines));
  }

  @Test
  public void real2() throws IOException {
    List<String> lines = getRealText("year2023", "day08");
    assertEquals(19185263738117L, Day08.Part2(lines));
  }
}
