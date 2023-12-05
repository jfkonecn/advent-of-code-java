package com.advent.of.code.year2023.day05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalLong;

public class Day05 {

  private record Converter(Long sourceStart, Long destinationStart, Long length) {
    public OptionalLong tryConvert(int source) {
      if (source >= sourceStart && source < sourceStart + length) {
        return OptionalLong.of(destinationStart + (source - sourceStart));
      } else {
        return OptionalLong.empty();
      }
    }
  }

  private record SeedMap(
      List<Long> seeds,
      List<Converter> seedToSoilConverters,
      List<Converter> soilToFertilizerConverters,
      List<Converter> fertilizerToWaterConverters,
      List<Converter> waterToLightConverters,
      List<Converter> lightToTemperatureConverters,
      List<Converter> temperatureToHumidityConverters,
      List<Converter> humidityToLocationConverters) {}

  private record Pair<T, U>(T first, U second) {}

  private static Pair<Integer, List<Converter>> parseConverters(int i, List<String> input) {
    var converters = new ArrayList<Converter>();
    while (i < input.size()) {
      var line = input.get(i++);
      if (line.isBlank()) {
        break;
      }
      var parts = line.split(" ");
      var sourceStart = Long.parseLong(parts[0]);
      var destinationStart = Long.parseLong(parts[1]);
      var length = Long.parseLong(parts[2]);
      converters.add(new Converter(sourceStart, destinationStart, length));
    }
    i++;
    return new Pair<>(i, converters);
  }

  private static SeedMap parseSeedMap(List<String> input) {
    List<Long> seeds =
        Arrays.stream(input.get(0).split(":")[1].trim().split(" ")).map(Long::parseLong).toList();

    var i = 3;
    var seedToSoilConvertersPair = parseConverters(i, input);
    i = seedToSoilConvertersPair.first();
    var seedToSoilConverters = seedToSoilConvertersPair.second();
    var soilToFertilizerConvertersPair = parseConverters(i, input);
    i = soilToFertilizerConvertersPair.first();
    var soilToFertilizerConverters = soilToFertilizerConvertersPair.second();
    var fertilizerToWaterConvertersPair = parseConverters(i, input);
    i = fertilizerToWaterConvertersPair.first();
    var fertilizerToWaterConverters = fertilizerToWaterConvertersPair.second();
    var waterToLightConvertersPair = parseConverters(i, input);
    i = waterToLightConvertersPair.first();
    var waterToLightConverters = waterToLightConvertersPair.second();
    var lightToTemperatureConvertersPair = parseConverters(i, input);
    i = lightToTemperatureConvertersPair.first();
    var lightToTemperatureConverters = lightToTemperatureConvertersPair.second();
    var temperatureToHumidityConvertersPair = parseConverters(i, input);
    i = temperatureToHumidityConvertersPair.first();
    var temperatureToHumidityConverters = temperatureToHumidityConvertersPair.second();
    var humidityToLocationConvertersPair = parseConverters(i, input);
    i = humidityToLocationConvertersPair.first();
    var humidityToLocationConverters = humidityToLocationConvertersPair.second();

    return new SeedMap(
        seeds,
        seedToSoilConverters,
        soilToFertilizerConverters,
        fertilizerToWaterConverters,
        waterToLightConverters,
        lightToTemperatureConverters,
        temperatureToHumidityConverters,
        humidityToLocationConverters);
  }

  public static int Part1(List<String> input) {
    var seedMap = parseSeedMap(input);
    return 0;
  }

  public static int Part2(List<String> input) {
    return 0;
  }
}
