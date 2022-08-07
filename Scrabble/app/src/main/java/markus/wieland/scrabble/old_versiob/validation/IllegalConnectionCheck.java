package markus.wieland.scrabble.old_versiob.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import markus.wieland.scrabble.new_version.board.BoardMatrix;
import markus.wieland.scrabble.new_version.board.Field;
import markus.wieland.scrabble.helper.Coordinate;
import markus.wieland.scrabble.helper.Matrix;
import markus.wieland.scrabble.R;
import markus.wieland.scrabble.helper.Direction;

public class IllegalConnectionCheck implements Validation {

    private final Matrix<Boolean> visited;
    private final List<Coordinate> coordinatesToCheck;
    private final Coordinate startCoordinate;

    public IllegalConnectionCheck(BoardMatrix boardMatrix, Coordinate startCoordinate) {
        coordinatesToCheck = new ArrayList<>();
        visited = new Matrix<>(boardMatrix.getDimension());
        this.startCoordinate = startCoordinate;
        for (Field field : boardMatrix) {
            if (field.getLetter() == null) continue;
            visited.set(field.getCoordinate(), false);
        }
    }

    @Override
    public ValidationResult validate() {
        coordinatesToCheck.clear();
        coordinatesToCheck.add(startCoordinate);

        while(!coordinatesToCheck.isEmpty()) {
            Coordinate coordinate = coordinatesToCheck.remove(0);
            if (visited.get(coordinate) == null) break;
            visited.set(coordinate, true);

            for (Direction direction : Objects.requireNonNull(Direction.class.getEnumConstants())) {
                Coordinate nextCoordinate = coordinate.getNextCoordinate(direction);
                if (visited.get(nextCoordinate) == null || visited.get(nextCoordinate)) continue;
                coordinatesToCheck.add(nextCoordinate);
            }
        }

        for (Boolean nodeVisited : visited) {
            if (nodeVisited == null) continue;
            if (!nodeVisited) return new ValidationResult.NonValidResult(R.string.error_message_not_connected);
        }

        return new ValidationResult();
    }
}