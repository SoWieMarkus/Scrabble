package markus.wieland.scrabble.new_version.word_finder;

import lombok.Getter;
import markus.wieland.scrabble.helper.Coordinate;

@Getter
public class ScrabbleWordFinderField {

    private final Coordinate coordinate;

    private static final char ANY_CHAR = '.';

    public ScrabbleWordFinderField(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public char getPatternChar() {
        return ANY_CHAR;
    }

}

