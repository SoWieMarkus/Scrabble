package markus.wieland.scrabble.helper;

import lombok.Getter;

@Getter
public class Tree {

    private final TreeNode root;

    public Tree(TreeNode root) {
        this.root = root;
    }

}
