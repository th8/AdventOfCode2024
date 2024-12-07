package nl.th8.adventofcode2024;

import nl.th8.adventofcode2024.utils.PuzzleInputParser;

import java.nio.file.Path;
import java.util.*;

public class DaySeven implements Day {

    private final PuzzleInputParser puzzleInputParser;
    private final List<Calibration> calibrations = new ArrayList<>();

    //For actual use
    public DaySeven() {
        puzzleInputParser = new PuzzleInputParser(Path.of("src", "main", "resources", "input", "7.txt"));
        prepareInput();
    }

    //For unittesting
    public DaySeven(PuzzleInputParser puzzleInputParser) {
        this.puzzleInputParser = puzzleInputParser;
    }

    @Override
    public long solvePartOne() {
        prepareInput();

        long total = 0;
        for(var calibration : calibrations) {
            if(isSolvable(calibration.goal, 0, calibration.values)) {
                total += calibration.goal;
            }
        }
        return total;
    }

    @Override
    public long solvePartTwo() {
        prepareInput();

        long total = 0;
        for(var calibration : calibrations) {
            if(isSolvableWithConcat(calibration.goal, 0, calibration.values)) {
                total += calibration.goal;
            }
        }
        return total;
    }

    private boolean isSolvable(long valueToMake, long currentTotal, Deque<Long> valuesToCompute) {
        if(valuesToCompute.isEmpty()) {
            return valueToMake == currentTotal;
        }

        long nextValueToCompute = valuesToCompute.pop();
        return isSolvable(valueToMake, currentTotal + nextValueToCompute, new ArrayDeque<>(valuesToCompute))
                || isSolvable(valueToMake, currentTotal * nextValueToCompute, new ArrayDeque<>(valuesToCompute));
    }

    private boolean isSolvableWithConcat(long valueToMake, long currentTotal, Deque<Long> valuesToCompute) {
        if(valuesToCompute.isEmpty()) {
            return valueToMake == currentTotal;
        }

        long nextValueToCompute = valuesToCompute.pop();
        return isSolvableWithConcat(valueToMake, currentTotal + nextValueToCompute, new ArrayDeque<>(valuesToCompute))
                || isSolvableWithConcat(valueToMake, currentTotal * nextValueToCompute, new ArrayDeque<>(valuesToCompute))
                || isSolvableWithConcat(valueToMake, Long.parseLong(currentTotal + String.valueOf(nextValueToCompute)), new ArrayDeque<>(valuesToCompute));
    }

    private void prepareInput() {
        List<String> input = puzzleInputParser.getInputAsStringList();
        calibrations.clear();

        input.forEach(line -> {
            var split = line.split("(: )|[ ]");
            calibrations.add(new Calibration(Long.parseLong(split[0]), new ArrayDeque<>(Arrays.stream(split).skip(1).map(Long::parseLong).toList())));
        });
    }

    @Override
    public int getDayNumber() {
        return 5;
    }

    private record Calibration(long goal, Deque<Long> values) {}
}
