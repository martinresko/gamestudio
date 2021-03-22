package sk.tuke.gamestudio.game.tentrix.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserInput {

    private int xCoordinate;
    private int yCoordinate;
    private int shape;

}
