package program.game.cell.clue;

public abstract class Clue {
    private final String clueType;
    public Clue(String clueType){
        this.clueType = clueType;
    }
    public String getClueType() {
        return clueType;
    }
}
