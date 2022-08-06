package markus.wieland.scrabble.new_version.word_finder.new_versiob;

import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchTreeNode {

    private boolean terminal;
    private SearchTreeNode parent;
    private final HashMap<Character, SearchTreeNode> children;

    public SearchTreeNode(SearchTreeNode parent) {
        this(parent, false);
    }

    public SearchTreeNode(SearchTreeNode parent, boolean terminal) {
        this.terminal = terminal;
        this.parent = parent;
        this.children = new HashMap<>();
    }


}
