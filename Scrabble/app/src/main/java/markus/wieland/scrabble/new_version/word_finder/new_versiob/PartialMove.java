package markus.wieland.scrabble.new_version.word_finder.new_versiob;

import lombok.AllArgsConstructor;
import lombok.Getter;
import markus.wieland.scrabble.new_version.board.Letter;
import markus.wieland.scrabble.helper.Coordinate;


@AllArgsConstructor
@Getter
public class PartialMove {

    private final Coordinate coordinate;
    private final Letter letter;

}
