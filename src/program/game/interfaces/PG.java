package program.game.interfaces;

import program.game.Grid;
import program.game.cell.Cell;
import socketManager.utilities.Tuple;

public abstract class PG implements GridMover {
    private final Grid gridReference;
    private Tuple<Integer, Integer> location;

    public PG(Grid gridReference){
        this.gridReference = gridReference;
    }

    @Override
    public void setLocation(Tuple<Integer, Integer> location) {
        this.location = location;
    }

    @Override
    public Tuple<Integer, Integer> getLocation() {
        return location;
    }

    @Override
    public void move(Directions directions) {
        Cell cell = gridReference.getCell(location);
        if(cell != null){
            Tuple<Integer, Integer> cellTargetLocation = cell.getNearCell(DirectionUtility.getStringFromDirection(directions));
            if(cellTargetLocation != null) {
                Cell cellTarget = gridReference.getCell(cellTargetLocation);
            }
        }
    }
}