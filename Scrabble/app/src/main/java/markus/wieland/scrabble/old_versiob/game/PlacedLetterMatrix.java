package markus.wieland.scrabble.old_versiob.game;

import java.util.ArrayList;
import java.util.List;

import markus.wieland.scrabble.helper.Matrix;
import markus.wieland.scrabble.helper.Dimension;

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
