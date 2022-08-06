package markus.wieland.scrabble.new_version.views;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import markus.wieland.scrabble.R;
import markus.wieland.scrabble.new_version.board.Letter;


public class LetterView extends ConstraintLayout implements View.OnTouchListener, View.OnDragListener {

    private Letter letter;
    private Paint paint;

    public LetterView(Context context) {
        this(context, null);
    }

    public LetterView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LetterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeView();
    }

    protected void initializeView() {
        setOnTouchListener(this);
        setOnDragListener(this);
        setWillNotDraw(false);
        paint = new Paint();
    }

    public void setLetter(Letter letter) {
        this.letter = letter;
        invalidate();
    }

    @Override
    protected void onMeasure(int width, int height) {
        //noinspection SuspiciousNameCombination
        super.onMeasure(width, width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (letter == null) {
            draw(R.drawable.empty_letter, canvas);
            return;
        }

        draw(R.drawable.letter_background, canvas);

        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(getWidth() * 0.75f);
        paint.setTextAlign(Paint.Align.CENTER);

        canvas.drawText(letter.toString(), getWidth() / 2, (int) ((canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2)), paint);

        paint.setTextSize(getWidth() * 0.25f);
        if (letter.getScore() == 0) return;
        canvas.drawText(String.valueOf(letter.getScore()), getWidth() *0.2f, (int) ((canvas.getHeight() * 0.2f) - ((paint.descent() + paint.ascent()) / 2)), paint);


    }

    private void draw(@DrawableRes int drawableRes, Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), drawableRes);
        assert drawable != null;
        drawable.setBounds(0, 0, getWidth(), getHeight());
        drawable.draw(canvas);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (letter == null) return false;


        ClipData.Item item = new ClipData.Item("Moving Letter");
        ClipData dragData = new ClipData(
                "Moving Letter",
                new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN},
                item);

        View.DragShadowBuilder myShadow = new View.DragShadowBuilder(this);

        v.startDragAndDrop(dragData, myShadow, v, 0);
        v.setTag(letter);
        setVisibility(GONE);
        return true;
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        return false;
    }

}

