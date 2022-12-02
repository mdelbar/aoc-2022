package be.mdelbar.aoc2022.day2;

import lombok.Getter;

@Getter
public enum Result {
    WIN(6),
    DRAW(3),
    LOSS(0);

    private final int score;

    Result(int score) {
        this.score = score;
    }


    public static Result fromLetter(String str) {
        return switch (str) {
            case "X" -> LOSS;
            case "Y" -> DRAW;
            case "Z" -> WIN;
            default -> throw new RuntimeException();
        };
    }


}
