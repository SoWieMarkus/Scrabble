package markus.wieland.scrabble.new_version.word_finder;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import markus.wieland.scrabble.new_version.Word;
import markus.wieland.scrabble.new_version.board.BoardMatrix;
import markus.wieland.scrabble.new_version.board.Field;
import markus.wieland.scrabble.new_version.board.Letter;
import markus.wieland.scrabble.new_version.helper.Coordinate;
import markus.wieland.scrabble.new_version.helper.Direction;
import markus.wieland.scrabble.new_version.helper.Matrix;
import markus.wieland.scrabble.new_version.word_finder.Move;
import markus.wieland.scrabble.new_version.word_finder.ScrabbleWordFinderLetterField;
import markus.wieland.scrabble.new_version.word_finder.ScrabbleWordFinderSearchField;
import markus.wieland.scrabble.validation.Characters;
import markus.wieland.scrabble.validation.ValidWord;

public class ScrabbleWordFinder {

    private ScrabbleWordFinder() {
    }

    public static void getPossibleMoves(Activity activity, Characters characters, BoardMatrix boardMatrix) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Matrix<ScrabbleWordFinderField> scrabbleWordFinderFields = new Matrix<>(boardMatrix.getDimension());

                boolean[][] searchMask = setupSearchMatrix(boardMatrix, scrabbleWordFinderFields);

                for (int i = 0; i < boardMatrix.getHeight(); i++) {
                    for (int j = 0; j < boardMatrix.getWidth(); j++) {
                        if (!searchMask[i][j]) continue;

                        Coordinate coordinate = new Coordinate(i, j);
                        discoverFields(boardMatrix, scrabbleWordFinderFields,Direction.UP, coordinate);
                        discoverFields(boardMatrix, scrabbleWordFinderFields,Direction.LEFT, coordinate);
                    }
                }

                for (ScrabbleWordFinderField scrabbleWordFinderField : scrabbleWordFinderFields) {
                    if (!(scrabbleWordFinderField instanceof ScrabbleWordFinderSearchField)) continue;

                    ScrabbleWordFinderSearchField scrabbleWordFinderSearchField = (ScrabbleWordFinderSearchField) scrabbleWordFinderField;

                    if (scrabbleWordFinderSearchField.isReachedFromDown()) {
                        discoverPatterns(boardMatrix, scrabbleWordFinderFields, scrabbleWordFinderSearchField, Direction.DOWN);
                    }

                    if (scrabbleWordFinderSearchField.isReachedFromRight()) {
                        discoverPatterns(boardMatrix, scrabbleWordFinderFields, scrabbleWordFinderSearchField, Direction.RIGHT);
                    }
                }


                int amount = 0;
                for (ScrabbleWordFinderField scrabbleWordFinderField : scrabbleWordFinderFields) {
                    if (!(scrabbleWordFinderField instanceof ScrabbleWordFinderSearchField)) continue;

                    amount++;
                }


                int current = 0;
                for (ScrabbleWordFinderField scrabbleWordFinderField : scrabbleWordFinderFields) {
                    if (!(scrabbleWordFinderField instanceof ScrabbleWordFinderSearchField)) continue;

                    long millis = System.currentTimeMillis();

                    ScrabbleWordFinderSearchField scrabbleWordFinderSearchField = (ScrabbleWordFinderSearchField) scrabbleWordFinderField;
                    scrabbleWordFinderSearchField.search(activity,characters);
                    Log.e("ENDE", (System.currentTimeMillis()-millis)+ " ms to find possible words");
                    Log.e("Progess", current + "/" + amount);
                    current++;
                }
            }
        };
        thread.start();

    }

    private static void discoverPatterns(BoardMatrix boardMatrix, Matrix<ScrabbleWordFinderField> scrabbleWordFinderFields, ScrabbleWordFinderSearchField scrabbleWordFinderSearchField, Direction direction) {
        int lettersUsed = 0;
        StringBuilder pattern = new StringBuilder();

        Coordinate coordinate = scrabbleWordFinderSearchField.getCoordinate();

        boolean split = false;

        while (boardMatrix.isInsideMatrix(coordinate) && lettersUsed < 7 && pattern.length() < 9) {

            ScrabbleWordFinderField scrabbleWordFinderField = scrabbleWordFinderFields.get(coordinate);
            pattern.append(scrabbleWordFinderField.getPatternChar());

            if (scrabbleWordFinderField instanceof ScrabbleWordFinderLetterField
                    || (scrabbleWordFinderField instanceof ScrabbleWordFinderSearchField
                    && ((ScrabbleWordFinderSearchField) scrabbleWordFinderField).isDirectlyAttached()))
                split = true;

            if (!(scrabbleWordFinderField instanceof ScrabbleWordFinderLetterField))
                lettersUsed++;

            coordinate = coordinate.getNextCoordinate(direction);
            if (split)
                ((ScrabbleWordFinderSearchField) scrabbleWordFinderSearchField).add(direction, pattern.toString());
        }

        if (scrabbleWordFinderSearchField.getPatterns(direction).isEmpty())
            scrabbleWordFinderSearchField.add(direction, pattern.toString());
    }

    private static void discoverFields(BoardMatrix boardMatrix, Matrix<ScrabbleWordFinderField> scrabbleWordFinderFields, Direction direction, Coordinate coordinate) {
        int lettersPlaced = 0;

        while (boardMatrix.isInsideMatrix(coordinate) && lettersPlaced < 7) {

            ScrabbleWordFinderField scrabbleWordFinderField = scrabbleWordFinderFields.get(coordinate);
            coordinate = coordinate.getNextCoordinate(direction);

            if (scrabbleWordFinderField instanceof ScrabbleWordFinderLetterField) continue;

            lettersPlaced++;
            if (scrabbleWordFinderField instanceof ScrabbleWordFinderSearchField) {
                ((ScrabbleWordFinderSearchField) scrabbleWordFinderField).setDiscoveredFrom(direction);
                continue;
            }

            ScrabbleWordFinderSearchField scrabbleWordFinderSearchField = new ScrabbleWordFinderSearchField(coordinate.getNextCoordinate(direction.getOppositeDirection()), lettersPlaced == 1);
            scrabbleWordFinderSearchField.setReachedFromDown(true);
            scrabbleWordFinderFields.set(coordinate.getNextCoordinate(direction.getOppositeDirection()), scrabbleWordFinderSearchField);

        }
    }

    private static boolean[][] setupSearchMatrix(BoardMatrix boardMatrix, Matrix<ScrabbleWordFinderField> scrabbleWordFinderFields){
        boolean[][] searchMask = new boolean[boardMatrix.getHeight()][boardMatrix.getWidth()];

        for (Field field : boardMatrix) {
            Letter letter = field.getLetter();
            if (letter == null) {
                scrabbleWordFinderFields.set(field.getCoordinate(), new ScrabbleWordFinderField(field.getCoordinate()));
                continue;
            }

            scrabbleWordFinderFields.set(field.getCoordinate(), new ScrabbleWordFinderLetterField(field.getCoordinate(), letter.getValue()));

            for (Direction direction : Objects.requireNonNull(Direction.class.getEnumConstants())) {
                Coordinate coordinate = field.getCoordinate().getNextCoordinate(direction);
                if (!boardMatrix.isInsideMatrix(coordinate) || boardMatrix.get(coordinate).getLetter() != null)
                    continue;
                searchMask[coordinate.getX()][coordinate.getY()] = true;
            }

        }
        return searchMask;
    }


}
