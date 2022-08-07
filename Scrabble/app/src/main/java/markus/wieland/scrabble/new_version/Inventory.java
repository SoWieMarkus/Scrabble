package markus.wieland.scrabble.new_version;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import markus.wieland.scrabble.new_version.board.Letter;
import markus.wieland.scrabble.helper.Matrix;
import markus.wieland.scrabble.old_versiob.validation.Characters;

public class Inventory extends Matrix<Letter> {

    private static final int DIMENSION_WIDTH = 7;
    private static final int DIMENSION_HEIGHT = 1;

    private final List<String> allPossibleCombinations;

    public Inventory() {
        super(DIMENSION_HEIGHT, DIMENSION_WIDTH);
        allPossibleCombinations = new ArrayList<>();
    }

    public Characters getCharacters() {
        Characters characters = new Characters();
        for (Letter letter : this) {
            if (letter == null) continue;
            characters.add(letter.getValue());
        }

        return characters;
    }

    public char[] getAsCharArray(){
        int size = 0;
        for (Letter letter : this) {
            if (letter == null) continue;
            size++;
        }
        if (size == 0) return new char[]{};
        int index = 0;
        char[] chars = new char[size];
        for (Letter letter : this) {
            if (letter == null) continue;
            chars[index] = letter.getValue();
            index++;
        }
        return chars;
    }

    public void add(List<Letter> letters) {
        if (letters.size() > 7) throw new IllegalStateException();
        for (int i = 0; i < DIMENSION_WIDTH; i++) {
            if (get(0, i) != null) continue;
            if (letters.isEmpty()) break;
            set(0, i, letters.remove(0));

        }
        //TODO ordentlich amchen
        calculateAllPossibleCombinations();
        if (letters.isEmpty()) return;
        throw new IllegalStateException();
    }

    public List<String> getAllPossibleCombinations(int maxLength) {
        List<String> allPossibleCombinationsByLength = new ArrayList<>();
        for (String string : allPossibleCombinations) {
            if (string.length() <= maxLength) allPossibleCombinationsByLength.add(string);
        }
        return allPossibleCombinationsByLength;
    }

    public void calculateAllPossibleCombinations(){
        char[] chars = getAsCharArray();
        allPossibleCombinations.clear();
        int max = (int) Math.pow(2, chars.length);
        for (int i = 0; i < max; i++) {
            String binaryString = Integer.toBinaryString(i);
            StringBuilder setStringBuilder = new StringBuilder();
            for (int j = 0; j < binaryString.length(); j++) {
                if (binaryString.charAt(binaryString.length() - (j+1)) == '1') {
                    setStringBuilder.append(chars[j]);
                }
            }

            String set = setStringBuilder.toString();
            allPossibleCombinations.addAll(permute(set));
        }
    }

    public static Set<String> permute(String chars)
    {
        Set<String> set = new TreeSet<>();

        if (chars.length() == 0) {
            set.add("");
        }
        else if (chars.length() == 1) {
            set.add(chars);
        }
        else {

            for (int i=0; i<chars.length(); i++)
            {
                // Remove the character at index i from the string
                String pre = chars.substring(0, i);
                String post = chars.substring(i+1);
                String remaining = pre+post;

                // Recurse to find all the permutations of the remaining chars
                for (String permutation : permute(remaining))
                {
                    // Concatenate the first character with the permutations of the remaining chars
                    set.add(chars.charAt(i) + permutation);
                }
            }
        }
        return set;
    }
}
