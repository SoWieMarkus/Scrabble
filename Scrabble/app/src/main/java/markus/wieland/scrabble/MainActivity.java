package markus.wieland.scrabble;

import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.Toast;

import lombok.AllArgsConstructor;
import markus.wieland.defaultappelements.uielements.activities.DefaultActivity;
import markus.wieland.scrabble.board.Board;
import markus.wieland.scrabble.board.word_managment.word_finder.WordFinder;
import markus.wieland.scrabble.game.Letter;
import markus.wieland.scrabble.helper.Coordinate;
import markus.wieland.scrabble.helper.FileReader;
import markus.wieland.scrabble.new_version.Scrabble;
import markus.wieland.scrabble.new_version.board.board_layout.BoardLayout;

public class MainActivity extends DefaultActivity {

    private Scrabble scrabble;

    public MainActivity() {
        super(R.layout.activity_main);
    }

    @Override
    public void bindViews() {

    }

    @Override
    public void initializeViews() {

    }

    @Override
    public void execute() {

        FileReader fileReader = new FileReader(this);

        /*MatrixView matrixView = findViewById(R.id.scrabble_view_fields);
        MatrixView matrixView2 = findViewById(R.id.scrabble_view_letters);

        matrixView2.setSizeChangedListener(new SizeChangedListener() {
            @Override
            public void onSizeChanged(float positionX, float positionY, float scaleFactor) {
                matrixView.setSize(positionX, positionY, scaleFactor);
            }
        });

        BoardLayout boardLayout = fileReader.read("board_layouts/default_board_layout.json", BoardLayout.class);
        matrixView.setNumColumns(boardLayout.getDimensions().getWidth());
        matrixView2.setNumColumns(boardLayout.getDimensions().getWidth());*/

        BoardLayout boardLayout = fileReader.read("board_layouts/default_board_layout.json", BoardLayout.class);
        Board boardMatrix = new Board(boardLayout);
        boardMatrix.get(new Coordinate(7, 7)).setLetter(new Letter(2, 'L'));
        boardMatrix.get(new Coordinate(7, 8)).setLetter(new Letter(1, 'U'));
        boardMatrix.get(new Coordinate(7, 9)).setLetter(new Letter(4, 'C'));
        boardMatrix.get(new Coordinate(7, 10)).setLetter(new Letter(1, 'I'));
        boardMatrix.get(new Coordinate(7, 11)).setLetter(new Letter(1, 'E'));

        boardMatrix.get(new Coordinate(6, 8)).setLetter(new Letter(4, 'K'));
        boardMatrix.get(new Coordinate(5, 8)).setLetter(new Letter(4, 'R'));
        boardMatrix.get(new Coordinate(5, 9)).setLetter(new Letter(4, 'U'));
        boardMatrix.get(new Coordinate(4, 8)).setLetter(new Letter(4, 'A'));
        boardMatrix.get(new Coordinate(4, 9)).setLetter(new Letter(4, 'R'));
        boardMatrix.get(new Coordinate(4, 10)).setLetter(new Letter(4, 'M'));
        boardMatrix.get(new Coordinate(3, 8)).setLetter(new Letter(4, 'M'));
        boardMatrix.get(new Coordinate(8, 8)).setLetter(new Letter(4, 'S'));

        long millis = System.currentTimeMillis();
        WordFinder wordFinder = new WordFinder(boardMatrix);


        Log.e("TIME", (System.currentTimeMillis()-millis)+"ms");

        wordFinder.getSearchBoard().print();
        /*Inventory inventory = new Inventory();
        List<Letter> letters = new ArrayList<>();
        letters.add(new Letter(1, 'H'));
        letters.add(new Letter(1, 'O'));
        letters.add(new Letter(1, 'F'));
        letters.add(new Letter(1, 'A'));
        letters.add(new Letter(1, 'R'));
        letters.add(new Letter(1, 'Z'));
        letters.add(new Letter(1, 'T'));
        inventory.add(letters)*/
        /*WordFinder wordFinder = new WordFinder(this, boardMatrix, inventory);


        List<String> strings = inventory.getAllPossibleCombinations(7);
        List<String> strings2 = inventory.getAllPossibleCombinations(6);

        int x = 0;


        strings.addAll(null);*/





        /*Matrix<SpecialBlockView> specialBlockViews = new Matrix<>(boardLayout.getDimensions());
        for (int x = 0; x < boardLayout.getDimensions().getHeight(); x++) {
            for (int y = 0; y < boardLayout.getDimensions().getWidth(); y++) {
                SpecialBlockView specialBlockView =  new SpecialBlockView(this);
                //specialBlockView.setOnDragListener(new CustomDragListener(new Coordinate(x,y)));
                specialBlockViews.set(x, y,specialBlockView);
            }
        }

        Matrix<LetterView> letterViews = new Matrix<>(boardLayout.getDimensions());
        for (int x = 0; x < boardLayout.getDimensions().getHeight(); x++) {
            for (int y = 0; y < boardLayout.getDimensions().getWidth(); y++) {
                LetterView letterView =  new LetterView(this);
                letterView.setOnDragListener(new CustomDragListener(new Coordinate(x,y)));
                letterViews.set(x, y,letterView);
            }
        }

        matrixView.setAdapter(new ScrabbleAdapter<>(specialBlockViews));
        matrixView2.setAdapter(new ScrabbleAdapter<>(letterViews));

        scrabble = new Scrabble(this, boardLayout, specialBlockViews, letterViews);
        ScrabbleView scrabbleViewInventory = findViewById(R.id.scrabble_view_inventory);

        /*FileReader fileReader = new FileReader(this);


        BoardLayout boardLayout = fileReader.read("board_layouts/default_board_layout.json", BoardLayout.class);


        ScrabbleView scrabbleView = findViewById(R.id.scrabble_view_fields);

        scrabbleView.setNumColumns(boardLayout.getDimensions().getWidth());

        Matrix<FieldView> fieldViews = new Matrix<>(boardLayout.getDimensions());
        for (int x = 0; x < boardLayout.getDimensions().getHeight(); x++) {
            for (int y = 0; y < boardLayout.getDimensions().getWidth(); y++) {
                fieldViews.set(x,y,new FieldView(this, (BoardLayoutField) null));
                fieldViews.get(x,y).setOnDragListener(new CustomDragListener(new Coordinate(x,y)));
            }
        }

        scrabbleViewInventory.setNumColumns(7);

        ScrabbleGame scrabbleGame = new ScrabbleGame(this);

        scrabbleView.setAdapter(new ScrabbleAdapter<>(fieldViews));



        findViewById(R.id.activity_main_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidationResult validationResult = scrabble.validate();
                Toast.makeText(MainActivity.this, validationResult.getErrorMessage(MainActivity.this)+"", Toast.LENGTH_SHORT).show();
            }
        });


        boolean valid = WordValidator.validate(this, "WORT");







        Inventory inventory = new Inventory();
        List<Letter> letters = new ArrayList<>();
        letters.add(new Letter(1, 'E'));
        letters.add(new Letter(1, 'E'));
        letters.add(new Letter(1, 'E'));
        letters.add(new Letter(1, 'S'));
        letters.add(new Letter(1, 'S'));
        letters.add(new Letter(1, 'S'));
        letters.add(new Letter(1, 'S'));
        inventory.add(new ArrayList<>());
        List<LetterView> letterViews2 = new ArrayList<>();
        for (Letter letter : letters) {
            LetterView letterView2 = new LetterView(this);
            letterView2.setLetter(letter);
            letterViews2.add(letterView2);
        }


        scrabbleViewInventory.setNumColumns(7);
        scrabbleViewInventory.setAdapter(new ScrabbleHandAdapter(letterViews2));*/

    }

    @AllArgsConstructor
    class CustomDragListener implements View.OnDragListener {


        private Coordinate coordinate;

        @Override
        public boolean onDrag(View v, DragEvent event) {
            if (event.getAction() == DragEvent.ACTION_DROP) {
                View view = (View) event.getLocalState();
                markus.wieland.scrabble.new_version.board.Letter letter = (markus.wieland.scrabble.new_version.board.Letter) view.getTag();
                Toast.makeText(MainActivity.this, coordinate.toString(), Toast.LENGTH_SHORT).show();
                //
                boolean wasGood = scrabble.setLetter(coordinate, letter);

                if (!wasGood) {
                    view.setVisibility(View.VISIBLE);
                } else {
                    //((ViewGroup)view.getParent()).removeView(view);
                }

                v.invalidate();

                return wasGood;
            }
            return true;
        }
    }
}