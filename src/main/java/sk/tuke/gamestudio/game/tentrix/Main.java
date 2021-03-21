package sk.tuke.gamestudio.game.tentrix;

import sk.tuke.gamestudio.game.tentrix.consoleui.ConsoleUI;
import sk.tuke.gamestudio.game.tentrix.core.Field;

public class Main {

    public static void main(String[] args) {
        Field field = new Field(9, 9, 3);
        ConsoleUI ui = new ConsoleUI(field);
        ui.play();
    }

}
