package markus.wieland.scrabble.new_version.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.GridView;

import androidx.annotation.NonNull;

public class MatrixView extends GridView implements ScaleGestureDetector.OnScaleGestureListener {

    private static final float MIN_SCALE_FACTOR = 1.0f;
    private static final float MAX_SCALE_FACTOR = 1.75f;

    private static final int INVALID_POINTER_ID = -1;
    private int currentPointerId;

    private ScaleGestureDetector scaleGestureDetector;

    private float scaleFactor;

    private float width;
    private float height;

    private float lastTouchX;
    private float lastTouchY;

    private float currentPositionX;
    private float currentPositionY;

    private float maxWidth;
    private float maxHeight;

    private SizeChangedListener sizeChangedListener;

    public MatrixView(Context context) {
        this(context, null);
    }

    public MatrixView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MatrixView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize() {
        this.scaleGestureDetector = new ScaleGestureDetector(getContext(), this);
        this.currentPointerId = INVALID_POINTER_ID;
        this.scaleFactor = MIN_SCALE_FACTOR;
        this.maxHeight = 0.0f;
        this.maxWidth = 0.0f;
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        scaleGestureDetector.onTouchEvent(event);

        int pointerIndex;

        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                currentPointerId = INVALID_POINTER_ID;
                break;
            case MotionEvent.ACTION_DOWN:
                lastTouchX = event.getX();
                lastTouchY = event.getY();
                currentPointerId = event.getPointerId(0);
                break;

            case MotionEvent.ACTION_MOVE:
                pointerIndex = event.findPointerIndex(currentPointerId);
                float x = event.getX(pointerIndex);
                float y = event.getY(pointerIndex);

                currentPositionX += x - lastTouchX;
                currentPositionY += y - lastTouchY;

                lastTouchX = x;
                lastTouchY = y;

                // TODO sure?
                if (currentPositionX > 0.0f) currentPositionX = 0.0f;
                if (currentPositionX < maxWidth) currentPositionX = maxWidth;
                if (currentPositionY > 0.0f) currentPositionY = 0.0f;
                if (currentPositionY < maxHeight) currentPositionY = maxHeight;


                update();
                invalidate();
                break;

            case MotionEvent.ACTION_POINTER_UP:
                pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
                int pointerId = event.getPointerId(pointerIndex);
                if (pointerId == currentPointerId) {
                    int newPointerIndex = pointerIndex == 0 ? 1 : 0;
                    lastTouchX = event.getX(newPointerIndex);
                    lastTouchY = event.getY(newPointerIndex);
                    currentPointerId = event.getPointerId(newPointerIndex);
                }
                break;

        }
        return true;
    }

    public void update(){
        if (sizeChangedListener != null)
            sizeChangedListener.onSizeChanged(currentPositionX, currentPositionY, scaleFactor);
    }

    public void setSizeChangedListener(SizeChangedListener sizeChangedListener) {
        this.sizeChangedListener = sizeChangedListener;
    }

    public void setSize(float currentPositionX, float currentPositionY, float scaleFactor) {
        this.currentPositionX = currentPositionX;
        this.currentPositionY = currentPositionY;
        this.scaleFactor = scaleFactor;
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(currentPositionX, currentPositionY);
        canvas.scale(scaleFactor, scaleFactor);
        canvas.restore();
    }

    @Override
    protected void dispatchDraw(@NonNull Canvas canvas) {
        canvas.save();
        if (scaleFactor == MIN_SCALE_FACTOR) {
            currentPositionX = 0.0f;
            currentPositionY = 0.0f;
        }
        canvas.translate(currentPositionX, currentPositionY);
        canvas.scale(scaleFactor, scaleFactor);
        super.dispatchDraw(canvas);
        canvas.restore();
        invalidate();
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        scaleFactor = detector.getScaleFactor() < 1 ? MIN_SCALE_FACTOR : MAX_SCALE_FACTOR;
        maxWidth = width - (width * scaleFactor);
        maxHeight = height - (height * scaleFactor);
        invalidate();
        update();
        return false;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {

        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {

    }
}
