package markus.wieland.scrabble.new_version.word_finder.new_versiob;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Prefix {

    private final String prefix;
    private final String word;
    private final SearchTreeNode searchTreeNode;


}
