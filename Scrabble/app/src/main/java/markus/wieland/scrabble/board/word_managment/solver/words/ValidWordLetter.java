package markus.wieland.scrabble.board.word_managment.solver.words;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ValidWordLetter {

    private final boolean concrete;
    private final char letter;


}
