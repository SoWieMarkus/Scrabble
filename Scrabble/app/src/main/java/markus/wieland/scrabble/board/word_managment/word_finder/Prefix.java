package markus.wieland.scrabble.board.word_managment.word_finder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import markus.wieland.scrabble.helper.Coordinate;

@Getter
@AllArgsConstructor
public class Prefix {

    private String prefixString;
    private PrefixTreeNode prefixTreeNode;

}
