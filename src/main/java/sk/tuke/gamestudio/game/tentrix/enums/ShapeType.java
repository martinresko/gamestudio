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

    public static ShapeType randomshape()  {
        return SHAPES.get(RANDOM.nextInt(SIZE));
    }
}
