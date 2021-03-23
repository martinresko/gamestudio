package sk.tuke.gamestudio.game.tentrix.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sk.tuke.gamestudio.game.tentrix.enums.ShapeType;

import java.util.Random;

import static sk.tuke.gamestudio.game.tentrix.enums.ShapeType.I_COORDINATES;
import static sk.tuke.gamestudio.game.tentrix.enums.ShapeType.I_SHAPE;
import static sk.tuke.gamestudio.game.tentrix.enums.ShapeType.J_COORDINATES;
import static sk.tuke.gamestudio.game.tentrix.enums.ShapeType.J_SHAPE;
import static sk.tuke.gamestudio.game.tentrix.enums.ShapeType.L_COORDINATES;
import static sk.tuke.gamestudio.game.tentrix.enums.ShapeType.L_SHAPE;
import static sk.tuke.gamestudio.game.tentrix.enums.ShapeType.O_COORDINATES;
import static sk.tuke.gamestudio.game.tentrix.enums.ShapeType.O_SHAPE;
import static sk.tuke.gamestudio.game.tentrix.enums.ShapeType.S_COORDINATES;
import static sk.tuke.gamestudio.game.tentrix.enums.ShapeType.S_SHAPE;
import static sk.tuke.gamestudio.game.tentrix.enums.ShapeType.T_COORDINATES;
import static sk.tuke.gamestudio.game.tentrix.enums.ShapeType.T_SHAPE;
import static sk.tuke.gamestudio.game.tentrix.enums.ShapeType.Z_COORDINATES;
import static sk.tuke.gamestudio.game.tentrix.enums.ShapeType.Z_SHAPE;
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
                shape.pattern = O_SHAPE.clone();
                break;
            case I:
                shape.pattern = I_SHAPE.clone();
                break;
            case S:
                shape.pattern = S_SHAPE.clone();
                break;
            case Z:
                shape.pattern = Z_SHAPE.clone();
                break;
            case J:
                shape.pattern = J_SHAPE.clone();
                break;
            case L:
                shape.pattern = L_SHAPE.clone();
                break;
            case T:
                shape.pattern = T_SHAPE.clone();
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


