package markus.wieland.scrabble.board.word_managment.solver;

import markus.wieland.scrabble.game.Letter;
import markus.wieland.scrabble.helper.Coordinate;

public class JokerPartialMove extends PartialMove {

    public JokerPartialMove(Letter letter, Coordinate coordinate) {
        super(letter, coordinate);
    }
}
