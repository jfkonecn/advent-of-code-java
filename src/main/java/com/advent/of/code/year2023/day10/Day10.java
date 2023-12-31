package com.advent.of.code.year2023.day10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Day10 {

  private record Point(int x, int y) {}

  private record GraphNode(Point point, List<Point> neighbors) {}

  private record Graph(
      HashMap<Point, GraphNode> nodes, GraphNode startingPoint, char startingPointChar) {}

  private static Graph ParseInput(List<String> input) {
    var nodes = new HashMap<Point, GraphNode>();
    GraphNode startingNode = null;
    var startingPointChar = ' ';
    for (int i = 0; i < input.size(); i++) {
      String line = input.get(i);
      for (int j = 0; j < line.length(); j++) {
        char c = line.charAt(j);
        if (c == '|') {
          // vertical pipe
          var neighbors = new ArrayList<Point>();
          neighbors.add(new Point(i - 1, j));
          neighbors.add(new Point(i + 1, j));
          nodes.put(new Point(i, j), new GraphNode(new Point(i, j), neighbors));
        } else if (c == '-') {
          // horizontal pipe
          var neighbors = new ArrayList<Point>();
          neighbors.add(new Point(i, j - 1));
          neighbors.add(new Point(i, j + 1));
          nodes.put(new Point(i, j), new GraphNode(new Point(i, j), neighbors));
        } else if (c == 'L') {
          // 90-degree bend connecting north and east
          var neighbors = new ArrayList<Point>();
          neighbors.add(new Point(i - 1, j));
          neighbors.add(new Point(i, j + 1));
          nodes.put(new Point(i, j), new GraphNode(new Point(i, j), neighbors));
        } else if (c == 'J') {
          // 90-degree bend connecting north and west
          var neighbors = new ArrayList<Point>();
          neighbors.add(new Point(i - 1, j));
          neighbors.add(new Point(i, j - 1));
          nodes.put(new Point(i, j), new GraphNode(new Point(i, j), neighbors));
        } else if (c == '7') {
          // 90-degree bend connecting south and west
          var neighbors = new ArrayList<Point>();
          neighbors.add(new Point(i + 1, j));
          neighbors.add(new Point(i, j - 1));
          nodes.put(new Point(i, j), new GraphNode(new Point(i, j), neighbors));
        } else if (c == 'F') {
          // 90-degree bend connecting south and east
          var neighbors = new ArrayList<Point>();
          neighbors.add(new Point(i + 1, j));
          neighbors.add(new Point(i, j + 1));
          nodes.put(new Point(i, j), new GraphNode(new Point(i, j), neighbors));
        } else if (c == '.') {
          // ground; there is no pipe in this tile
        } else if (c == 'S') {
          // starting position of the animal; there is a pipe on this tile, but your sketch doesn't
          // show what shape the pipe has
          var neighbors = new ArrayList<Point>();

          var hasNorth = false;
          var hasSouth = false;
          var hasEast = false;
          var hasWest = false;
          if (i > 0) {
            char north = input.get(i - 1).charAt(j);
            if (north == '|' || north == '7' || north == 'F') {
              neighbors.add(new Point(i - 1, j));
              hasNorth = true;
            }
          }
          if (i < input.size() - 1) {
            char south = input.get(i + 1).charAt(j);
            if (south == '|' || south == 'L' || south == 'J') {
              neighbors.add(new Point(i + 1, j));
              hasSouth = true;
            }
          }
          if (j < input.get(i).length() - 1) {
            char east = input.get(i).charAt(j + 1);
            if (east == '-' || east == 'L' || east == 'F') {
              neighbors.add(new Point(i, j + 1));
              hasEast = true;
            }
          }
          if (j > 0) {
            char west = input.get(i).charAt(j - 1);
            if (west == '-' || west == 'J' || west == '7') {
              neighbors.add(new Point(i, j - 1));
              hasWest = true;
            }
          }
          if (hasNorth && hasSouth) {
            startingPointChar = '|';
          } else if (hasEast && hasWest) {
            startingPointChar = '-';
          } else if (hasNorth && hasEast) {
            startingPointChar = 'L';
          } else if (hasNorth && hasWest) {
            startingPointChar = 'J';
          } else if (hasSouth && hasEast) {
            startingPointChar = 'F';
          } else if (hasSouth && hasWest) {
            startingPointChar = '7';
          } else {
            throw new RuntimeException("Unexpected starting point: " + c);
          }
          startingNode = new GraphNode(new Point(i, j), neighbors);
          nodes.put(startingNode.point(), startingNode);
        } else {
          throw new RuntimeException("Unexpected character: " + c);
        }
      }
    }
    return new Graph(nodes, startingNode, startingPointChar);
  }

  public static int Part1(List<String> input) {
    var graph = ParseInput(input);
    var steps = getPath(graph).size();
    return steps / 2;
  }

  private static HashSet<Point> getPath(Graph graph) {
    var path = new HashSet<Point>();
    var currentNode = graph.startingPoint();
    var previousNode = currentNode;
    do {
      var neighbors = currentNode.neighbors();
      for (var neighbor : neighbors) {
        if (neighbor.equals(previousNode.point())) {
          continue;
        }
        previousNode = currentNode;
        currentNode = graph.nodes().get(neighbor);
        path.add(currentNode.point());
        break;
      }
    } while (currentNode != graph.startingPoint());
    return path;
  }

  public static int Part2(List<String> input) {
    var graph = ParseInput(input);
    var path = getPath(graph);
    var grid = new char[input.size()][input.get(0).length()];
    for (int i = 0; i < input.size(); i++) {
      for (int j = 0; j < input.get(i).length(); j++) {
        if (path.contains(new Point(i, j))) {
          var c = input.get(i).charAt(j);
          if (c == 'S') {
            grid[i][j] = graph.startingPointChar();
          } else {
            grid[i][j] = c;
          }
        } else {
          grid[i][j] = '.';
        }
      }
    }
    printGrid(input, grid);
    for (int i = 0; i < input.size(); i++) {
      var isContained = false;
      for (int j = 0; j < input.get(i).length(); j++) {
        if (grid[i][j] == '.') {
          if (isContained) {
            grid[i][j] = 'I';
          } else {
            grid[i][j] = 'O';
          }
        } else if (grid[i][j] == '|' || grid[i][j] == 'L' || grid[i][j] == 'J') {
          isContained = !isContained;
        }
      }
    }
    System.out.println();
    System.out.println();
    printGrid(input, grid);
    System.out.println();
    System.out.println();
    var totalIs = 0;
    for (int i = 0; i < input.size(); i++) {
      for (int j = 0; j < input.get(i).length(); j++) {
        if (grid[i][j] == 'I') {
          totalIs++;
        }
      }
    }
    return totalIs;
  }

  private static void printGrid(List<String> input, char[][] grid) {
    for (int i = 0; i < input.size(); i++) {
      for (int j = 0; j < input.get(i).length(); j++) {
        System.out.print(grid[i][j]);
      }
      System.out.println();
    }
  }
}
