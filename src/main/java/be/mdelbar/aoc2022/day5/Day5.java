package be.mdelbar.aoc2022.day5;

import be.mdelbar.aoc2022.AOCDay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Day5 implements AOCDay {

    private static final Logger logger = LoggerFactory.getLogger(Day5.class);

    @Override
    public void part1(byte[] inputBytes) {
        String input = new String(inputBytes, StandardCharsets.UTF_8);
        var lines = input.split("\\n");
        var moves = Arrays.stream(lines)
                .skip(10) // Skip stack input
                .map(this::toMove)
                .toList();

        List<Stack<Character>> stacks = new ArrayList<>();
        stacks.add(createStack("RGJBTVZ"));
        stacks.add(createStack("JRVL"));
        stacks.add(createStack("SQF"));
        stacks.add(createStack("ZHNLFVQG"));
        stacks.add(createStack("RQTJCSMW"));
        stacks.add(createStack("SWTCHF"));
        stacks.add(createStack("DZCVFNJ"));
        stacks.add(createStack("LGZDWRFQ"));
        stacks.add(createStack("JBWVP"));

        execute(stacks, moves);

        var topCrates = stacks.stream()
                .map(Stack::lastElement)
                .map(Object::toString)
                .collect(Collectors.joining());
        logger.info("Solution: " + topCrates);
    }

    @Override
    public void part2(byte[] inputBytes) {
        String input = new String(inputBytes, StandardCharsets.UTF_8);
        var lines = input.split("\\n");
        var moves = Arrays.stream(lines)
                .skip(10) // Skip stack input
                .map(this::toMove)
                .toList();

        List<Stack<Character>> stacks = new ArrayList<>();
        stacks.add(createStack("RGJBTVZ"));
        stacks.add(createStack("JRVL"));
        stacks.add(createStack("SQF"));
        stacks.add(createStack("ZHNLFVQG"));
        stacks.add(createStack("RQTJCSMW"));
        stacks.add(createStack("SWTCHF"));
        stacks.add(createStack("DZCVFNJ"));
        stacks.add(createStack("LGZDWRFQ"));
        stacks.add(createStack("JBWVP"));

        execute2(stacks, moves);

        var topCrates = stacks.stream()
                .map(Stack::lastElement)
                .map(Object::toString)
                .collect(Collectors.joining());
        logger.info("Solution: " + topCrates);
    }


    private Move toMove(String line) {
        var split = line.split(" ");
        var amount = Integer.parseInt(split[1]);
        var from = Integer.parseInt(split[3]);
        var to = Integer.parseInt(split[5]);

        return new Move(amount, from, to);
    }

    private Stack<Character> createStack(String str) {
        Stack<Character> stack = new Stack<>();
        for(char c : str.toCharArray()) {
            stack.push(c);
        }
        return stack;
    }

    private void execute(List<Stack<Character>> stacks, List<Move> moves) {
        for(Move move : moves) {
            for(int i = 0; i < move.amount; i++) {
                var crate = stacks.get(move.from - 1).pop();
                stacks.get(move.to - 1).push(crate);
            }
        }
    }

    private void execute2(List<Stack<Character>> stacks, List<Move> moves) {
        for(Move move : moves) {
            Stack<Character> crates = new Stack<>();
            for(int i = 0; i < move.amount; i++) {
                crates.push(stacks.get(move.from - 1).pop());
            }
            while(!crates.isEmpty()) {
                stacks.get(move.to - 1).push(crates.pop());
            }
        }
    }

    private record Move(int amount, int from, int to) {}

}
