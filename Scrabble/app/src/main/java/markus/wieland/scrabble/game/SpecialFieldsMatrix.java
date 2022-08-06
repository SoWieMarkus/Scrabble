package markus.wieland.scrabble.game;

import java.util.ArrayList;
import java.util.List;

import markus.wieland.scrabble.new_version.helper.Coordinate;
import markus.wieland.scrabble.new_version.helper.Matrix;
import markus.wieland.scrabble.board_layout.BoardSpecialFieldType;
import markus.wieland.scrabble.new_version.helper.Dimension;
import markus.wieland.scrabble.board_layout.SpecialField;

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
