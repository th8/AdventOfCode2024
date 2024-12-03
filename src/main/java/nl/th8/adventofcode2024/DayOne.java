package nl.th8.adventofcode2024;

import nl.th8.adventofcode2024.utils.PuzzleInputParser;

import java.nio.file.Path;
import java.util.*;

public class DayOne implements Day {
    private final PuzzleInputParser puzzleInputParser;
    private final List<Integer> leftList = new ArrayList<>();
    private final List<Integer> rightList = new ArrayList<>();

    //For actual use
    public DayOne() {
        puzzleInputParser = new PuzzleInputParser(Path.of("src", "main", "resources", "input", "1.txt"));
        prepareLists();
    }

    //For Unittesting
    public DayOne(PuzzleInputParser puzzleInputParser) {
        this.puzzleInputParser = puzzleInputParser;
        prepareLists();
    }

    public int solvePartOne() {
        int total = 0;
        for(int i = 0; i < leftList.size(); i++) {
            total += Math.abs(leftList.get(i) - rightList.get(i));
        }
        return total;
    }

    public int solvePartTwo() {
        //Create a lookup table for the amount of occurrences of a certain value in the rightList
        Map<Integer, Integer> rightListLookupTable = new HashMap<>();
        for(Integer i : rightList) {
            rightListLookupTable.compute(i, (key, currentTotal) -> currentTotal == null ? 1 : currentTotal + 1);
        }

        int total = 0;
        for (Integer currentLocationId : leftList) {
            total += (currentLocationId * rightListLookupTable.getOrDefault(currentLocationId, 0));
        }
        return total;
    }

    private void prepareLists() {
        puzzleInputParser.getInputAsStringList().forEach(row -> {
            var locationIds = row.split(" {3}");
            leftList.add(Integer.valueOf(locationIds[0]));
            rightList.add(Integer.valueOf(locationIds[1]));
        });

        leftList.sort(Integer::compareTo);
        rightList.sort(Integer::compareTo);
    }

    public int getDayNumber() {
        return 1;
    }
}
