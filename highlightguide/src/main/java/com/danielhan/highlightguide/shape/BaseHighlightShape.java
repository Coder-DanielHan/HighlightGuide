package com.danielhan.highlightguide.shape;

import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * 高亮形状超类
 *
 * @author DanielHan
 * @date 2017/11/21
 */

public abstract class BaseHighlightShape {
    private static final float DEFAULT_BLUR_RADIUS = 15;
    //模糊半径
    private float blurRadius = DEFAULT_BLUR_RADIUS;

    protected Canvas mCanvas;
    protected Paint paint;

    public BaseHighlightShape(float blurRadius) {
        this.blurRadius = blurRadius;
    }

    /**
     * 绘制图形
     */
    public void drawShape(Bitmap bitmap, RectF borderRectF) {
        mCanvas = new Canvas(bitmap);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setDither(true);
        if (blurRadius > 0) {
            paint.setMaskFilter(new BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.SOLID));
        }
    }


}
