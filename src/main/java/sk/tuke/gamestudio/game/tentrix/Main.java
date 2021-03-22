package sk.tuke.gamestudio.game.tentrix;

import sk.tuke.gamestudio.game.tentrix.consoleui.ConsoleUI;
import sk.tuke.gamestudio.game.tentrix.models.Field;
import sk.tuke.gamestudio.game.tentrix.service.GameEngineService;

public class Main {

    public static void main(String[] args) {
        Field field = new Field(9, 9, 3);
        GameEngineService gameEngineService = new GameEngineService(field);
        ConsoleUI ui = new ConsoleUI(field, gameEngineService);
        ui.play();
    }

}
