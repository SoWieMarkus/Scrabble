package markus.wieland.scrabble.helper;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import markus.wieland.scrabble.helper.Coordinate;

@Setter
@Getter
@AllArgsConstructor
public class Dimension implements Iterable<Coordinate> {

    private int height;
    private int width;

    private final List<Coordinate> allCoordinates;

    public Dimension(int height, int width) {
        this.height = height;
        this.width = width;
        this.allCoordinates = new ArrayList<>();
        for (int x = 0; x< height; x++) {
            for (int y = 0; y < width; y++) {
                allCoordinates.add(new Coordinate(x,y));
            }
        }
    }

    public boolean isInsideRange(Coordinate coordinate) {
        if (coordinate.getX() < 0 || coordinate.getX() >= height) return false;
        return coordinate.getY() >= 0 && coordinate.getY() < width;
    }


    @NonNull
    @Override
    public Iterator<Coordinate> iterator() {
        return allCoordinates.iterator();
    }
}
