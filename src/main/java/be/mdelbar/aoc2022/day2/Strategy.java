package be.mdelbar.aoc2022.day2;

public record Strategy(RPS enemy, RPS you) {

    public Result run() {
        return switch (enemy) {
            case ROCK -> switch (you) {
                case ROCK -> Result.DRAW;
                case PAPER -> Result.WIN;
                case SCISSORS -> Result.LOSS;
            };
            case PAPER -> switch (you) {
                case ROCK -> Result.LOSS;
                case PAPER -> Result.DRAW;
                case SCISSORS -> Result.WIN;
            };
            case SCISSORS -> switch (you) {
                case ROCK -> Result.WIN;
                case PAPER -> Result.LOSS;
                case SCISSORS -> Result.DRAW;
            };
        };
    }
}
