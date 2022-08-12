package markus.wieland.scrabble.board.word_managment.solver;

import android.app.Activity;

import java.util.List;

import lombok.Getter;
import markus.wieland.scrabble.board.Board;
import markus.wieland.scrabble.board.word_managment.SearchTree;
import markus.wieland.scrabble.board.word_managment.solver.board.SearchBoard;
import markus.wieland.scrabble.game.Inventory;

@Getter
public class WordFinder {

    private final SearchBoard searchBoard;
    private final Inventory inventory;

    public WordFinder(Board board, Inventory inventory) {
        this.searchBoard = new SearchBoard(board);
        this.inventory = inventory;
    }

    public List<Move> getPossibleMoves(Activity activity) {
        SearchTree searchTree = SearchTree.getInstance(activity);
        this.searchBoard.calculatePossibleCharacters(inventory, searchTree);
        this.searchBoard.searchPrefix(inventory, searchTree);
        List<Move> moves = this.searchBoard.searchWords(searchTree);
        return moves;
    }




}
