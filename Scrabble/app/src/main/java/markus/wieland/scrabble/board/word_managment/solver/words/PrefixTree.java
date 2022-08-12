package markus.wieland.scrabble.board.word_managment.solver.words;

import java.util.HashSet;
import java.util.Set;

import markus.wieland.scrabble.game.letters.Letters;
import markus.wieland.scrabble.helper.Tree;

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

    public Set<Prefix> generatePrefix(int maxLength, Pattern pattern) {
        Set<Prefix> generatedPrefix = new HashSet<>();
        search(generatedPrefix, "", getRoot(), pattern, maxLength);
        return generatedPrefix;
    }

    public void search(Set<Prefix> generatedPrefix, String currentString, PrefixTreeNode currentNode, Pattern pattern, int maxLength) {
        generatedPrefix.add(new Prefix(currentString, currentNode));

        if (currentString.length() == maxLength) return;
        if (!pattern.canLetterBePlaced(currentNode.getLevel())) return;

        for (char letter : currentNode.getPossibleCharacter(pattern.get(currentNode.getLevel()))) {
            PrefixTreeNode prefixTreeNode = currentNode.get(letter);
            if (letter == Letters.JOKER) {
                for (char jokerLetter : Letters.getAllPossibleLetters()){
                    search(generatedPrefix, Character.toLowerCase(jokerLetter) + currentString, prefixTreeNode, pattern, maxLength);
                }
            }
            search(generatedPrefix, letter + currentString, prefixTreeNode, pattern, maxLength);
        }
    }

    @Override
    public PrefixTreeNode getRoot() {
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
