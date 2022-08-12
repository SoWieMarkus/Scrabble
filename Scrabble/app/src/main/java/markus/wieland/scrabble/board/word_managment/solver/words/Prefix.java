package markus.wieland.scrabble.board.word_managment.solver.words;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import markus.wieland.scrabble.board.word_managment.SearchTreeNode;
import markus.wieland.scrabble.helper.Coordinate;

@Getter
@Setter
@AllArgsConstructor
public class Prefix {

    private final String prefixString;
    private final PrefixTreeNode prefixTreeNode;
    private SearchTreeNode searchTreeNode;

    private Coordinate startCoordinate;
    private Coordinate endCoordinateOfWholeCurrentWord;

    public Prefix(String prefixString, PrefixTreeNode prefixTreeNode) {
        this.prefixString = prefixString;
        this.prefixTreeNode = prefixTreeNode;
    }

}
