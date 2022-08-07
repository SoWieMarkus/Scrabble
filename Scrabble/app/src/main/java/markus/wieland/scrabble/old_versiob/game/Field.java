package markus.wieland.scrabble.old_versiob.game;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.scrabble.helper.Coordinate;

@Getter
@Setter
public class Field {

    private Coordinate coordinate;

    public Coordinate getCoordinate() {
        return new Coordinate(coordinate.getX(), coordinate.getY());
    }
}
