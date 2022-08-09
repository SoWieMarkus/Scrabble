package markus.wieland.scrabble.board.word_managment.word_finder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import markus.wieland.scrabble.helper.Tree;
import markus.wieland.scrabble.helper.TreeNode;

public class PrefixTree extends Tree {

    public PrefixTree(char[] letters) {
        super(new PrefixTreeNode(0));
        Set<String> possiblePrefix = generateAllPrefix(letters);
        for (String prefix : possiblePrefix) {
            add(prefix);
        }
    }

    public void add(String prefix) {
        PrefixTreeNode currentNode = getRoot();
        int index = 1;
        for (char letter : prefix.toCharArray()) {
            if (!currentNode.contains(letter)) {
                currentNode.add(letter, new PrefixTreeNode(index));
            }
            currentNode = currentNode.get(letter);
            index++;
            assert currentNode != null;
        }
    }

    public Set<String> generatePrefix(int maxLength, HashMap<Integer, Set<Character>> pattern) {
        if (getRoot().getPossibleCharacter())


        for (Map.Entry<Character, TreeNode> entry : getRoot().getChildren().entrySet()) {

        }

        return new HashSet<>();
    }

    @Override
    public PrefixTreeNode getRoot(){
        return (PrefixTreeNode) super.getRoot();
    }

    private Set<String> generateAllPrefix(char[] letters) {
        return generateAllPrefix(String.valueOf(letters));
    }

    private Set<String> generateAllPrefix(String prefix) {
        Set<String> permutations = new HashSet<>();

        if (prefix.isEmpty()) {
            permutations.add("");
            return permutations;
        }

        if (prefix.length() == 1) {
            permutations.add(prefix);
            return permutations;
        }

        for (int i = 0; i < prefix.length(); i++) {
            String pre = prefix.substring(0, i);
            String post = prefix.substring(i + 1);
            String remaining = pre + post;
            for (String permutation : generateAllPrefix(remaining)) {
                permutations.add(prefix.charAt(i) + permutation);
            }
        }
        return permutations;
    }


}
