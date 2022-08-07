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
        int nextX = x + direction.getVertical();
        int nextY = y + direction.getHorizontal();
        return new Coordinate(nextX, nextY);
    }

    @NonNull
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
