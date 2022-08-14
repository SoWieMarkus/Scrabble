package markus.wieland.scrabble.board.word_managment.solver.words;

import java.util.ArrayList;
import java.util.List;

public class Suffix {

    private final List<ValidWordLetter> letters;

    public Suffix(String part){
        this.letters = new ArrayList<>();
        for (char letter : part.toCharArray()) {
            letters.add(new ValidWordLetter(true, letter));
        }
    }

}
