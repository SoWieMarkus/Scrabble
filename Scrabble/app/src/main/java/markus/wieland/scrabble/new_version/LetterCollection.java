package markus.wieland.scrabble.new_version;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import markus.wieland.scrabble.new_version.board.Letter;


public class LetterCollection {

    private final List<Letter> letters;

    public LetterCollection(List<Letter> letters) {
        this.letters = letters;
        Collections.shuffle(this.letters);
    }

    public List<Letter> getLetters(int amount) {
        if (amount < 0 || amount > 7) throw new IllegalArgumentException("Can't get more than 7 or less than 0 zero stones!");
        List<Letter> lettersForPlayer = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            if (this.letters.isEmpty()) break;
            lettersForPlayer.add(this.letters.remove(0));
        }
        return lettersForPlayer;
    }


}
