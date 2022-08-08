package markus.wieland.scrabble.board.word_managment.word_finder;

import android.app.Activity;

import java.util.List;

import lombok.Getter;
import markus.wieland.scrabble.board.Board;
import markus.wieland.scrabble.board.word_managment.SearchTree;
import markus.wieland.scrabble.game.Inventory;

@Getter
public class WordFinder {

    private final SearchBoard searchBoard;
    private Inventory inventory;

    public WordFinder(Board board, Inventory inventory) {
        this.searchBoard = new SearchBoard(board);
        this.inventory = inventory;
    }

    public List<String> getPossibleMoves(Activity activity) {
        this.searchBoard.calculatePossibleCharacters(inventory, SearchTree.getInstance(activity));
        return null;
    }


}
