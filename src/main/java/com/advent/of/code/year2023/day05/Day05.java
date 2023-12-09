package com.advent.of.code.year2023.day05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalLong;
import java.util.stream.Collectors;

public class Day05 {

  private record Converter(Long sourceStart, Long destinationStart, Long length) {
    public OptionalLong tryConvert(Long source) {
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
      var destinationStart = Long.parseLong(parts[0]);
      var sourceStart = Long.parseLong(parts[1]);
      var length = Long.parseLong(parts[2]);
      converters.add(new Converter(sourceStart, destinationStart, length));
    }
    i++;
    return new Pair<>(i, converters);
  }

  private static SeedMap parseSeedMap(List<String> input) {
    var seeds =
        Arrays.stream(input.get(0).split(":")[1].trim().split(" "))
            .map(Long::parseLong)
            .collect(Collectors.toList());

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

  private static void convert(List<Long> source, List<Converter> converters) {
    for (int i = 0; i < source.size(); i++) {
      var value = source.get(i);
      for (var converter : converters) {
        var converted = converter.tryConvert(value);
        if (converted.isPresent()) {
          source.set(i, converted.getAsLong());
          break;
        }
      }
    }
  }

  private static Long convert(Long source, List<Converter> converters) {
    for (var converter : converters) {
      var converted = converter.tryConvert(source);
      if (converted.isPresent()) {
        return converted.getAsLong();
      }
    }
    return source;
  }

  private static Long convert(Long source, SeedMap seedMap) {
    source = convert(source, seedMap.seedToSoilConverters());
    source = convert(source, seedMap.soilToFertilizerConverters());
    source = convert(source, seedMap.fertilizerToWaterConverters());
    source = convert(source, seedMap.waterToLightConverters());
    source = convert(source, seedMap.lightToTemperatureConverters());
    source = convert(source, seedMap.temperatureToHumidityConverters());
    source = convert(source, seedMap.humidityToLocationConverters());
    return source;
  }

  public static long Part1(List<String> input) {
    var seedMap = parseSeedMap(input);
    var source = new ArrayList<>(seedMap.seeds());
    convert(source, seedMap.seedToSoilConverters());
    convert(source, seedMap.soilToFertilizerConverters());
    convert(source, seedMap.fertilizerToWaterConverters());
    convert(source, seedMap.waterToLightConverters());
    convert(source, seedMap.lightToTemperatureConverters());
    convert(source, seedMap.temperatureToHumidityConverters());
    convert(source, seedMap.humidityToLocationConverters());
    return source.stream().mapToLong(Long::longValue).min().getAsLong();
  }

  public static long Part2(List<String> input) {
    var seedMap = parseSeedMap(input);
    var min = Long.MAX_VALUE;
    var total = seedMap.seeds().size() / 2;
    for (int i = 0; i < seedMap.seeds().size(); i += 2) {
      var start = seedMap.seeds().get(i);
      var length = seedMap.seeds().get(i + 1) + start;
      System.out.println("starting " + i + " of " + total);
      for (var j = start; j < length; j++) {
        var temp = convert(j, seedMap);
        min = Math.min(temp, min);
      }
      System.out.println("completed " + i + " of " + total);
      System.out.println("min " + min);
    }
    return min;
  }
}
