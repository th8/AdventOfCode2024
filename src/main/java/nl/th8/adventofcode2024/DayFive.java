package nl.th8.adventofcode2024;

import nl.th8.adventofcode2024.utils.PuzzleInputParser;

import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayFive implements Day {

    private final PuzzleInputParser puzzleInputParser;
    private Map<Integer, Set<Integer>> pageRules = new HashMap<>();
    private List<List<Integer>> printInstructions = new ArrayList<>();

    //For actual use
    public DayFive() {
        puzzleInputParser = new PuzzleInputParser(Path.of("src", "main", "resources", "input", "5.txt"));
        prepareInput();
    }

    //For unittesting
    public DayFive(PuzzleInputParser puzzleInputParser) {
        this.puzzleInputParser = puzzleInputParser;
        prepareInput();
    }

    @Override
    public long solvePartOne() {
        List<List<Integer>> validInstructions = new ArrayList<>();
        for (List<Integer> printInstruction : printInstructions) {
            Set<Integer> alreadyPrinted = new HashSet<>();
            boolean isValidInstruction = true;
            for(int page : printInstruction) {
                if(pageRules.getOrDefault(page, Collections.emptySet()).stream().anyMatch(alreadyPrinted::contains)) {
                    isValidInstruction = false;
                    break;
                }
                alreadyPrinted.add(page);
            }
            if(isValidInstruction)
                validInstructions.add(printInstruction);
        }

        int total = 0;
        for(List<Integer> printInstruction : validInstructions) {
            total += printInstruction.get(printInstruction.size() / 2);
        }

        return total;
    }

    @Override
    public long solvePartTwo() {
        List<List<Integer>> fixedInstructions = new ArrayList<>();
        for (List<Integer> printInstruction : printInstructions) {
            fixInvalidInstructions(printInstruction).ifPresent(fixedInstructions::add);
        }

        int total = 0;
        for(List<Integer> printInstruction : fixedInstructions) {
            total += printInstruction.get(printInstruction.size() / 2);
        }

        return total;
    }

    private Optional<List<Integer>> fixInvalidInstructions(List<Integer> printInstruction) {
        Set<Integer> alreadyPrinted = new HashSet<>();
        for(int page : printInstruction) {
            if(pageRules.getOrDefault(page, Collections.emptySet()).stream().anyMatch(alreadyPrinted::contains)) {
                return Optional.of(fixInvalidInstruction(printInstruction, page));
            }
            alreadyPrinted.add(page);
        }
        return Optional.empty();
    }

    private List<Integer> fixInvalidInstruction(List<Integer> instructionToFix, int invalidPage) {
        int newIndex = instructionToFix.indexOf(
                instructionToFix.stream()
                .filter(i -> pageRules.get(invalidPage).contains(i))
                .findFirst().orElseThrow()
        );

        instructionToFix.remove((Integer) invalidPage);
        instructionToFix.add(newIndex, invalidPage);
        var recursiveFix = fixInvalidInstructions(instructionToFix);
        return recursiveFix.orElse(instructionToFix);
    }

    private void prepareInput() {
        List<String> input = puzzleInputParser.getInputAsStringList();
        input.forEach(line -> {
            if(line.contains("|")) {
                String[] split = line.split("\\|");
                if(pageRules.containsKey(Integer.parseInt(split[0])))
                    pageRules.get(Integer.parseInt(split[0])).add(Integer.parseInt(split[1]));
                else {
                    pageRules.put(Integer.parseInt(split[0]), new HashSet<>(Set.of(Integer.parseInt(split[1]))));
                }
            } else if(line.contains(",")) {
                String[] split = line.split(",");
                List<Integer> instruction = new ArrayList<>();
                for(String s : split) {
                    instruction.add(Integer.parseInt(s));
                }
                printInstructions.add(instruction);
            }
        });
    }

    @Override
    public int getDayNumber() {
        return 5;
    }
}
