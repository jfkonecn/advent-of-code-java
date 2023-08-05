package com.advent.of.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Helpers {
  public static List<String> getExampleText(String day) throws IOException {

    return Files.readAllLines(
        Paths.get("target", "test-classes", day, "example.txt"));
  }

  public static List<String> getRealText(String day) throws IOException {

    return Files.readAllLines(
        Paths.get("target", "test-classes", day, "real.txt"));
  }
}
