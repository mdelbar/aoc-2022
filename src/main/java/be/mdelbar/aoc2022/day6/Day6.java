package be.mdelbar.aoc2022.day6;

import be.mdelbar.aoc2022.AOCDay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

public class Day6 implements AOCDay {

    private static final Logger logger = LoggerFactory.getLogger(Day6.class);

    @Override
    public void part1(byte[] inputBytes) {
        String input = new String(inputBytes, StandardCharsets.UTF_8);

        logger.info("Marker: " + findMarkerIndex(input));
    }

    private int findMarkerIndex(String input) {
        for(int i = 0; i < input.length() - 4; i++) {
            String substring = input.substring(i, i + 4);
            if(allCharsDifferent(substring)) {
                return i + 4;
            }
        }
        return -1;
    }

    private boolean allCharsDifferent(String substring) {
        char[] chars = substring.toCharArray();
        for(int i = 0; i < chars.length; i++) {
            for(int j = i + 1; j < chars.length; j++) {
                if(chars[i] == chars[j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void part2(byte[] inputBytes) {
        String input = new String(inputBytes, StandardCharsets.UTF_8);

        logger.info("Message: " + findMessageIndex(input));
    }

    private int findMessageIndex(String input) {
        for(int i = 0; i < input.length() - 14; i++) {
            String substring = input.substring(i, i + 14);
            if(allCharsDifferent(substring)) {
                return i + 14;
            }
        }
        return -1;
    }
}
