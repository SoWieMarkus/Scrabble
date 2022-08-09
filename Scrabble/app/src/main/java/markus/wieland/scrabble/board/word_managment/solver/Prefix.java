package markus.wieland.scrabble.board.word_managment.solver;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Prefix {

    private String prefixString;
    private PrefixTreeNode prefixTreeNode;

}
