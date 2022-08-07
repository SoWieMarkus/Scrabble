package markus.wieland.scrabble.old_versiob.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import markus.wieland.scrabble.R;
import markus.wieland.scrabble.board.layout.BoardLayoutField;

public class FieldView extends View {

    private BoardLayoutField field;

    public FieldView(Context context, BoardLayoutField field) {
        super(context);
        this.field = field;
    }

    public FieldView(Context context) {
        super(context);
    }

    public FieldView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FieldView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void set(BoardLayoutField field) {
        this.field = field;
        invalidate();
    }

    @Override
    protected void onMeasure(int w, int h) {
        super.onMeasure(w, w);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (field == null) {
            draw(R.drawable.empty, canvas);
            draw(R.drawable.border, canvas);
            return;
        }

        draw(field.getType().getDrawable(), canvas);
        draw(R.drawable.border, canvas);
    }

    private void draw(@DrawableRes int drawableRes, Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), drawableRes);
        assert drawable != null;
        drawable.setBounds(0, 0, getWidth(), getHeight());
        drawable.draw(canvas);
    }
}
