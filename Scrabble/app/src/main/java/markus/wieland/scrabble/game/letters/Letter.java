package markus.wieland.scrabble.game.letters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import markus.wieland.scrabble.game.letters.Letters;

@Getter
@AllArgsConstructor
public class Letter {

    private final int score;
    private final char value;

    public boolean isJoker() {
        return value == Letters.JOKER;
    }

}
