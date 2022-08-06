package markus.wieland.scrabble.game.bot;

import java.util.ArrayList;
import java.util.List;

import markus.wieland.scrabble.game.Field;
import markus.wieland.scrabble.new_version.helper.Direction;

public class PossibleAddWordField extends Field {

    private final List<Direction> directions;

    public PossibleAddWordField() {
        this.directions = new ArrayList<>();
    }

    public PossibleAddWordField(Direction direction) {
        this();
        addDirection(direction);
    }

    public void addDirection(Direction direction) {
        Direction directionToAdd = direction.getOppositeDirection();
        if (directions.contains(directionToAdd)) throw new IllegalStateException();
        this.directions.add(directionToAdd);
    }


}
