package markus.wieland.scrabble.board;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.scrabble.game.Letter;
import markus.wieland.scrabble.game.SpecialBlockType;
import markus.wieland.scrabble.helper.Coordinate;

@Getter
@Setter
public class Field {

    private Coordinate coordinate;
    private Letter letter;
    private SpecialBlockType specialBlockType;
    private boolean concrete;

    public Field(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

}
