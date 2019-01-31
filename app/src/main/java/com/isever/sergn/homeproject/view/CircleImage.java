package com.isever.sergn.homeproject.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.isever.sergn.homeproject.R;

public class CircleImage extends View {
    private final static String TAG = "#CircleImage#";

    private Paint paint;
    private int radius;
    private int color;
    private boolean pressed;
    private View.OnClickListener listener;

    public CircleImage(Context context) {
        super(context);
        init();
    }

    public CircleImage(Context context, @NonNull AttributeSet attrs) {
        super(context, attrs);
        initAttributes(context, attrs, 0, 0);
        init();
    }

    public CircleImage(Context context, @NonNull AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttributes(context, attrs, defStyleAttr, 0);
        init();
    }

    public CircleImage(Context context, @NonNull AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttributes(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void initAttributes(Context context, AttributeSet attrs,
                                int defStyleAttr, int defStyleRes) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleImage,
                defStyleAttr, defStyleRes);

        setRadius(typedArray.getResourceId(R.styleable.CircleImage_cv_Radius, 100));
        setColor(typedArray.getResourceId(R.styleable.CircleImage_cv_Color, Color.BLUE));

        typedArray.recycle();
    }


    private void init() {

        paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
    }

    private void setRadius(int radius) {
        this.radius = radius;
    }

    private void setColor(int color) {
        this.color = color;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(radius, radius, radius, paint);
        if (pressed)
            canvas.drawCircle(radius, radius, radius / 10, paint);
        else
            canvas.drawCircle(radius, radius, radius, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int Action = event.getAction();

        switch (Action) {
            case MotionEvent.ACTION_DOWN:
                pressed = true;
                invalidate();
                if (listener != null) listener.onClick(this);
                return true;
            case MotionEvent.ACTION_UP:
                pressed = false;
                invalidate();
                return true;
            default:
                invalidate();
                return true;
        }
    }

    // установка слушателя
    @Override
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }
}
