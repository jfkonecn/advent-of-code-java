package com.advent.of.code.year2023.day10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Day10 {

  private record Point(int x, int y) {}

  private record GraphNode(Point point, List<Point> neighbors) {}

  private record Graph(HashMap<Point, GraphNode> nodes, GraphNode startingPoint) {}

  private static Graph ParseInput(List<String> input) {
    var nodes = new HashMap<Point, GraphNode>();
    GraphNode startingNode = null;
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

          if (i > 0) {
            char north = input.get(i - 1).charAt(j);
            if (north == '|' || north == '7' || north == 'F') {
              neighbors.add(new Point(i - 1, j));
            }
          }
          if (i < input.size() - 1) {
            char south = input.get(i + 1).charAt(j);
            if (south == '|' || south == 'L' || south == 'J') {
              neighbors.add(new Point(i + 1, j));
            }
          }
          if (j < input.get(i).length() - 1) {
            char east = input.get(i).charAt(j + 1);
            if (east == '-' || east == 'L' || east == 'F') {
              neighbors.add(new Point(i, j + 1));
            }
          }
          if (j > 0) {
            char west = input.get(i).charAt(j - 1);
            if (west == '-' || west == 'J' || west == '7') {
              neighbors.add(new Point(i, j - 1));
            }
          }
          startingNode = new GraphNode(new Point(i, j), neighbors);
          nodes.put(startingNode.point(), startingNode);
        } else {
          throw new RuntimeException("Unexpected character: " + c);
        }
      }
    }
    return new Graph(nodes, startingNode);
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
          grid[i][j] = input.get(i).charAt(j);
        } else {
          grid[i][j] = '.';
        }
      }
    }
    printGrid(input, grid);
    for (int i = 0; i < input.size(); i++) {
      for (int j = 0; j < input.get(i).length(); j++) {
        if (grid[i][j] != '.') {
          continue;
        } else if (j > 0 && (grid[i][j - 1] == 'O' || grid[i][j - 1] == 'I')) {
          grid[i][j] = grid[i][j - 1];
        } else {
          if (i == 3 && j == 14) {
            System.out.println("here");
          }
          var leftIsEven = verticalIsEven(grid, 0, j - 1, i);
          var rightIsEven = verticalIsEven(grid, j + 1, input.get(i).length() - 1, i);
          var topIsEven = horizontalIsEven(grid, 0, i - 1, j);
          var bottomIsEven = horizontalIsEven(grid, i + 1, input.size() - 1, j);
          grid[i][j] = leftIsEven || rightIsEven || topIsEven || bottomIsEven ? 'O' : 'I';
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

  private static Boolean horizontalIsEven(
      char[][] grid, int startRowIdx, int endRowIdx, int colIdx) {
    var totalHorizontalPipes = 0;
    for (int k = startRowIdx; k <= endRowIdx; k++) {
      var c = grid[k][colIdx];
      if (c == '-') {
        totalHorizontalPipes++;
      } else if (c == '7') {
        do {
          k++;
        } while (k < endRowIdx && grid[k][colIdx] == '|');
        c = grid[k][colIdx];
        if (c == 'L') {
          totalHorizontalPipes++;
        }
      } else if (c == 'F') {
        do {
          k++;
        } while (k < endRowIdx && grid[k][colIdx] == '|');
        c = grid[k][colIdx];
        if (c == 'J') {
          totalHorizontalPipes++;
        }
      } else if (c == 'L') {
        do {
          k++;
        } while (k < endRowIdx && grid[k][colIdx] == '|');
        c = grid[k][colIdx];
        if (c == '7') {
          totalHorizontalPipes++;
        }
      } else if (c == 'J') {
        do {
          k++;
        } while (k < endRowIdx && grid[k][colIdx] == '|');
        c = grid[k][colIdx];
        if (c == 'F') {
          totalHorizontalPipes++;
        }
      }
    }
    return totalHorizontalPipes % 2 == 0;
  }

  private static Boolean verticalIsEven(char[][] grid, int startColIdx, int endColIdx, int rowIdx) {
    var totalVerticalPipes = 0;
    for (int k = startColIdx; k <= endColIdx; k++) {
      var c = grid[rowIdx][k];
      if (c == '|') {
        totalVerticalPipes++;
      } else if (c == '7') {
        do {
          k++;
        } while (k < endColIdx && grid[rowIdx][k] == '-');
        c = grid[rowIdx][k];
        if (c == 'F') {
          totalVerticalPipes++;
        }
      } else if (c == 'F') {
        do {
          k++;
        } while (k < endColIdx && grid[rowIdx][k] == '-');
        c = grid[rowIdx][k];
        if (c == '7') {
          totalVerticalPipes++;
        }
      } else if (c == 'L') {
        do {
          k++;
        } while (k < endColIdx && grid[rowIdx][k] == '-');
        c = grid[rowIdx][k];
        if (c == 'J') {
          totalVerticalPipes++;
        }
      } else if (c == 'J') {
        do {
          k++;
        } while (k < endColIdx && grid[rowIdx][k] == '-');
        c = grid[rowIdx][k];
        if (c == 'L') {
          totalVerticalPipes++;
        }
      }
    }
    return totalVerticalPipes % 2 == 0;
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
