package program.game.json;

import socketManager.utilities.Tuple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class RoadMap {
    private Tuple<Integer, Integer> address;
    private HashMap<String, ArrayList<String>> paths;

    @Override
    public String toString() {
        return "RoadMap{" +
                "address=" + address +
                ", paths=" + paths +
                '}';
    }

    public HashMap<String, ArrayList<String>> getPaths() {
        return paths;
    }

    public Tuple<Integer,Integer> getAddress() {
        return address;
    }
}
