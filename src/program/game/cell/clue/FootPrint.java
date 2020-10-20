package program.game.cell.clue;

import program.game.interfaces.Directions;

public class FootPrint extends Clue {
    private final Directions direction;

    public FootPrint(Directions direction){
        super("FootPrint");
        this.direction = direction;
    }

    public Directions getDirection() {
        return direction;
    }
}
