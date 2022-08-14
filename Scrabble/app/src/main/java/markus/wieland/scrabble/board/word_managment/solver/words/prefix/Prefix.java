package markus.wieland.scrabble.board.word_managment.solver.words.prefix;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import markus.wieland.scrabble.board.word_managment.SearchTreeNode;
import markus.wieland.scrabble.game.letters.Word;
import markus.wieland.scrabble.game.moves.Move;
import markus.wieland.scrabble.helper.Coordinate;

@Getter
@Setter
@AllArgsConstructor
public class Prefix {

    private final String prefixString;
    private String afterwards;
    private final PrefixTreeNode prefixTreeNode;
    private SearchTreeNode searchTreeNode;

    private Coordinate startCoordinate;
    private Coordinate endCoordinateOfWholeCurrentWord;

    public Prefix(String prefixString, PrefixTreeNode prefixTreeNode) {
        this.prefixString = prefixString;
        this.prefixTreeNode = prefixTreeNode;
    }

}
