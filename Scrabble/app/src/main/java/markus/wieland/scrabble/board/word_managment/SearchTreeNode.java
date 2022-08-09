package markus.wieland.scrabble.board.word_managment;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.scrabble.helper.TreeNode;

@Getter
@Setter
public class SearchTreeNode extends TreeNode {

    private boolean terminal;

    public SearchTreeNode get(char letter) {
        return (SearchTreeNode) super.get(letter);
    }
}
