package program.game;

import program.game.cell.Cell;
import socketManager.utilities.Tuple;

public class Grid {
    private final Cell[][] cells;

    private final int
            DIMENSION_X = 4,
            DIMENSION_Y = 4;
    public Grid(){
        cells = new Cell[DIMENSION_X][DIMENSION_Y];
        for (int y = 0; y < DIMENSION_Y; y++)
            for (int x = 0; x < DIMENSION_X; x++){
                Cell cell = new Cell(new Tuple<>(x, y));
                cell.setNearCells(new Tuple<>(DIMENSION_X, DIMENSION_Y));
                cells[x][y] = cell;
            }
    }

    public void getRoadMap(){

    }

    public Cell getCell(Tuple<Integer, Integer> location){
        Cell cell = null;
        for (int y = 0; y < DIMENSION_Y; y++)
            for (int x = 0; x < DIMENSION_X; x++){
                if (location.equals(new Tuple<>(x,y))){
                    cell = cells[x][y];
                    break;
                }
            }

        return cell;
    }
}
