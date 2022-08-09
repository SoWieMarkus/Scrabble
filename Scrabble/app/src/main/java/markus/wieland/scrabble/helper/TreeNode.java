package markus.wieland.scrabble.helper;

import java.util.HashMap;

import lombok.Getter;
import markus.wieland.scrabble.game.letters.Letters;

@Getter
public class TreeNode {

    private final HashMap<Character, TreeNode> children;

    public TreeNode() {
        this.children = new HashMap<>();
    }

    public boolean contains(char letter) {
        return children.containsKey(letter);
    }

    public TreeNode get(char letter) {
        if (Character.isLowerCase(letter)) letter = Character.toUpperCase(letter);
        return children.get(letter);
    }

    public void add(char letter, TreeNode treeNode) {
        this.children.put(letter, treeNode);
    }

}
