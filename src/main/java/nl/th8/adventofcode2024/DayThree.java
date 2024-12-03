package nl.th8.adventofcode2024;

import nl.th8.adventofcode2024.utils.PuzzleInputParser;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayThree implements Day {

    private static final String DO_INSTRUCTION = "do()";
    private static final String DONT_INSTRUCTION = "don't()";
    private final PuzzleInputParser puzzleInputParser;
    private String memory;

    //For actual use
    public DayThree() {
        puzzleInputParser = new PuzzleInputParser(Path.of("src", "main", "resources", "input", "3.txt"));
        prepareInput();
    }

    //For unittesting
    public DayThree(PuzzleInputParser puzzleInputParser) {
        this.puzzleInputParser = puzzleInputParser;
        prepareInput();
    }

    @Override
    public int solvePartOne() {
        Pattern pattern = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}\\)");
        return runInstructions(pattern);
    }

    @Override
    public int solvePartTwo() {
        Pattern pattern = Pattern.compile("(mul\\([0-9]{1,3},[0-9]{1,3}\\))|do\\(\\)|don't\\(\\)");
        return runInstructions(pattern);
    }

    private int runInstructions(Pattern pattern) {
        Matcher matcher = pattern.matcher(memory);

        int total = 0;
        boolean doMultiplication = true;
        while (matcher.find()) {
            String group = matcher.group();
            if(DO_INSTRUCTION.equals(group))
                doMultiplication = true;
            else if(DONT_INSTRUCTION.equals(group))
                doMultiplication = false;
            else if(doMultiplication) {
                String[] multiplicationInstruction = group.split("[(,)]");
                total += Integer.parseInt(multiplicationInstruction[1]) * Integer.parseInt(multiplicationInstruction[2]);
            }
        }
        return total;
    }

    private void prepareInput() {
        this.memory = puzzleInputParser.getInputAsString();
    }

    @Override
    public int getDayNumber() {
        return 3;
    }
}
