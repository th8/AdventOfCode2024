package nl.th8.adventofcode2024;

import nl.th8.adventofcode2024.utils.PuzzleInputParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DaySevenTest {

    private final PuzzleInputParser puzzleInputParser = mock(PuzzleInputParser.class);

    @Test
    void solvePart1() {
        when(puzzleInputParser.getInputAsStringList()).thenReturn(List.of(
                "190: 10 19",
                "3267: 81 40 27",
                "83: 17 5",
                "156: 15 6",
                "7290: 6 8 6 15",
                "161011: 16 10 13",
                "192: 17 8 14",
                "21037: 9 7 18 13",
                "292: 11 6 16 20"
        ));
        Day day = new DaySeven(puzzleInputParser);

        assertEquals(3749, day.solvePartOne());
    }

    @Test
    void solvePart2() {
        when(puzzleInputParser.getInputAsStringList()).thenReturn(List.of(
                "190: 10 19",
                "3267: 81 40 27",
                "83: 17 5",
                "156: 15 6",
                "7290: 6 8 6 15",
                "161011: 16 10 13",
                "192: 17 8 14",
                "21037: 9 7 18 13",
                "292: 11 6 16 20"
        ));
        Day day = new DaySeven(puzzleInputParser);

        assertEquals(11387, day.solvePartTwo());
    }
}