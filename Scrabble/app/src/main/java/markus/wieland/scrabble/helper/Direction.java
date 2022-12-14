package markus.wieland.scrabble.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Direction {

    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1);

    private final int vertical;
    private final int horizontal;

    public Axis getAxis() {
        switch (this) {
            case RIGHT:
            case LEFT:
                return Axis.HORIZONTAL;
            case DOWN:
            case UP:
                return Axis.VERTICAL;
            default:
                throw new IllegalStateException();
        }
    }

    public boolean isNegativeDirection() {
        return horizontal < 0 || vertical < 0;
    }

    public boolean isHorizontal() {
        return horizontal != 0;
    }

    public boolean isVertical() {
        return vertical != 0;
    }

    public Direction getOppositeDirection() {
        switch (this) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            default:
                throw new IllegalStateException();
        }
    }


}
