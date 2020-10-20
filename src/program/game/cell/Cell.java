package program.game.cell;

import socketManager.utilities.Tuple;

import java.util.HashMap;

public class Cell {
    private final HashMap<String, Tuple<Integer,Integer>> nearCells = new HashMap<>();
    private final Tuple<Integer, Integer> id;
    public Cell(Tuple<Integer, Integer> id){
        this.id = id;
    }
// firstValue = MAXX, secondValue = MAXY
    public void setNearCells(Tuple<Integer, Integer> MaxValues){
        if (id.getFirstValue() > 0)
            nearCells.put("l", new Tuple<>(id.getFirstValue() - 1, id.getSecondValue()));
        if (id.getFirstValue() < MaxValues.getFirstValue() - 1)
            nearCells.put("r", new Tuple<>(id.getFirstValue() + 1, id.getSecondValue()));
        if (id.getSecondValue() > 0)
            nearCells.put("u", new Tuple<>(id.getFirstValue(), id.getSecondValue() - 1));
        if (id.getSecondValue() < MaxValues.getFirstValue() - 1)
            nearCells.put("d", new Tuple<>(id.getFirstValue(), id.getSecondValue() + 1));
    }

    public Tuple<Integer, Integer> getId() {
        return id;
    }

    public Tuple<Integer,Integer> getNearCell(String direction) {
        return nearCells.get(direction);
    }

    @Override
    public String toString() {
        return "Cell{" +
                "nearCells=" + nearCells +
                ", id=" + id +
                '}';
    }
}
