package markus.wieland.scrabble.helper;

import java.util.HashMap;

import lombok.Getter;

@Getter
public class TreeNode {

    private final HashMap<Character, TreeNode> children;

    public TreeNode(){
        this.children = new HashMap<>();
    }

    public boolean contains(char letter) {
        return children.containsKey(letter);
    }

    public TreeNode get(char letter) {
        return children.get(letter);
    }

    public void add(char letter, TreeNode treeNode) {
        this.children.put(letter, treeNode);
    }

}
