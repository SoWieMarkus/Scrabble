package markus.wieland.scrabble.old_versiob.game;

import java.util.ArrayList;
import java.util.List;

import markus.wieland.scrabble.helper.Coordinate;
import markus.wieland.scrabble.helper.Matrix;
import markus.wieland.scrabble.old_versiob.board_layout.BoardSpecialFieldType;
import markus.wieland.scrabble.helper.Dimension;
import markus.wieland.scrabble.old_versiob.board_layout.SpecialField;

public class SpecialFieldsMatrix extends Matrix<SpecialField> {

    public SpecialFieldsMatrix(int height, int width) {
        super(height, width);
    }

    public SpecialFieldsMatrix(Dimension dimension) {
        super(dimension);
    }

    public List<Coordinate> getCoordinateOfFieldType(BoardSpecialFieldType boardSpecialFieldType, boolean needsResult) {
        List<Coordinate> coordinates = new ArrayList<>();
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                SpecialField specialField = get(i, j);

                if (specialField == null || specialField.getType() != boardSpecialFieldType) continue;
                coordinates.add(new Coordinate(i, j));
            }
        }

        if (needsResult && coordinates.isEmpty())
            throw new IllegalStateException("No result for " + boardSpecialFieldType.toString());
        return coordinates;
    }

    public List<Coordinate> getCoordinateOfFieldType(BoardSpecialFieldType boardSpecialFieldType) {
        return getCoordinateOfFieldType(boardSpecialFieldType, false);

    }
}
