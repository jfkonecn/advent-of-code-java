package com.advent.of.code.day02;
import static com.advent.of.code.Helpers.getExampleText;
import static com.advent.of.code.Helpers.getRealText;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import org.junit.Test;

public class Day02Tests {
  @Test
  public void example1() throws IOException {
    List<String> lines = getExampleText("day02");
    assertEquals(2L, Day02.Part1(lines));
  }

  @Test
  public void real1() throws IOException {
    List<String> lines = getRealText("day02");
    assertEquals(506L, Day02.Part1(lines));
  }

  @Test
  public void example2() throws IOException {
    List<String> lines = getExampleText("day02");
    assertEquals(1L, Day02.Part2(lines));
  }

  @Test
  public void real2() throws IOException {
    List<String> lines = getRealText("day02");
    assertEquals(443L, Day02.Part2(lines));
  }
}
