package markus.wieland.scrabble.new_version.word_finder.new_versiob;

import java.util.ArrayList;
import java.util.List;

public class Pattern {

    private final List<String> patterns;

    public Pattern() {
        this.patterns = new ArrayList<>();
    }

    public boolean isLongEnough(int length) {
        return patterns.size() >= length;
    }

    public void add(String pattern) {
        this.patterns.add(pattern);
    }


    public String toString(int length) {
        int currentStep = 0;
        if (patterns.isEmpty()) return "";
        StringBuilder patternAsString = new StringBuilder();
        for (int i = patterns.size() - (patterns.size() - length) - 1; i >= 0; i--) {
            patternAsString.append(patterns.get(i));
            currentStep++;
            if (currentStep == length) break;
        }
        return patternAsString.toString();

    }


}
