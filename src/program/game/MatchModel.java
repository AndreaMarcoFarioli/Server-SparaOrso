package program.game;

import program.game.cell.Cell;
import program.game.cell.clue.Clue;
import program.game.cell.clue.FootPrint;
import program.game.interfaces.Bear;
import program.game.interfaces.Directions;
import program.game.interfaces.Hunter;
import program.game.interfaces.PG;
import socketManager.utilities.Tuple;

import java.util.ArrayList;

public class MatchModel {
    public static final int
            MOVEMENT_PER_ROUND = 3,
            MAX_ROUNDS = 3,
            MAX_BULLETS = 3;

    private int
            actual_movement_count,
            actual_round_count,
            actual_bullet_count;

    private final Grid grid = new Grid();

    private final ArrayList<Tuple<Cell, Clue>> clues = new ArrayList<>();

    private boolean endgame = false;

    private final PG
            bear = new Bear(grid),
            hunter = new Hunter(grid);

    public void resetGame(){
        actual_round_count = MAX_ROUNDS;
        actual_bullet_count = MAX_BULLETS;
        actual_movement_count = MOVEMENT_PER_ROUND;
        endgame = false;
    }

    public void request_to_command(Runnable runnable){
        if(!endgame)
            runnable.run();
    }

    public void decrement_movements(){
        actual_movement_count--;
    }

    public void decrement_bullets(){
        actual_bullet_count--;
    }

    public void decrement_rounds(){
        actual_round_count--;
    }

    public int getActual_movement_count() {
        return actual_movement_count;
    }

    public int getActual_bullet_count() {
        return actual_bullet_count;
    }

    public int getActual_round_count() {
        return actual_round_count;
    }

    public boolean isEndgame() {
        return endgame;
    }

    public void addFootPrintClue(Directions direction){
        clues.add(new Tuple<>(grid.getCell(bear.getLocation()), new FootPrint(direction)));
    }

    public Bear getBear() {
        return (Bear) bear;
    }

    public Hunter getHunter(){
        return (Hunter) hunter;
    }
}
