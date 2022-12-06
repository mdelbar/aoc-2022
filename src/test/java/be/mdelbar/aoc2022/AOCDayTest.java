package be.mdelbar.aoc2022;

import be.mdelbar.aoc2022.day6.Day6;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class AOCDayTest {

    private static final String INPUT = "src/test/resources/day6/input.txt";

    private final AOCDay day = new Day6();


    @Test
    void part1() throws Exception {
        day.part1(readInput());
    }

    @Test
    void part2() throws Exception {
        day.part2(readInput());
    }


    private byte[] readInput() throws IOException {
        return Files.readAllBytes(Path.of(INPUT));
    }

}
