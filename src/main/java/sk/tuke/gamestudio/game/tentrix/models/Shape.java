package sk.tuke.gamestudio.game.tentrix.models;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sk.tuke.gamestudio.game.tentrix.enums.ShapeType;

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
    private String pattern;
    private int orientation;

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

    public static Shape create() {

        Shape shape = new Shape();

        shape.type = randomShape();

        switch (shape.type) {
            case O:
                shape.pattern = "XX \nXX";
                break;
            case I:
                shape.pattern = "X\nX\nX\nX\n";
                //shape.pattern = "XXXX";
                break;
            case S:
                shape.pattern = " XX \nXX ";
                //shape.pattern = "X \nXX\n X";
                break;
            case Z:
                shape.pattern = "XX \n XX";
                //shape.pattern = " X\nXX\nX ";
                break;
            case J:
                shape.pattern = " X\n X\nXX";
                //shape.pattern = "X  \nXXX";
                //shape.pattern = "XX\nX \nX ";
                //shape.pattern = "XXX\n  X";
                break;
            case L:
                shape.pattern = "X \nX \nXX";
                //shape.pattern = "XXX\nX  ";
                //shape.pattern = "XX\n X\n X";
                //shape.pattern = "  X\nXXX";
                break;
            case T:
                shape.pattern = "XXX\n X ";
                //shape.pattern = " X\nXX\n X";
                //shape.pattern = " X \nXXX";
                //shape.pattern = "X \nXX\nX ";
                break;
        }

        return shape;
    }

    //TODO: spravit rotaciu
    public void rotate() {
        orientation++;
    }

    public void unrotate() {
        orientation--;
    }

}


