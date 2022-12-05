package be.mdelbar.aoc2022.day3;

import be.mdelbar.aoc2022.AOCDay;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Day3 implements AOCDay {

    private static final Logger logger = LoggerFactory.getLogger(Day3.class);

    @Override
    public void part1(byte[] inputBytes) {
        String input = new String(inputBytes, StandardCharsets.UTF_8);
        var lines = input.split("\\n");

        var prioSum = Arrays.stream(lines)
                .map(this::toRucksack)
                .map(this::toCommonLetter)
                .mapToInt(this::toPriority)
                .sum();
        logger.info("Sum: " + prioSum);
    }

    @Override
    public void part2(byte[] inputBytes) {
        String input = new String(inputBytes, StandardCharsets.UTF_8);
        var lines = input.split("\\n");

        int totalPrio = 0;
        for (int lineIndex = 0; lineIndex < lines.length; lineIndex += 3) {
            char common = findCommon(lines[lineIndex], lines[lineIndex + 1], lines[lineIndex + 2]);
            totalPrio += toPriority(common);
        }
        logger.info("Sum: " + totalPrio);
    }


    private Pair<String, String> toRucksack(String line) {
        var left = line.substring(0, line.length() / 2);
        var right = line.substring(line.length() / 2);
        return Pair.of(left, right);
    }

    private char toCommonLetter(Pair<String, String> rucksack) {
        var left = rucksack.getLeft().toCharArray();
        var right = rucksack.getRight().toCharArray();

        for (char c : left) {
            for (char d : right) {
                if (c == d) {
                    return c;
                }
            }
        }
        return '0';
    }

    private char findCommon(String line1, String line2, String line3) {
        for(char c1 : line1.toCharArray()) {
            for(char c2 : line2.toCharArray()) {
                for(char c3 : line3.toCharArray()) {
                    if(c1 == c2 && c2 == c3) {
                        return c1;
                    }
                }
            }
        }
        return '0';
    }

    private int toPriority(char c) {
        var num = Character.isUpperCase(c)
                ? Character.getNumericValue(c) - Character.getNumericValue('A') + 26
                : Character.getNumericValue(c) - Character.getNumericValue('a');
        return num + 1;
    }

}
