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
            MOVEMENT_PER_ROUND = 9,
            MAX_ROUNDS = 1,
            MAX_BULLETS = 3;

    private int
            actual_movement_count = MOVEMENT_PER_ROUND,
            actual_round_count = MAX_ROUNDS,
            actual_bullet_count = MAX_BULLETS;

    private final Grid grid = new Grid();

    private final ArrayList<Tuple<Cell, Clue>> clues = new ArrayList<>();

    private boolean
            endGame = false,
            startGame= true,
            bearHit = false;

    private final PG
            bear = new Bear(grid),
            hunter = new Hunter(grid);

    public void resetGame(){
        actual_round_count = MAX_ROUNDS;
        actual_bullet_count = MAX_BULLETS;
        actual_movement_count = MOVEMENT_PER_ROUND;
        endGame = false;
    }

    public void request_to_command(Runnable runnable){
        if(!endGame)
            runnable.run();
    }

    public void decrement_movements(){
        if(--actual_movement_count == 0){
            if(!endGame) {
                if(--actual_round_count > 0){
                    actual_movement_count = MOVEMENT_PER_ROUND;
                }else
                    endGame = true;
            }
        }
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

    public boolean isEndGame() {
        return endGame;
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

    public void setStartGame(boolean startGame){
        this.startGame = startGame;
    }

    public boolean isStartGame() {
        return startGame;
    }

    public Cell getCell(Tuple<Integer, Integer> coordinates){
        return grid.getCell(coordinates);
    }

    public boolean isPossibleMove(){
        return getActual_movement_count() > 0 && enoughBullets();
    }

    public boolean enoughBullets(){
        return getActual_bullet_count() > 0;
    }

    public boolean isBearHit() {
        return bearHit;
    }

    public void setBearHit(boolean bearHit) {
        this.bearHit = bearHit;
    }

    public boolean checkEndGame(){
        return bearHit || !enoughBullets() || !isPossibleMove();
    }
}
