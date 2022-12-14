package markus.wieland.scrabble.board.layout;

import androidx.annotation.NonNull;

import java.util.Iterator;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.scrabble.helper.Dimension;

@Getter
@Setter
public class BoardLayout implements Iterable<BoardLayoutField> {

    private Dimension dimensions;
    private List<BoardLayoutField> specialFields;

    @NonNull
    @Override
    public Iterator<BoardLayoutField> iterator() {
        return specialFields.iterator();
    }
}
