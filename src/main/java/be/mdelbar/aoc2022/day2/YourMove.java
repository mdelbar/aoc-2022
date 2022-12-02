package be.mdelbar.aoc2022.day2;

public record YourMove(RPS enemy, Result result) {

    public RPS yourMove() {
        return switch (enemy) {
            case ROCK -> switch (result) {
                case WIN -> RPS.PAPER;
                case DRAW -> RPS.ROCK;
                case LOSS -> RPS.SCISSORS;
            };
            case PAPER -> switch (result) {
                case WIN -> RPS.SCISSORS;
                case DRAW -> RPS.PAPER;
                case LOSS -> RPS.ROCK;
            };
            case SCISSORS -> switch (result) {
                case WIN -> RPS.ROCK;
                case DRAW -> RPS.SCISSORS;
                case LOSS -> RPS.PAPER;
            };
        };
    }
}
