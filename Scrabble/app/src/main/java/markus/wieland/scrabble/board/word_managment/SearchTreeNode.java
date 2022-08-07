package markus.wieland.scrabble.board.word_managment;

import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchTreeNode {

    private final HashMap<Character, SearchTreeNode> children;
    private boolean terminal;

    public SearchTreeNode() {
        this.children = new HashMap<>();
    }

}
