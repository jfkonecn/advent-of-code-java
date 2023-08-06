package com.advent.of.code.day04;

import java.util.ArrayList;
import java.util.List;

enum Length {
  inches,
  centimeters,
}

class Height {
  private Integer value;
  public Integer getValue() { return value; }
  private Length unit;
  public Length getUnit() { return unit; }
  public Height(Integer value, Length unit) {
    this.value = value;
    this.unit = unit;
  }
}

class Passport {
  private Integer birthYear;
  private Integer issueYear;
  private Integer expirationYear;
  private Height height;
  private String hairColor;
  private String eyeColor;
  private String passportId;
  private String countryId;

  private Passport(String line) {
    String[] parts = line.split(" ");
    for (String part : parts) {
      String[] rawField = part.split(":");
      String label = rawField[0];
      String value = rawField[1];

      switch (label) {
      case "byr":
        birthYear = Integer.parseInt(value);
        break;
      case "iyr":
        issueYear = Integer.parseInt(value);
        break;
      case "eyr":
        expirationYear = Integer.parseInt(value);
        break;
      case "hgt":
        int lastIndex = value.length() - 2;
        String firstHalf = value.substring(0, lastIndex);
        String secondHalf = value.substring(lastIndex);

        Integer unitValue = Integer.parseInt(firstHalf);
        Length unit = switch (secondHalf) {
            case "in" -> Length.inches;
            default -> Length.centimeters;
        };
        height = new Height(unitValue, unit);

        break;
      case "hcl":
              hairColor = value;
              break;
            case "ecl":
              eyeColor = value;
              break;
            case "pid":
              passportId = value;
              break;
            case "cid":
              countryId = value;
              break;
            default:
              break;
            };
      }
    }

    private static Passport createPassport(StringBuilder builder) {
      Passport passport = new Passport(builder.toString().trim());
      builder.setLength(0);
      return passport;
    }

    public static List<Passport> parse(List<String> input) {
      StringBuilder builder = new StringBuilder();
      List<Passport> passports = new ArrayList<>();
      for (String line : input) {
            if (line.isEmpty()) {
              passports.add(createPassport(builder));

            } else {
              builder.append(" ");
              builder.append(line);
            }
      }
      passports.add(createPassport(builder));
      return passports;
    }

    public boolean isValidPart1() {
      return !(birthYear == null || issueYear == null ||
               expirationYear == null || height == null || hairColor == null ||
               eyeColor == null || passportId == null);
    }
  }

  public class Day04 {

    public static long Part1(List<String> input) {
      List<Passport> passports = Passport.parse(input);
      return passports.stream().filter(x -> x.isValidPart1()).count();
    }

    public static int Part2(List<String> input) { return -1; }
  }
