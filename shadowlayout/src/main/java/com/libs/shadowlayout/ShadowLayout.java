package com.libs.shadowlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by 4530 on 23/09/2017.
 */

public class ShadowLayout extends FrameLayout {

    private Paint mShadowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float shadowOffset;
    private int shadowColor;
    private boolean shadowed;

    View child;
    Bitmap shadowBitmap;

    Bitmap convertBitmap;
    Canvas convertCanvas;

    public ShadowLayout(Context context){
        super(context);

        init();
    }

    public ShadowLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ShadowLayout, 0, 0);

        try {
            shadowed = a.getBoolean(R.styleable.ShadowLayout_shadowed, true);
            shadowColor = Color.argb(a.getInteger(R.styleable.ShadowLayout_shadowopacity, 50), 0, 0, 0);
            mShadowPaint.setColorFilter(new PorterDuffColorFilter(shadowColor, PorterDuff.Mode.SRC_IN));
            shadowOffset = a.getFloat(R.styleable.ShadowLayout_shadowoffset, 5);
        }finally {
            a.recycle();
        }
    }

    public ShadowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ShadowLayout, 0, 0);

        try {
            shadowed = a.getBoolean(R.styleable.ShadowLayout_shadowed, true);
            shadowColor = Color.argb(a.getInteger(R.styleable.ShadowLayout_shadowopacity, 50), 0, 0, 0);
            mShadowPaint.setColorFilter(new PorterDuffColorFilter(shadowColor, PorterDuff.Mode.SRC_IN));
            shadowOffset = a.getFloat(R.styleable.ShadowLayout_shadowoffset, 5);
        }finally {
            a.recycle();
        }
    }

    private void init(){
        shadowColor = Color.argb(50, 0, 0, 0);
        shadowOffset = 5;
        mShadowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mShadowPaint.setColorFilter(new PorterDuffColorFilter(shadowColor, PorterDuff.Mode.SRC_IN));
        setWillNotDraw(false);
        shadowed = false;
    }

    @Override
    protected void onDraw(Canvas canvas){
        if(shadowed){
            for(int i = 0; i < getChildCount(); i++){
                child = getChildAt(i);
                shadowBitmap = getBitmapFromView(child);
                canvas.drawBitmap(shadowBitmap, child.getLeft() + shadowOffset, child.getTop() + shadowOffset, mShadowPaint);
            }
        }
    }

    private Bitmap getBitmapFromView(View view) {
        if(convertBitmap == null){
            convertBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
            convertCanvas = new Canvas(convertBitmap);
        }else {
            convertBitmap.eraseColor(Color.TRANSPARENT);
        }
        view.draw(convertCanvas);
        return convertBitmap;
    }

    public void setShadowed(boolean shadowed) {
        this.shadowed = shadowed;
    }

    public void setShadowOpacity(int opacity){
        shadowColor = Color.argb(opacity, 0, 0, 0);
        mShadowPaint.setColorFilter(new PorterDuffColorFilter(shadowColor, PorterDuff.Mode.SRC_IN));
    }

    public void setShadowOffset(float offset){
        shadowOffset = offset;
    }

    public boolean isShadowed(){
        return shadowed;
    }

    public int getShadowOpacity(){
        return Color.alpha(shadowColor);
    }

    public float getShadowOffset(){
        return shadowOffset;
    }
}

