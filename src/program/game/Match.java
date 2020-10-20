package program.game;

import packetManager.Request;
import packetManager.Response;
import program.game.interfaces.DirectionUtility;
import program.game.interfaces.Directions;
import socketManager.EventSocketOriented;
import socketManager.utilities.Tuple;

public class Match {
    private final EventSocketOriented socketOriented;
    private final MatchModel matchModel = new MatchModel();

    public Match(EventSocketOriented socketOriented) throws Exception {
        this.socketOriented = socketOriented;
        generateEvents();
    }

    private void generateEvents() throws Exception {
        socketOriented.on("move", this::move);
        socketOriented.on("pass_turn", this::passTurn);
        socketOriented.on("shoot", this::move);
        socketOriented.on("randomStartPosition", this::randomStartPosition);
    }

    private void randomStartPosition(Request request, Response response){
        try {
            socketOriented.emit64("didMove", new Tuple<>("did", "true"), new Tuple<>("x", Integer.valueOf((int)Math.floor(Math.random()*4)).toString()), new Tuple<>("y", Integer.valueOf((int)Math.floor(Math.random()*4)).toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void move(Request request, Response response){
        matchModel.request_to_command(() -> {
            String reqDirection = request.getBody("direction");
            Directions direction = DirectionUtility.getDirectionFromString(reqDirection);
            if (direction != null){
                matchModel.getHunter().move(direction);
                try {
                    socketOriented.emit64("didMove", new Tuple<>("did", "true"), new Tuple<>("x", "5"), new Tuple<>("y", "3"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void passTurn(Request request, Response response){

    }

    private void shoot(Request request, Response response){

    }



    private void bearTurn() throws Exception {
        //getDIRECTION
        Directions direction = Directions.DOWN;
        matchModel.request_to_command(() -> {

        });
        socketOriented.emit("bearPassed");
    }

}
