package markus.wieland.scrabble.game;

import java.util.ArrayList;
import java.util.List;

import markus.wieland.scrabble.new_version.helper.Matrix;
import markus.wieland.scrabble.new_version.helper.Dimension;

public class PlacedLetterMatrix extends Matrix<PlacedLetter> {

    public PlacedLetterMatrix(int height, int width) {
        super(height, width);
    }

    public PlacedLetterMatrix(Dimension dimension) {
        super(dimension);
    }

    public List<PlacedLetter> getNonConcreteLetters() {
        List<PlacedLetter> nonConcreteLetters = new ArrayList<>();
        for (PlacedLetter letter : this) {
            if (letter.isConcrete()) continue;

            nonConcreteLetters.add(letter);
        }
        return nonConcreteLetters;
    }

}
