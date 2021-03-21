package sk.tuke.gamestudio.game.tentrix;

public class Test {
    public static void main(String [] args) {
        Field field = new Field(5, 12, 3);
        for (int row = 0; row < field.getRowCount(); row++){
            for (int column = 0; column < field.getColCount(); column++){
                Tile tile = field.getTile(row,column);
                if (tile == null){
                    System.out.print("-");

                }
            }
            System.out.println();

        }


       for (int shape = 0; shape  < field.getShapeCount(); shape++ ){
            Tile stile = field.getsTile(shape);
            if (stile == null){
                System.out.print("[ ]");


            }

        }


    }
}
