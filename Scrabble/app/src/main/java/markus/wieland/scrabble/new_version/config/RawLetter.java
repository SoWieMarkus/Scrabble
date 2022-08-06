package markus.wieland.scrabble.new_version.config;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.scrabble.new_version.board.Letter;

@Getter
@Setter
public class RawLetter {

    private char value;
    private int score;
    private int amount;

    public Letter getLetter() {
        return new Letter(score, value);
    }
}
