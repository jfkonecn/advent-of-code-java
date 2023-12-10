package com.advent.of.code.year2023.day10;

import static com.advent.of.code.Helpers.getExample2Text;
import static com.advent.of.code.Helpers.getExample3Text;
import static com.advent.of.code.Helpers.getExample4Text;
import static com.advent.of.code.Helpers.getExample5Text;
import static com.advent.of.code.Helpers.getExample6Text;
import static com.advent.of.code.Helpers.getExampleText;
import static com.advent.of.code.Helpers.getRealText;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import org.junit.Test;

public class Day10Tests {
  @Test
  public void example1a() throws IOException {
    List<String> lines = getExampleText("year2023", "day10");
    assertEquals(4, Day10.Part1(lines));
  }

  @Test
  public void example1b() throws IOException {
    List<String> lines = getExample2Text("year2023", "day10");
    assertEquals(8, Day10.Part1(lines));
  }

  @Test
  public void real1() throws IOException {
    List<String> lines = getRealText("year2023", "day10");
    assertEquals(7093, Day10.Part1(lines));
  }

  @Test
  public void example2a() throws IOException {
    List<String> lines = getExample3Text("year2023", "day10");
    assertEquals(4, Day10.Part2(lines));
  }

  @Test
  public void example2b() throws IOException {
    List<String> lines = getExample4Text("year2023", "day10");
    assertEquals(8, Day10.Part2(lines));
  }

  @Test
  public void example2c() throws IOException {
    List<String> lines = getExample5Text("year2023", "day10");
    assertEquals(10, Day10.Part2(lines));
  }

  @Test
  public void example2d() throws IOException {
    List<String> lines = getExample6Text("year2023", "day10");
    assertEquals(4, Day10.Part2(lines));
  }

  @Test
  public void real2() throws IOException {
    List<String> lines = getRealText("year2023", "day10");
    // 388 is too low
    assertEquals(0, Day10.Part2(lines));
  }
}
