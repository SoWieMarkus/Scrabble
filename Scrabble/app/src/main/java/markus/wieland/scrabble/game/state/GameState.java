package markus.wieland.scrabble.game.state;

import java.util.List;

import lombok.Getter;
import markus.wieland.scrabble.board.Field;
import markus.wieland.scrabble.board.layout.BoardLayout;
import markus.wieland.scrabble.board.layout.BoardLayoutField;
import markus.wieland.scrabble.game.Player;
import markus.wieland.scrabble.game.Scrabble;
import markus.wieland.scrabble.helper.Coordinate;
import markus.wieland.scrabble.helper.Dimension;

@Getter
public class GameState {

    private final Field[][] fields;
    private final Dimension dimension;
    private List<Player> players;
    private long currentPlayer;

    public GameState(Scrabble scrabble) {
        this(scrabble.getBoard().getDimension());
        for (Field field : scrabble.getBoard()) {
            this.fields[field.getCoordinate().getX()][field.getCoordinate().getY()] = field;
        }
    }

    public GameState(BoardLayout boardLayout) {
        this(boardLayout.getDimensions());

        for (int i = 0; i < dimension.getHeight(); i++) {
            for (int j = 0; j < dimension.getWidth(); j++) {
                fields[i][j] = new Field(new Coordinate(i,j));
            }
        }
        for (BoardLayoutField boardLayoutField : boardLayout.getSpecialFields()) {
            fields[boardLayoutField.getCoordinate().getX()][boardLayoutField.getCoordinate().getY()]
                    .setSpecialBlockType(boardLayoutField.getSpecialBlock());
        }

    }

    private GameState(Dimension dimension) {
        this.dimension = dimension;
        this.fields = new Field[dimension.getHeight()][dimension.getWidth()];
    }


}
