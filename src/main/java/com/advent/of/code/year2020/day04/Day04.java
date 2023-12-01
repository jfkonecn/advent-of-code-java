package com.advent.of.code.year2020.day04;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

enum Length {
  inches,
  centimeters,
}

class Height {
  private Integer value;
  public Integer getValue() { return value; }
  private Length unit;
  public Length getUnit() { return unit; }
  public Height(String value) {
    if (value.length() < 3) {
      return;
    }
    int lastIndex = value.length() - 2;
    String firstHalf = value.substring(0, lastIndex);
    String secondHalf = value.substring(lastIndex);

    this.value = Integer.parseInt(firstHalf);
    this.unit = switch (secondHalf) {
            case "in" -> Length.inches;
            case "cm" -> Length.centimeters;
            default -> null;
        };
  }

  public boolean isValid() {
      if ( value != null && this.unit != null) {
          if (this.unit == Length.centimeters) {
              return 150 <= this.value && this.value <= 193;
          } else {
              return 59 <= this.value && this.value <= 76;
          }
      } else {
          return false;
      }
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
                  height = new Height(value);

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
                       expirationYear == null || height == null ||
                       hairColor == null || eyeColor == null ||
                       passportId == null);
            }

            private static Pattern hairColorPattern =
                Pattern.compile("^#[\\da-f]{6}$");
            private static Pattern passportIdPattern =
                Pattern.compile("^[\\d]{9}$");

            public boolean isValidPart2() {
              boolean birthYearIsValid =
                  birthYear != null && 1920 <= birthYear && birthYear <= 2002;
              boolean issueYearIsValid =
                  issueYear != null && 2010 <= issueYear && issueYear <= 2020;
              boolean expirationYearIsValid = expirationYear != null &&
                                              2020 <= expirationYear &&
                                              expirationYear <= 2030;
              boolean heightIsValid = height != null && height.isValid();
              boolean hairColorIsValid =
                  hairColor != null &&
                  hairColorPattern.matcher(hairColor).matches();
              boolean eyeColorIsValid = eyeColor != null && switch (eyeColor) {
                  case "amb", "blu", "brn", "gry", "grn", "hzl", "oth" -> true;
                  default -> false;
              };
              boolean passportIdIsValid = passportId != null && passportIdPattern.matcher(passportId).matches();
              boolean countryIdIsValid = true;
              return birthYearIsValid && issueYearIsValid &&
                  expirationYearIsValid && heightIsValid && hairColorIsValid &&
                  eyeColorIsValid && passportIdIsValid && countryIdIsValid;
            }
  }

  public class Day04 {

            public static long Part1(List<String> input) {
              List<Passport> passports = Passport.parse(input);
              return passports.stream().filter(x -> x.isValidPart1()).count();
            }

            public static long Part2(List<String> input) {
              List<Passport> passports = Passport.parse(input);
              return passports.stream().filter(x -> x.isValidPart2()).count();
            }
  }
