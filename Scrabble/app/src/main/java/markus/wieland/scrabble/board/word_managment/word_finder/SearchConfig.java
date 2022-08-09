package markus.wieland.scrabble.board.word_managment.word_finder;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SearchConfig {

    private String word;
    private int steps;
    private Pattern pattern;

}
