package markus.wieland.scrabble.board.word_managment.solver;

import java.util.List;

import markus.wieland.scrabble.board.Board;
import markus.wieland.scrabble.helper.Axis;

public class Move {

    private int score;
    private Axis axis;
    private List<PartialMove> partialMoves;


    public void execute(Board board) {
        for (PartialMove partialMove : partialMoves) {
            partialMove.execute(board);
        }
    }


}
