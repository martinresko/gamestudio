package sk.tuke.gamestudio.game.tentrix.enums;

import java.util.List;
import java.util.Random;

public enum ShapeType {

    O,
    I,
    S,
    Z,
    L,
    J,
    T;

    private static final List<ShapeType> SHAPES =
            List.of(values());

    private static final int SIZE = SHAPES.size();
    private static final Random RANDOM = new Random();

    public static ShapeType randomShape()  {
        return SHAPES.get(RANDOM.nextInt(SIZE));
    }

    public static final int[][] O_COORDINATES = {{0, 0, 0, 1, 1, 0, 1, 1}, {0, 0, 0, 1, 1, 0, 1, 1}, {0, 0, 0, 1, 1, 0, 1, 1}, {0, 0, 0, 1, 1, 0, 1, 1}};
    public static final int[][] I_COORDINATES = {{0, 0, 0, 1, 0, 2, 0, 3}, {0, 0, 1, 0, 2, 0, 3, 0}, {0, 0, 0, 1, 0, 2, 0, 3}, {0, 0, 1, 0, 2, 0, 3, 0}};
    public static final int[][] S_COORDINATES = {{0, 0, 1, 0, 1, 1, 2, 1}, {1, 0, 0, 1, 1, 1, 0, 2}, {0, 0, 1, 0, 1, 1, 2, 1}, {1, 0, 0, 1, 1, 1, 0, 2}};
    public static final int[][] Z_COORDINATES = {{1, 0, 2, 0, 0, 1, 1, 1}, {0, 0, 0, 1, 1, 1, 1, 2}, {1, 0, 2, 0, 0, 1, 1, 1}, {0, 0, 0, 1, 1, 1, 1, 2}};
    public static final int[][] J_COORDINATES = {{0, 0, 1, 0, 1, 1, 1, 2}, {0, 0, 1, 0, 2, 0, 0, 1}, {0, 0, 0, 1, 0, 2, 1, 2}, {2, 0, 0, 1, 1, 1, 2, 1}};
    public static final int[][] L_COORDINATES = {{0, 0, 1, 0, 0, 1, 0, 2}, {0, 0, 0, 1, 1, 1, 2, 1}, {1, 0, 1, 1, 0, 1, 1, 2}, {0, 0, 1, 0, 2, 0, 2, 1}};
    public static final int[][] T_COORDINATES = {{1, 0, 0, 1, 1, 1, 2, 1}, {1, 0, 0, 1, 1, 1, 1, 2}, {0, 0, 1, 0, 2, 0, 1, 1}, {0, 0, 0, 1, 1, 1, 0, 2}};

    public static final String[] O_SHAPE = {"XX \nXX", "XX \nXX", "XX \nXX", "XX \nXX"};
    public static final String[] I_SHAPE = {"X\nX\nX\nX\n", "XXXX", "X\nX\nX\nX\n", "XXXX"};
    public static final String[] S_SHAPE = {" XX\nXX ", "X \nXX\n X", " XX\nXX ", "X \nXX\n X"};
    public static final String[] Z_SHAPE = {"XX \n XX", " X\nXX\nX ", "XX \n XX", " X\nXX\nX "};
    public static final String[] J_SHAPE = {" X\n X\nXX", "X  \nXXX", "XX\nX \nX ", "XXX\n  X"};
    public static final String[] L_SHAPE = {"X \nX \nXX", "XXX\nX  ", "XX\n X\n X", "  X\nXXX"};
    public static final String[] T_SHAPE = {"XXX\n X ", " X\nXX\n X", " X \nXXX", "X \nXX\nX "};

}
