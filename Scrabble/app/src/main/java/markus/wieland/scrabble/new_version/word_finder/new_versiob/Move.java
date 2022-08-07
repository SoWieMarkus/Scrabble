package markus.wieland.scrabble.new_version.word_finder.new_versiob;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import markus.wieland.scrabble.new_version.board.BoardMatrix;
import markus.wieland.scrabble.new_version.board.Field;

@Getter
public class Move {

    private int score;
    private final Set<PartialMove> moves;

    public Move() {
        this.moves = new HashSet<>();
        this.score = 0;
    }

    public boolean execute(BoardMatrix boardMatrix){
        for (PartialMove partialMove : moves) {
            Field field = boardMatrix.get(partialMove.getCoordinate());
            if (field.getLetter() != null || field.isConcrete()) return false;
            boardMatrix.get(partialMove.getCoordinate()).setLetter(partialMove.getLetter());
        }
        return true;
    }



}
