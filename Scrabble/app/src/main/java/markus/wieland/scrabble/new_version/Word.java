package markus.wieland.scrabble.new_version;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lombok.Getter;
import markus.wieland.scrabble.new_version.board.Letter;
import markus.wieland.scrabble.helper.Coordinate;
import markus.wieland.scrabble.helper.Range;

@Getter
public class Word implements Iterable<Letter>{

    private final Range range;
    private final List<Letter> letters;

    public Word() {
        this.range = new Range();
        this.letters = new ArrayList<>();
    }

    public void add(Letter letter, Coordinate coordinate) {
        this.letters.add(letter);
        this.range.update(coordinate);
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder word = new StringBuilder();
        for (Letter letter : letters) {
            word.append(letter.toString());
        }
        return word.toString();
    }

    public String getId() {
        return this + range.toString();
    }

    public int getLength(){
        return this.letters.size();
    }

    @NonNull
    @Override
    public Iterator<Letter> iterator() {
        return letters.iterator();
    }
}
