package markus.wieland.scrabble.new_version.word_finder;

import markus.wieland.scrabble.helper.Coordinate;


public class ScrabbleWordFinderLetterField extends ScrabbleWordFinderField {

    private final char letter;

    public ScrabbleWordFinderLetterField(Coordinate coordinate, char letter) {
        super(coordinate);
        this.letter = letter;
    }

    @Override
    public char getPatternChar() {
        return letter;
    }

}
