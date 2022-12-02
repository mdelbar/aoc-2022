package be.mdelbar.aoc2022.day2;

import be.mdelbar.aoc2022.AOCDay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Day2 implements AOCDay {

    private static final Logger logger = LoggerFactory.getLogger(Day2.class);

    @Override
    public void part1(byte[] inputBytes) {
        String input = new String(inputBytes, StandardCharsets.UTF_8);
        var rounds = input.split("\\n");

        var totalScore = Arrays.stream(rounds)
                .map(this::toStrategy)
                .mapToInt(this::toScore)
                .sum();
        logger.info("Total score: " + totalScore);
    }

    @Override
    public void part2(byte[] inputBytes) {
        String input = new String(inputBytes, StandardCharsets.UTF_8);
        var rounds = input.split("\\n");

        var totalScore = Arrays.stream(rounds)
                .map(this::toYourMove)
                .map(this::toStrategy)
                .mapToInt(this::toScore)
                .sum();
        logger.info("Total score: " + totalScore);
    }


    private Strategy toStrategy(String line) {
        var split = line.split(" ");
        return new Strategy(RPS.fromLetter(split[0]), RPS.fromLetter(split[1]));
    }

    private Strategy toStrategy(YourMove yourMove) {
        return new Strategy(yourMove.enemy(), yourMove.yourMove());
    }

    private YourMove toYourMove(String line) {
        var split = line.split(" ");
        return new YourMove(RPS.fromLetter(split[0]), Result.fromLetter(split[1]));
    }

    private int toScore(Strategy strat) {
        var result = strat.run();
        return result.getScore() + strat.you().getScore();
    }
}
