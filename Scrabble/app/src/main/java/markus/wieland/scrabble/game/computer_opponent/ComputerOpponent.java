package markus.wieland.scrabble.game.computer_opponent;

import android.app.Activity;

import java.util.List;

import markus.wieland.scrabble.board.Board;
import markus.wieland.scrabble.board.word_managment.solver.Move;
import markus.wieland.scrabble.board.word_managment.solver.WordFinder;
import markus.wieland.scrabble.game.Player;


public class ComputerOpponent extends Player {

    private float difficulty;
    private float currentAverageScore;

    public Move getMove(Activity activity, Board board){
        WordFinder wordFinder = new WordFinder(board, getInventory());
        List<Move> possibleMoves = wordFinder.getPossibleMoves(activity);
        if (possibleMoves.isEmpty()) return null;
        float estimatedScore = getEstimatedScore();

        float currentMaxScore = 0f;
        Move currentMove = null;


    }

    private float getEstimatedScore(){
        return difficulty * 2 - currentAverageScore;
    }



}
