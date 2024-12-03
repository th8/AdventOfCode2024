package nl.th8.adventofcode2024;

import nl.th8.adventofcode2024.utils.PuzzleInputParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DayThreeTest {

    private final PuzzleInputParser puzzleInputParser = mock(PuzzleInputParser.class);

    @Test
    void solvePart1() {
        when(puzzleInputParser.getInputAsString()).thenReturn("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))");
        DayThree day = new DayThree(puzzleInputParser);

        assertEquals(161, day.solvePartOne());
    }

    @Test
    void solvePart2() {
        when(puzzleInputParser.getInputAsString()).thenReturn("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))");
        DayThree day = new DayThree(puzzleInputParser);
        assertEquals(48, day.solvePartTwo());
    }
}