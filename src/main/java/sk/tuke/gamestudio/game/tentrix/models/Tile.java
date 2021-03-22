package sk.tuke.gamestudio.game.tentrix.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import sk.tuke.gamestudio.game.tentrix.enums.TileState;

@AllArgsConstructor
@Getter
@Setter
public class Tile {

    private TileState state;

}
