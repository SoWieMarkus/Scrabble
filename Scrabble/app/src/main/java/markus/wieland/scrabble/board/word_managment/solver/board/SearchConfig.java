package markus.wieland.scrabble.board.word_managment.solver.board;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.scrabble.board.word_managment.solver.words.Pattern;

@Setter
@Getter
public class SearchConfig {

    private String word;
    private int steps;
    private Pattern pattern;

    public SearchConfig() {
        this.word = "";
        this.steps = 0;
    }

}
