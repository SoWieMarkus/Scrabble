package markus.wieland.scrabble.helper;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import markus.wieland.scrabble.helper.Coordinate;

@Getter
@Setter
public class Range {

    private Coordinate startCoordinate;
    private Coordinate endCoordinate;

    public Range(Coordinate startCoordinate, Coordinate endCoordinate) {
        this.startCoordinate = startCoordinate;
        this.endCoordinate = endCoordinate;
    }

    public Range() {
        this.startCoordinate = null;
        this.endCoordinate = null;
    }

    public void update(Coordinate coordinate) {
        if (startCoordinate == null) {
            startCoordinate = coordinate;
        }
        endCoordinate = coordinate;

        if (startCoordinate.getX() != endCoordinate.getX() && startCoordinate.getY() != endCoordinate.getY()) {
            throw new IllegalArgumentException();
        }
    }

    public Axis getAxis(){
        boolean isXDirection = startCoordinate.getX() == endCoordinate.getX();
        boolean isYDirection = startCoordinate.getY() == endCoordinate.getY();

        if (isXDirection) return Axis.VERTICAL;
        if (isYDirection) return Axis.HORIZONTAL;
        throw new IllegalStateException();
    }

    public List<Coordinate> getListOfCoordinates(){
        Axis axis = getAxis();

        int staticCoordinate = axis == Axis.VERTICAL ? startCoordinate.getX() : startCoordinate.getY();
        int min = axis == Axis.VERTICAL ? Math.min(startCoordinate.getY(), endCoordinate.getY())
                : Math.min(startCoordinate.getX(), endCoordinate.getX());
        int max = axis == Axis.VERTICAL ? Math.max(startCoordinate.getY(), endCoordinate.getY())
                : Math.max(startCoordinate.getX(), endCoordinate.getX());

        List<Coordinate> coordinates = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            if (axis == Axis.VERTICAL){
                coordinates.add(new Coordinate(staticCoordinate, i));
                continue;
            }
            coordinates.add(new Coordinate(i, staticCoordinate));
        }

        return coordinates;
    }

    @NonNull
    @Override
    public String toString() {
        if (startCoordinate == null) throw new IllegalStateException();
        return startCoordinate.getX() + ","
                + startCoordinate.getY() + ","
                + endCoordinate.getX() + ","
                + endCoordinate.getY();
    }
}
