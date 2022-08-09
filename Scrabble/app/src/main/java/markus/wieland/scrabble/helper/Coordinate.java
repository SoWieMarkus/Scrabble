package markus.wieland.scrabble.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class Coordinate {

    private final int x;
    private final int y;

    public Coordinate getNextCoordinate(Direction direction) {
       return getCoordinate(direction, 1);
    }

    public Coordinate getCoordinate(Direction direction, int steps) {
        int nextX = x + direction.getVertical() * steps;
        int nextY = y + direction.getHorizontal() * steps;
        return new Coordinate(nextX, nextY);
    }

    @NonNull
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
