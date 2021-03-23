package sk.tuke.gamestudio.game.tentrix.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sk.tuke.gamestudio.game.tentrix.enums.ShapeType;

import java.util.Random;

import static sk.tuke.gamestudio.game.tentrix.enums.ShapeType.I_COORDINATES;
import static sk.tuke.gamestudio.game.tentrix.enums.ShapeType.J_COORDINATES;
import static sk.tuke.gamestudio.game.tentrix.enums.ShapeType.L_COORDINATES;
import static sk.tuke.gamestudio.game.tentrix.enums.ShapeType.O_COORDINATES;
import static sk.tuke.gamestudio.game.tentrix.enums.ShapeType.S_COORDINATES;
import static sk.tuke.gamestudio.game.tentrix.enums.ShapeType.T_COORDINATES;
import static sk.tuke.gamestudio.game.tentrix.enums.ShapeType.Z_COORDINATES;
import static sk.tuke.gamestudio.game.tentrix.enums.ShapeType.randomShape;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Shape {

    private ShapeType type;
    private String[] pattern;
    private int orientation;
    private static final Random random = new Random();

    public static Shape create() {

        Shape shape = new Shape();

        shape.orientation = random.nextInt(4);
        shape.pattern = new String[4];
        shape.type = randomShape();

        switch (shape.type) {
            case O:
                shape.pattern[0] = "XX \nXX";
                shape.pattern[1] = "XX \nXX";
                shape.pattern[2] = "XX \nXX";
                shape.pattern[3] = "XX \nXX";
                break;
            case I:
                shape.pattern[0] = "X\nX\nX\nX\n";
                shape.pattern[1] = "XXXX";
                shape.pattern[2] = "X\nX\nX\nX\n";
                shape.pattern[3] = "XXXX";
                break;
            case S:
                shape.pattern[0] = " XX \nXX ";
                shape.pattern[1] = "X \nXX\n X";
                shape.pattern[2] = " XX \nXX ";
                shape.pattern[3] = "X \nXX\n X";
                break;
            case Z:
                shape.pattern[0] = "XX \n XX";
                shape.pattern[1] = " X\nXX\nX ";
                shape.pattern[2] = "XX \n XX";
                shape.pattern[3] = " X\nXX\nX ";
                break;
            case J:
                shape.pattern[0] = " X\n X\nXX";
                shape.pattern[1] = "X  \nXXX";
                shape.pattern[2] = "XX\nX \nX ";
                shape.pattern[3] = "XXX\n  X";
                break;
            case L:
                shape.pattern[0] = "X \nX \nXX";
                shape.pattern[1] = "XXX\nX  ";
                shape.pattern[2] = "XX\n X\n X";
                shape.pattern[3] = "  X\nXXX";
                break;
            case T:
                shape.pattern[0] = "XXX\n X ";
                shape.pattern[1] = " X\nXX\n X";
                shape.pattern[2] = " X \nXXX";
                shape.pattern[3] = "X \nXX\nX ";
                break;
        }

        return shape;
    }

    public int[][] provideShapeCoordinates () {
        switch (this.type) {
            case O:
                return O_COORDINATES;
            case I:
                return I_COORDINATES;
            case S:
                return S_COORDINATES;
            case Z:
                return Z_COORDINATES;
            case J:
                return J_COORDINATES;
            case L:
                return L_COORDINATES;
            case T:
                return T_COORDINATES;
        }
        return null;
    }

}


