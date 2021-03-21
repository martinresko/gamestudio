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

    public static final int[][] O_COORDINATES = {{0, 0, 0, 1, 1, 0, 1, 1}};
    public static final int[][] I_COORDINATES = {{0, 0, 0, 1, 0, 2, 0, 3}, {0, 0, 1, 0, 2, 0, 3, 0}};
    public static final int[][] S_COORDINATES = {{0, 1, 0, 2, 1, 0, 1, 1}, {1, 1, 2, 1, 0, 0, 1, 0}};
    public static final int[][] Z_COORDINATES = {{0, 0, 0, 1, 1, 1, 1, 2}, {0, 1, 1, 0, 1, 1, 2, 0}};
    public static final int[][] L_COORDINATES = {{0, 2, 1, 0, 1, 1, 1, 2}, {2, 1, 0, 0, 1, 0, 2, 0}, {1, 0, 0, 2, 0, 1, 0, 0}, {0, 0, 2, 1, 1, 1, 0, 1}};
    public static final int[][] J_COORDINATES = {{0, 0, 1, 0, 1, 1, 1, 2}, {0, 1, 0, 0, 1, 0, 2, 0}, {1, 2, 0, 2, 0, 1, 0, 0}, {2, 0, 2, 1, 1, 1, 0, 1}};
    public static final int[][] T_COORDINATES = {{0, 1, 1, 1, 2, 1, 1, 0}, {0, 0, 0, 1, 0, 2, 1, 1}, {1, 2, 1, 1, 1, 0, 0, 1}, {2, 0, 1, 0, 0, 0, 1, 1}};

}
