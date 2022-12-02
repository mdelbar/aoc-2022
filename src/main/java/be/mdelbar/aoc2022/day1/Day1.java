package be.mdelbar.aoc2022.day1;

import be.mdelbar.aoc2022.AOCDay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Day1 implements AOCDay {

    private static final Logger logger = LoggerFactory.getLogger(Day1.class);

    @Override
    public void part1(byte[] inputBytes) {
        String input = new String(inputBytes, StandardCharsets.UTF_8);
        var elves = input.split("\\n\\n");
        int maxCalories = 0;
        for(var elf : elves) {
            int calories = countCalories(elf);
            if(calories > maxCalories) {
                maxCalories = calories;
            }
        }
        logger.info("Max calories: " + maxCalories);
    }

    @Override
    public void part2(byte[] inputBytes) {
        String input = new String(inputBytes, StandardCharsets.UTF_8);
        var elves = input.split("\\n\\n");
        int top1 = 0;
        int top2 = 0;
        int top3 = 0;
        for(var elf : elves) {
            int calories = countCalories(elf);
            if(calories > top1) {
                top3 = top2;
                top2 = top1;
                top1 = calories;
            }
            else if(calories > top2) {
                top3 = top2;
                top2 = calories;
            }
            else if(calories > top3) {
                top3 = calories;
            }
        }
        logger.info("Number 1: " + top1);
        logger.info("Number 2: " + top2);
        logger.info("Number 3: " + top3);
        logger.info("Sum: " + (top1 + top2 + top3));
    }


    private int countCalories(String elf) {
        var lines = elf.split("\\n");
        return Arrays.stream(lines)
                .mapToInt(Integer::parseInt)
                .sum();
    }
}
