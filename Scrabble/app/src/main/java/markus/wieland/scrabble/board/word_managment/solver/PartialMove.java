package markus.wieland.scrabble.board.word_managment.solver;

import lombok.AllArgsConstructor;
import lombok.Getter;
import markus.wieland.scrabble.board.Board;
import markus.wieland.scrabble.game.letters.Letter;
import markus.wieland.scrabble.helper.Coordinate;

@AllArgsConstructor
@Getter
public class PartialMove {

    private Letter letter;
    private Coordinate coordinate;
    private boolean concrete;

    public void execute(Board board) {
        board.get(coordinate).setLetter(letter);
    }
}
