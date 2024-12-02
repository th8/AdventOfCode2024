package nl.th8.adventofcode2023;

import nl.th8.adventofcode2023.utils.PuzzleInputParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DayTwoTest {

    private final PuzzleInputParser puzzleInputParser = mock(PuzzleInputParser.class);

    private DayTwo day;

    @BeforeEach
    public void beforeEach() {
        when(puzzleInputParser.getInputAsStringList()).thenReturn(Arrays.asList(
                "7 6 4 2 1",
                "1 2 7 8 9",
                "9 7 6 2 1",
                "1 3 2 4 5",
                "8 6 4 4 1",
                "1 3 6 7 9"));

        day = new DayTwo(puzzleInputParser);
    }

    @Test
    void solvePart1() {
        assertEquals(2, day.solvePartOne());
    }

    @Test
    void solvePart2() {
        assertEquals(4, day.solvePartTwo());
    }
}