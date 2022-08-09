package markus.wieland.scrabble.board.word_managment.solver;

import markus.wieland.scrabble.board.Field;
import markus.wieland.scrabble.helper.Coordinate;

public class SearchField extends Field {

    public SearchField(Coordinate coordinate) {
        super(coordinate);
    }

    public void update(Field field) {
        setLetter(field.getLetter());
        setSpecialBlockType(field.getSpecialBlockType());
        setConcrete(field.isConcrete());
    }
}
