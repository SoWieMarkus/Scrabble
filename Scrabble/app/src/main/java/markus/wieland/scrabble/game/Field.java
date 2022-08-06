package markus.wieland.scrabble.game;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.scrabble.new_version.helper.Coordinate;

@Getter
@Setter
public class Field {

    private Coordinate coordinate;

    public Coordinate getCoordinate() {
        return new Coordinate(coordinate.getX(), coordinate.getY());
    }
}
