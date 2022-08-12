package markus.wieland.scrabble.game;

import android.app.Activity;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.scrabble.board.Board;
import markus.wieland.scrabble.game.computer_opponent.ComputerOpponent;
import markus.wieland.scrabble.game.letters.Letters;
import markus.wieland.scrabble.game.state.GameState;
import markus.wieland.scrabble.new_version.word_finder.Move;

@Getter
@Setter
public class Scrabble {

    private final Board board;
    private final Dictionary dictionary;
    private final Activity activity;
    private final Letters letters;
    private GameEventListener gameEventListener;
    private Players players;

    public Scrabble(GameState gameState, Activity activity) {
        this.board = new Board(gameState);
        this.dictionary = new Dictionary();
        this.activity = activity;
        //TODO
        this.letters = new Letters(activity);
        this.players = new Players();
    }

    public void registerPlayer(Player player) {
        this.players.add(player);
    }

    public void executeMove(Move move) {

        // TODO execute move
        // validate
        // gib neue steine
        // next player
        Player player = players.getNextPlayer();
        if (gameEventListener != null) gameEventListener.onNextPlayer(player);
        if (player instanceof ComputerOpponent) {
            Thread thread = new Thread() {
                @Override
                public void run(){
                    Move computerMove = ((ComputerOpponent) player).getMove(board);
                    executeMove(computerMove);
                }
            };
            thread.start();
        }
    }




}
