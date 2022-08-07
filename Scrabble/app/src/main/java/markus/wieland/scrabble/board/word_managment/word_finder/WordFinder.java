package markus.wieland.scrabble.board.word_managment.word_finder;

import lombok.Getter;
import markus.wieland.scrabble.board.Board;
import markus.wieland.scrabble.new_version.Inventory;

@Getter
public class WordFinder {

    private final SearchBoard searchBoard;
    private Inventory inventory;

    public WordFinder(Board board) {
        this.searchBoard = new SearchBoard(board);
    }


}
