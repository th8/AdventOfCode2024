package nl.th8.adventofcode2023;

import nl.th8.adventofcode2023.utils.PuzzleInputParser;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DayTwo implements Day {

    private final PuzzleInputParser puzzleInputParser;
    private List<List<Integer>> reports;

    //For actual use
    public DayTwo() {
        puzzleInputParser = new PuzzleInputParser(Path.of("src", "main", "resources", "input", "2.txt"));
        prepareInput();
    }

    //For unittesting
    public DayTwo(PuzzleInputParser puzzleInputParser) {
        this.puzzleInputParser = puzzleInputParser;
        prepareInput();
    }

    @Override
    public int solvePartOne() {
        int safeReports = 0;
        for (List<Integer> report : reports) {
            boolean isSafe = isSafe(report);

            if (isSafe)
                safeReports++;
        }
        return safeReports;
    }

    @Override
    public int solvePartTwo() {
        int safeReports = 0;
        for (List<Integer> report : reports) {
            boolean isSafe = isSafe(report);

            if (isSafe || applyProblemDampner(report))
                safeReports++;
        }
        return safeReports;
    }

    private boolean isSafe(List<Integer> report) {
        UpOrDown isGoingUpOrDown = null;

        for(int i = 0; i < report.size()-1; i++) {
            int difference = report.get(i) - report.get(i+1);

            if(isGoingUpOrDown != UpOrDown.DOWN && difference >= 1 && difference < 4) {
                isGoingUpOrDown = UpOrDown.UP;
            } else if (isGoingUpOrDown != UpOrDown.UP && difference <= -1 && difference > -4) {
                isGoingUpOrDown = UpOrDown.DOWN;
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean applyProblemDampner(List<Integer> report) {
        for(int i = 0; i < report.size(); i++) {
            List<Integer> dampenedReport = new ArrayList<>(report);
            dampenedReport.remove(i);
            if(isSafe(dampenedReport))
                return true;
        }
        return false;
    }

    private void prepareInput() {
        this.reports = puzzleInputParser.getInputAsStringList().stream()
                .map(report -> report.split(" "))
                .map(report -> Arrays.stream(report)
                        .map(Integer::valueOf)
                        .toList())
                .toList();
    }

    @Override
    public int getDayNumber() {
        return 2;
    }

    private enum UpOrDown {
        UP,
        DOWN,
    }
}
