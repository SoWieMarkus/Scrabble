package markus.wieland.scrabble.board.word_managment.solver;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import markus.wieland.scrabble.game.letters.Letters;
import markus.wieland.scrabble.helper.TreeNode;

@Getter
public class PrefixTreeNode extends TreeNode {

    private final int level;

    public PrefixTreeNode(int level) {
        this.level = level;
    }

    public Set<Character> getPossibleCharacter(Set<Character> characters) {
        HashSet<Character> charactersOfNode = new HashSet<>();
        if (characters == null) return getChildren().keySet();
        if (getChildren().containsKey(Letters.JOKER)) charactersOfNode.add(Letters.JOKER);

        for (char letter : characters) {
            if (getChildren().containsKey(letter)) {
                charactersOfNode.add(letter);
            }
        }
        return charactersOfNode;
    }

    @Override
    public PrefixTreeNode get(char letter) {
        return (PrefixTreeNode) super.get(letter);
    }

}
