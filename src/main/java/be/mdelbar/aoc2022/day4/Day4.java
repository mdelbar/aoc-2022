package be.mdelbar.aoc2022.day4;

import be.mdelbar.aoc2022.AOCDay;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Day4 implements AOCDay {

    private static final Logger logger = LoggerFactory.getLogger(Day4.class);

    @Override
    public void part1(byte[] inputBytes) {
        String input = new String(inputBytes, StandardCharsets.UTF_8);
        var lines = input.split("\\n");

        var solution = Arrays.stream(lines)
                .map(this::toAssignment)
                .map(this::toRange)
                .filter(this::fullyContained)
                .count();

        logger.info("Solution: " + solution);
    }

    @Override
    public void part2(byte[] inputBytes) {
        String input = new String(inputBytes, StandardCharsets.UTF_8);
        var lines = input.split("\\n");

        var solution = Arrays.stream(lines)
                .map(this::toAssignment)
                .map(this::toRange)
                .filter(this::hasOverlap)
                .count();

        logger.info("Solution: " + solution);
    }


    private Pair<String, String> toAssignment(String line) {
        var split = StringUtils.split(line, ",");
        return Pair.of(split[0], split[1]);
    }

    private Pair<Range, Range> toRange(Pair<String, String> assignment) {
        var left = assignment.getLeft();
        var right = assignment.getRight();
        return Pair.of(toRange(left), toRange(right));
    }

    private Range toRange(String str) {
        var split = str.split("-");
        return new Range(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }

    private boolean fullyContained(Pair<Range, Range> ranges) {
        var left = ranges.getLeft();
        var right = ranges.getRight();

        return (left.start <= right.start && left.end >= right.end)
            || (right.start <= left.start && right.end >= left.end);
    }

    private boolean hasOverlap(Pair<Range, Range> ranges) {
        var left = ranges.getLeft();
        var right = ranges.getRight();

        return (left.end >= right.start && left.end <= right.end)
            || (right.end >= left.start && right.end <= left.end);
    }


    private record Range(int start, int end) {}

}
