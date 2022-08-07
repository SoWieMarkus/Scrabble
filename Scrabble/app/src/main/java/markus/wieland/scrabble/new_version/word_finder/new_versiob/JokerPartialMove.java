package markus.wieland.scrabble.new_version.word_finder.new_versiob;

import lombok.Getter;
import markus.wieland.scrabble.new_version.board.Letter;
import markus.wieland.scrabble.helper.Coordinate;

@Getter
public class JokerPartialMove extends PartialMove {

    private final char valueOfJoker;

    public JokerPartialMove(Coordinate coordinate, Letter letter, char valueOfJoker) {
        super(coordinate, letter);
        this.valueOfJoker = valueOfJoker;
    }

    @Override
    public Letter getLetter() {
        return new Letter(0, valueOfJoker);
    }
}
