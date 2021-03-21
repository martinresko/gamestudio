package sk.tuke.gamestudio.game.tentrix.core;


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

    private static int[][] coordinates = {{}};

    public static int[][] getCoordinates() {
        return coordinates;
    }

    public static Shape create() {

        Shape shape = new Shape();

        shape.type = randomShape();

        switch (shape.type) {
            case O:
                shape.pattern = "XX \nXX";
                coordinates = O_COORDINATES.clone();
                break;
            case I:
                shape.pattern = "XXXX";
                coordinates = I_COORDINATES.clone();
                break;
            case Z:
                shape.pattern = "XX \n XX";
                coordinates = Z_COORDINATES.clone();
                break;
            case S:
                shape.pattern = " XX \nXX ";
                coordinates = S_COORDINATES.clone();
                break;
            case J:
                shape.pattern = "X  \nXXX";
                coordinates = J_COORDINATES.clone();
                break;
            case L:
                shape.pattern = "  X \nXXX";
                coordinates = L_COORDINATES.clone();
                break;
            case T:
                shape.pattern = "XXX \n X ";
                coordinates = T_COORDINATES.clone();
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

}


