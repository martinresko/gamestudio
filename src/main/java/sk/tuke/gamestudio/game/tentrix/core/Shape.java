package sk.tuke.gamestudio.game.tentrix.core;


import sk.tuke.gamestudio.game.tentrix.enums.ShapeType;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import static sk.tuke.gamestudio.game.tentrix.enums.ShapeType.*;

public class Shape {

    private ShapeType type;
    private String[] pattern;
    private int[] numbers;
    private int orientation;

    private static final Integer[] NUMBERS_WITHIN_BLOCKS = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    private static final int[][] O_COORDINATES = {{0, 0, 0, 1, 1, 0, 1, 1}};
    private static final int[][] I_COORDINATES = {{0, 0, 0, 1, 0, 2, 0, 3}, {0, 0, 1, 0, 2, 0, 3, 0}};
    private static final int[][] S_COORDINATES = {{0, 1, 0, 2, 1, 0, 1, 1}, {1, 1, 2, 1, 0, 0, 1, 0}};
    private static final int[][] Z_COORDINATES = {{0, 0, 0, 1, 1, 1, 1, 2}, {0, 1, 1, 0, 1, 1, 2, 0}};
    private static final int[][] L_COORDINATES = {{0, 2, 1, 0, 1, 1, 1, 2}, {2, 1, 0, 0, 1, 0, 2, 0}, {1, 0, 0, 2, 0, 1, 0, 0}, {0, 0, 2, 1, 1, 1, 0, 1}};
    private static final int[][] J_COORDINATES = {{0, 0, 1, 0, 1, 1, 1, 2}, {0, 1, 0, 0, 1, 0, 2, 0}, {1, 2, 0, 2, 0, 1, 0, 0}, {2, 0, 2, 1, 1, 1, 0, 1}};
    private static final int[][] T_COORDINATES = {{0, 0, 0, 1, 0, 2, 1, 1}, {0, 1, 1, 1, 2, 1, 1, 0}, {1, 2, 1, 1, 1, 0, 0, 1}, {2, 0, 1, 0, 0, 0, 1, 1}};
    private static final int[][][] ALL_BLOCKS_COORDINATES = {O_COORDINATES, I_COORDINATES, Z_COORDINATES, S_COORDINATES, J_COORDINATES, L_COORDINATES, T_COORDINATES};

    private Shape() {
    }

    public static Shape create() {

        Shape shape = new Shape();

        Random random = new Random();
        shape.type = randomshape();
        shape.numbers = new int[4];
        shape.pattern = new String[2];

        // generating 4 non-repeating numbers for the numbers within the Tetromino (only 4 numbers needed)
        Collections.shuffle(Arrays.asList(NUMBERS_WITHIN_BLOCKS));
        for (int i = 0; i < 4; i++) {
            shape.numbers[i] = NUMBERS_WITHIN_BLOCKS[i];
        }

        // generating the string pattern of the Tetromino according to its type
        switch (shape.type) {
            case O:
                // type O
                shape.pattern[0] = String.format(" %d %d  ", shape.numbers[0], shape.numbers[1]);
                shape.pattern[1] = String.format(" %d %d  ", shape.numbers[2], shape.numbers[3]);
                break;
            case I:
                // type I
                shape .pattern[0] = "          ";
                shape .pattern[1] = String.format(" %d %d %d %d  ", shape.numbers[0], shape.numbers[1], shape.numbers[2], shape.numbers[3]);
                break;
            case Z:
                // type Z
                shape.pattern[0] = String.format(" %d %d    ", shape.numbers[0], shape.numbers[1]);
                shape.pattern[1] = String.format("   %d %d  ", shape.numbers[2], shape.numbers[3]);
                break;
            case S:
                // type S
                shape.pattern[0] = String.format("   %d %d  ", shape.numbers[0], shape.numbers[1]);
                shape.pattern[1] = String.format(" %d %d    ", shape.numbers[2], shape.numbers[3]);
                break;
            case J:
                // type J
                shape.pattern[0] = String.format(" %d      ", shape.numbers[0]);
                shape.pattern[1] = String.format(" %d %d %d  ", shape.numbers[1], shape.numbers[2], shape.numbers[3]);
                break;
            case L:
                // type L
                shape.pattern[0] = String.format("     %d  ", shape.numbers[0]);
                shape.pattern[1] = String.format(" %d %d %d  ", shape.numbers[1], shape.numbers[2], shape.numbers[3]);
                break;
            case T:
                // type T
                shape.pattern[0] = String.format(" %d %d %d  ", shape.numbers[0], shape.numbers[1], shape.numbers[2]);
                shape.pattern[1] = String.format("   %d    ", shape.numbers[3]);
                break;
        }

        return shape;
    }

    public void rotate() {
        orientation++;
    }

    public void unrotate() {
        orientation--;
    }

    public int[] getNumbers() {
        return numbers;
    }

    public String[] getPattern() {
        return pattern;
    }

/*    public int[] getCoordinates() {
        int[][] coordinates = ALL_BLOCKS_COORDINATES[type];
        return coordinates[orientation % coordinates.length];
    }*/

}


