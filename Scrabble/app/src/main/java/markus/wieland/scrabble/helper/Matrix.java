package markus.wieland.scrabble.helper;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import markus.wieland.scrabble.helper.Coordinate;
import markus.wieland.scrabble.helper.Dimension;

public class Matrix<E> implements Iterable<E> {

    private final List<List<E>> matrixObject;
    private final int height;
    private final int width;
    private final Dimension dimension;

    public Matrix(int height, int width) {
        this.matrixObject = new ArrayList<>();

        for (int i = 0; i < height; i++) {
            matrixObject.add(new ArrayList<>());
            for (int j = 0; j < width; j++) {
                matrixObject.get(i).add(null);
            }
        }

        dimension = new Dimension(height, width);
        this.height = height;
        this.width = width;
    }

    public Matrix(Dimension dimension) {
        this(dimension.getHeight(), dimension.getWidth());
    }

    public void set(int x, int y, E e) {
        check(x, y);
        this.matrixObject.get(x).set(y, e);
    }

    public boolean isInsideMatrix(Coordinate coordinate) {
        return dimension.isInsideRange(coordinate);
    }

    public void set(Coordinate coordinate, E e) {
        set(coordinate.getX(), coordinate.getY(), e);
    }

    private void check(int x, int y) {
        if (x >= height || y >= width)
            throw new IllegalArgumentException(
                    "x: " + x + ", y: " + y + " isn't inside the matrix size. (" + height + "," + width + ")");
    }

    public E get(int x, int y) {
        check(x, y);
        return matrixObject.get(x).get(y);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public E get(Coordinate coordinate) {
        return get(coordinate.getX(), coordinate.getY());
    }

    public E get(int i) {
        int j = 0;
        for (E e : this) {
            if (i == j) return e;
            j++;
        }
        return null;
    }

    public List<E> toList() {
        List<E> matrixAsList = new ArrayList<>();
        for (E e : this) {
            matrixAsList.add(e);
        }
        return matrixAsList;
    }

    public List<E> getAllNonNullValues() {
        List<E> allNonNullValues = new ArrayList<>();
        for (E e : this) {
            if (e == null) continue;
            allNonNullValues.add(e);
        }
        return allNonNullValues;
    }

    public Dimension getDimension() {
        return dimension;
    }

    @NonNull
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int currentX = 0;
            private int currentY = 0;

            @Override
            public boolean hasNext() {
                return (currentX < height && currentY < width);
            }

            @Override
            public E next() {
                try {
                    E e = get(currentX, currentY);
                    if (currentY == width - 1) {
                        currentY = 0;
                        currentX++;
                    } else {
                        currentY++;
                    }
                    return e;
                } catch (Exception e) {
                    throw new NoSuchElementException();
                }
            }
        };
    }
}
