package markus.wieland.scrabble.old_versiob.game;

import java.util.List;

import lombok.Getter;
import markus.wieland.scrabble.helper.Matrix;
import markus.wieland.scrabble.new_version.board.Letter;

@Getter
public class Inventory {

    private final Matrix<Letter> letters;
    private static final int AMOUNT_OF_POSSIBLE_LETTERS_IN_INVENTORY = 7;

    public Inventory() {
        this.letters = new Matrix<>(1,AMOUNT_OF_POSSIBLE_LETTERS_IN_INVENTORY);
    }

    public void add(List<Letter> newLetters) {
        int index = 0;
        for (int i = 0; i < AMOUNT_OF_POSSIBLE_LETTERS_IN_INVENTORY; i++) {
            Letter letter = letters.get(0,i);
            if (letter != null) continue;

            letters.set(0,i, newLetters.get(index));
            index++;
            if (index == newLetters.size()) break;

        }

        if (index != newLetters.size()) throw new IllegalStateException("Not all letters where added to the list!");
    }

    public Parts getParts(){
        List<Letter> lettersAsList = getLetters().toList();
        StringBuilder lettersAsString = new StringBuilder();
        for (Letter letter : lettersAsList) {
            lettersAsString.append(letter.getValue());
        }
        return new Parts(lettersAsString.toString());
    }

}
