package be.mdelbar.aoc2022.day2;

import lombok.Getter;

@Getter
public enum RPS {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    private final int score;

    RPS(int score) {
        this.score = score;
    }

    public static RPS fromLetter(String str) {
        return switch (str) {
            case "A", "X" -> ROCK;
            case "B", "Y" -> PAPER;
            case "C", "Z" -> SCISSORS;
            default -> throw new RuntimeException();
        };
    }

}
