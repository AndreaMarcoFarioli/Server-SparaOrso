package program.game;

import packetManager.Request;
import packetManager.Response;
import program.game.cell.Cell;
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
        socketOriented.on("shoot", this::shoot);
        socketOriented.on("randomStartPosition", this::randomStartPosition);
    }

    private void randomStartPosition(Request request, Response response){
        try {
            if (matchModel.isStartGame()) {
                matchModel.getHunter().setLocation(
                        new Tuple<>
                                ((int) Math.floor(Math.random() * 4),
                                        (int) Math.floor(Math.random() * 4))
                );

                socketOriented.emit64("didMove",
                        new Tuple<>("did", "true"),
                        new Tuple<>("x", matchModel.getHunter().getLocation().getFirstValue().toString()),
                        new Tuple<>("y", matchModel.getHunter().getLocation().getSecondValue().toString()));
                matchModel.setStartGame(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void move(Request request, Response response){
        try{
            Cell cell = matchModel.getCell(matchModel.getHunter().getLocation());
            Directions direction = DirectionUtility.getDirectionFromString(request.getBody("direction"));
            Tuple<String, String> x = new Tuple<>("x"," "), y = new Tuple<>("y", " ");

            boolean did =
                    !matchModel.checkEndGame() &&
                    direction != null &&
                    cell.getNearCell(request.getBody("direction")) != null;

            if (did){
                Tuple<Integer, Integer> nearCell = cell.getNearCell(request.getBody("direction"));
                matchModel.getHunter().setLocation(nearCell);
                matchModel.decrement_movements();
                x = new Tuple<>("x", nearCell.getFirstValue().toString());
                y = new Tuple<>("y", nearCell.getSecondValue().toString());
            }

            socketOriented.emit64("didMove", new Tuple<>("did", ""+did), x,y);
            bearTurn();
            checkEndGame();
        }catch (Exception e){}
    }

    private void shoot(Request request, Response response){
        try{
            matchModel.getBear().setLocation(new Tuple<>(0,0));
            Tuple<Integer, Integer>
                    hunterLocation = matchModel.getHunter().getLocation(),
                    bearLocation = matchModel.getBear().getLocation();
            boolean did, eq = false;
            if (did = !matchModel.checkEndGame()){
                matchModel.decrement_bullets();
                matchModel.setBearHit(hunterLocation.equals(bearLocation));
            }
            socketOriented.emit64("didShoot", new Tuple<>("did", ""+did));
            checkEndGame();
        }catch (Exception e){}
    }

    private void checkEndGame() throws Exception {
        if (matchModel.checkEndGame()){
            boolean win = matchModel.isBearHit();
            socketOriented.emit64("endGame", new Tuple<>("win", ""+win));
        }
    }

    private void bearTurn() throws Exception {
        //getDIRECTION
        Directions direction = Directions.DOWN;
        matchModel.request_to_command(() -> {

        });
        socketOriented.emit("bearPassed");
    }

}
