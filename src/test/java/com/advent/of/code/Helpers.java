package com.advent.of.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Helpers {
  public static List<String> getExampleText(String year, String day) throws IOException {

    return Files.readAllLines(Paths.get("target", "test-classes", year, day, "example.txt"));
  }

  public static List<String> getRealText(String year, String day) throws IOException {

    return Files.readAllLines(Paths.get("target", "test-classes", year, day, "real.txt"));
  }

  public static List<String> getExample2Text(String year, String day) throws IOException {

    return Files.readAllLines(Paths.get("target", "test-classes", year, day, "example2.txt"));
  }

  public static List<String> getExample3Text(String year, String day) throws IOException {

    return Files.readAllLines(Paths.get("target", "test-classes", year, day, "example3.txt"));
  }
}
