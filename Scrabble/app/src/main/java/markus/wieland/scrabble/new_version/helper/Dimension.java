package markus.wieland.scrabble.new_version.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Dimension {

    private int height;
    private int width;

    public boolean isInsideRange(Coordinate coordinate) {
        if (coordinate.getX() < 0 || coordinate.getX() >= height) return false;
        return coordinate.getY() >= 0 && coordinate.getY() < width;
    }

}
