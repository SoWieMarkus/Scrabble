package markus.wieland.scrabble.old_versiob.game;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import markus.wieland.scrabble.R;

public class LetterViewOld extends ConstraintLayout implements View.OnTouchListener, View.OnDragListener {

    private ImageView background;
    private TextView value;
    private TextView points;
    private Letter letter;

    public LetterViewOld(Context context) {
        this(context, null);
    }

    public LetterViewOld(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LetterViewOld(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeView();
    }

    protected void initializeView() {
        setOnTouchListener(this);
        setOnDragListener(this);
        LayoutInflater.from(getContext()).inflate(R.layout.layout_letter_view, this);
        //background = findViewById(R.id.layout_letter_view_background);
        //value = findViewById(R.id.layout_letter_view_value);
    }

    public void setLetter(Letter letter) {
        this.letter = letter;
        this.value.setText(letter.toString());
        //this.points.setText(letter.getPoints() + "");
        //this.points.setVisibility(letter.getPoints() == 0 ? GONE : VISIBLE);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
        ClipData dragData = new ClipData(
                (CharSequence) v.getTag(),
                new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN},
                item);

        View.DragShadowBuilder myShadow = new View.DragShadowBuilder(this);

        v.startDragAndDrop(dragData,  // The data to be dragged
                myShadow,  // The drag shadow builder
                null,      // No need to use local data
                0          // Flags (not currently used, set to 0)
        );
        setVisibility(GONE);
        return true;
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        return false;
    }


}
