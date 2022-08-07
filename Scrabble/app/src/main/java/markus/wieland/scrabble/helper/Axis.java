package markus.wieland.scrabble.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Axis {

    VERTICAL(Direction.UP, Direction.DOWN),
    HORIZONTAL(Direction.LEFT, Direction.RIGHT);

    private final Direction directionNegative;
    private final Direction directionPositive;

    public Axis getOtherAxis() {
        if (this == VERTICAL) return HORIZONTAL;
        return VERTICAL;
    }

}
