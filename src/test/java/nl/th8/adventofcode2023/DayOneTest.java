package nl.th8.adventofcode2023;

import nl.th8.adventofcode2023.utils.PuzzleInputParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DayOneTest {

    private final PuzzleInputParser puzzleInputParser = mock(PuzzleInputParser.class);

    private DayOne dayOne;

    @BeforeEach
    public void beforeEach() {
        when(puzzleInputParser.getInputAsStringList()).thenReturn(Arrays.asList("3   4",
                "4   3",
                "2   5",
                "1   3",
                "3   9",
                "3   3"));

        dayOne = new DayOne(puzzleInputParser);
    }

    @Test
    void solvePart1() {
        assertEquals(11, dayOne.solvePartOne());
    }

    @Test
    void solvePart2() {
        assertEquals(31, dayOne.solvePartTwo());
    }
}