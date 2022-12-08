package be.mdelbar.aoc2022.day8;

import be.mdelbar.aoc2022.AOCDay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

public class Day8 implements AOCDay {

    private static final Logger logger = LoggerFactory.getLogger(Day8.class);

    @Override
    public void part1(byte[] inputBytes) {
        String input = new String(inputBytes, StandardCharsets.UTF_8);
        int[][] grid = makeGrid(input);

        var solution = countVisibleTrees(grid);
        
        logger.info("Solution: " + solution);
    }

    private int countVisibleTrees(int[][] grid) {
        int visibleTrees = 0;
        for(int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid.length; col++) {
                if(row == 0 || row == grid.length - 1
                || col == 0 || col == grid.length - 1) {
                    visibleTrees++;
                }
                else if(isTreeVisible(grid, row, col)) {
                    visibleTrees++;
                }
            }
        }
        return visibleTrees;
    }

    private boolean isTreeVisible(int[][] grid, int row, int col) {
        return isTreeVisibleUp(grid, row, col)
                || isTreeVisibleDown(grid, row, col)
                || isTreeVisibleLeft(grid, row, col)
                || isTreeVisibleRight(grid, row, col);
    }

    private boolean isTreeVisibleUp(int[][] grid, int row, int col) {
        int tree = grid[row][col];
        for(int i = 0; i < row; i++) {
            if(grid[i][col] >= tree) {
                return false;
            }
        }
        return true;
    }

    private boolean isTreeVisibleDown(int[][] grid, int row, int col) {
        int tree = grid[row][col];
        for(int i = row + 1; i < grid.length; i++) {
            if(grid[i][col] >= tree) {
                return false;
            }
        }
        return true;
    }

    private boolean isTreeVisibleLeft(int[][] grid, int row, int col) {
        int tree = grid[row][col];
        for(int j = 0; j < col; j++) {
            if(grid[row][j] >= tree) {
                return false;
            }
        }
        return true;
    }

    private boolean isTreeVisibleRight(int[][] grid, int row, int col) {
        int tree = grid[row][col];
        for(int j = col + 1; j < grid.length; j++) {
            if(grid[row][j] >= tree) {
                return false;
            }
        }
        return true;
    }

    private int[][] makeGrid(String input) {
        String[] lines = input.split("\\n");
        int[][] grid = new int[lines.length][lines.length];
        for(int row = 0; row < lines.length; row++) {
            for(int col = 0; col < lines.length; col++) {
                grid[row][col] = Integer.parseInt(String.valueOf(lines[row].charAt(col)));
            }
        }
        return grid;
    }

    @Override
    public void part2(byte[] inputBytes) {
        String input = new String(inputBytes, StandardCharsets.UTF_8);
        int[][] grid = makeGrid(input);

        var solution = getHighestScenicScore(grid);

        logger.info("Solution: " + solution);
    }

    private int getHighestScenicScore(int[][] grid) {
        int highestScore = 0;
        for(int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid.length; col++) {
                int score = calculateScenicScore(grid, row, col);
                if(score > highestScore) {
                    highestScore = score;
                }
            }
        }
        return highestScore;
    }

    private int calculateScenicScore(int[][] grid, int row, int col) {
        return scoreUp(grid, row, col)
                * scoreDown(grid, row, col)
                * scoreLeft(grid, row, col)
                * scoreRight(grid, row, col);
    }

    private int scoreUp(int[][] grid, int row, int col) {
        int tree = grid[row][col];
        int score = 0;
        for(int i = row - 1; i >= 0; i--) {
            score++;
            if(grid[i][col] >= tree) {
                return score;
            }
        }
        return score;
    }

    private int scoreDown(int[][] grid, int row, int col) {
        int tree = grid[row][col];
        int score = 0;
        for(int i = row + 1; i < grid.length; i++) {
            score++;
            if(grid[i][col] >= tree) {
                return score;
            }
        }
        return score;
    }

    private int scoreLeft(int[][] grid, int row, int col) {
        int tree = grid[row][col];
        int score = 0;
        for(int j = col - 1; j >= 0; j--) {
            score++;
            if(grid[row][j] >= tree) {
                return score;
            }
        }
        return score;
    }

    private int scoreRight(int[][] grid, int row, int col) {
        int tree = grid[row][col];
        int score = 0;
        for(int j = col + 1; j < grid.length; j++) {
            score++;
            if(grid[row][j] >= tree) {
                return score;
            }
        }
        return score;
    }

}
