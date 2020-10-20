package program.game.interfaces;

import socketManager.utilities.Tuple;

public interface GridMover {
    void move(Directions directions);
    void setLocation(Tuple<Integer, Integer> location);
    Tuple<Integer, Integer> getLocation();
}
