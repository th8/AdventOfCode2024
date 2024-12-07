package nl.th8.adventofcode2024;

import nl.th8.adventofcode2024.utils.PuzzleInputParser;

import java.nio.file.Path;
import java.util.*;

public class DaySix implements Day {

    public static final String VISITED = "X";
    public static final String OBSTACLE = "#";
    public static final String START = "^";
    private final PuzzleInputParser puzzleInputParser;
    private String[][] map;
    private int startX;
    private int startY;

    //For actual use
    public DaySix() {
        puzzleInputParser = new PuzzleInputParser(Path.of("src", "main", "resources", "input", "6.txt"));
    }

    //For unittesting
    public DaySix(PuzzleInputParser puzzleInputParser) {
        this.puzzleInputParser = puzzleInputParser;
    }

    @Override
    public long solvePartOne() {
        prepareInput();

        boolean isInBound = true;
        Direction currentDirection = Direction.NORTH;
        int currentX = startX;
        int currentY = startY;

        while(isInBound) {
            try {
                String moveTo = map[currentY + currentDirection.speedY][currentX + currentDirection.speedX];
                if(Objects.equals(moveTo, OBSTACLE)) {
                    currentDirection = currentDirection.next();
                } else {
                    currentX += currentDirection.speedX;
                    currentY += currentDirection.speedY;
                    map[currentY][currentX] = VISITED;
                }
            } catch (IndexOutOfBoundsException e) {
                isInBound = false;
            }
        }

        return Math.toIntExact(Arrays.stream(map)
                    .mapToLong(row -> Arrays.stream(row)
                            .filter(field -> field.equals(VISITED))
                            .count()
                    )
                    .sum());
    }

    @Override
    public long solvePartTwo() {
        //We want to explore the default path from part one to try our new obstacles on. Fields not visited on that route are irrelevant as we may only place one obstacle.
        solvePartOne();

        int total = 0;
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                if(map[i][j].equals(VISITED)) {
                    map[i][j] = OBSTACLE;
                    if(checkIsLoop())
                        total++;
                    map[i][j] = VISITED;
                }
            }
        }

        return total;
    }

    private boolean checkIsLoop() {
        Set<String> obstacleHit = new HashSet<>();
        Direction currentDirection = Direction.NORTH;
        int currentX = startX;
        int currentY = startY;

        while(true) {
            try {
                String moveTo = map[currentY + currentDirection.speedY][currentX + currentDirection.speedX];
                if(Objects.equals(moveTo, OBSTACLE)) {
                    //If we hit an obstacle multiple times whilst walking in the same direction, we assume we're in a loop.
                    if(obstacleHit.contains(makeCoordinate(currentX + currentDirection.speedY, currentY + currentDirection.speedY, currentDirection))) {
                        return true;
                    }
                    obstacleHit.add(makeCoordinate(currentX + currentDirection.speedY, currentY + currentDirection.speedY, currentDirection));
                    currentDirection = currentDirection.next();
                } else {
                    currentX += currentDirection.speedX;
                    currentY += currentDirection.speedY;
                }
            } catch (IndexOutOfBoundsException e) {
                return false;
            }
        }
    }


    /**
     * Create a unique identifier for hitting an obstacle whilst walking in a specific direction
     * @param x coordinate of the obstacle
     * @param y coordinate of the obstacle
     * @param direction the guard is walking in whilst hitting this object
     * @return a unique identifier
     */
    private String makeCoordinate(int x, int y, Direction direction) {
        return "X%dY%dD%s".formatted(x, y, direction.name());
    }

    private void prepareInput() {
        List<String> input = puzzleInputParser.getInputAsStringList();
        map = new String[input.size()][input.getFirst().length()];
        for (int i = 0; i < input.size(); i++) {
            if(input.get(i).contains(START)) {
                startX = input.get(i).indexOf(START);
                startY = i;
            }
            map[i] = input.get(i).split("");
        }
        map[startY][startX] = VISITED;
    }

    @Override
    public int getDayNumber() {
        return 6;
    }

    private enum Direction {
        NORTH(0, -1),
        EAST(1, 0),
        SOUTH(0, 1),
        WEST(-1, 0);

        private final int speedX;
        private final int speedY;

        Direction(int speedX, int speedY) {
            this.speedX = speedX;
            this.speedY = speedY;
        }

        public Direction next() {
            return switch (this) {
                case NORTH -> EAST;
                case EAST -> SOUTH;
                case SOUTH -> WEST;
                case WEST -> NORTH;
            };
        }
    }
}
