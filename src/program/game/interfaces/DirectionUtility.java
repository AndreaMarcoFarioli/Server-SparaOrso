package program.game.interfaces;

public class DirectionUtility{
    public static Directions getDirectionFromString(String direction){
        return switch (direction) {
            case "u" -> Directions.UP;
            case "d" -> Directions.DOWN;
            case "l" -> Directions.LEFT;
            case "r" -> Directions.RIGHT;
            default -> null;
        };
    }

    public static String getStringFromDirection(Directions direction){
        return switch (direction) {
            case UP -> "u";
            case DOWN -> "d";
            case LEFT -> "l";
            case RIGHT -> "r";
        };
    }
}