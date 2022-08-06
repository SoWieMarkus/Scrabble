package markus.wieland.scrabble.new_version.word_finder.new_versiob;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JokerPossibility {

    private static final char NOT_USED = '-';

    private final Joker[] jokers = new Joker[2];
    private String word;

    public JokerPossibility(char firstJoker, String word) {
        this.jokers[0] = new Joker(word.indexOf("?"), firstJoker);
        this.word = word.replaceFirst("\\?", String.valueOf(firstJoker));
    }

    public JokerPossibility(Joker joker1, Joker joker2, String word) {
        this.jokers[0] = joker1;
        this.jokers[1] = joker2;
        this.word = word;
    }

    public boolean hasJoker() {
        return word.contains("?");
    }


    public JokerPossibility update(char joker) {
        if (jokers[1] != null) throw new IllegalArgumentException();
        Joker secondJoker = new Joker(word.indexOf("?"), joker);
        String newWord = word.replaceFirst("\\?", String.valueOf(joker));
        return new JokerPossibility(jokers[0], secondJoker, newWord);
    }

}
