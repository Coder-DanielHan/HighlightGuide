package com.danielhan.highlightguide.shape;

import android.graphics.Bitmap;
import android.graphics.RectF;

/**
 * 圆角矩形
 *
 * @author DanielHan
 * @date 2017/11/22
 */

public class RectHighlightShape extends BaseHighlightShape {

    //The x-radius of the oval used to round the corners
    private float rx;
    //The y-radius of the oval used to round the corners
    private float ry;

    public RectHighlightShape(float blurRadius, float rx, float ry) {
        super(blurRadius);
        this.rx = rx;
        this.ry = ry;
    }

    @Override
    public void drawShape(Bitmap bitmap, RectF borderRectF) {
        super.drawShape(bitmap, borderRectF);
        mCanvas.drawRoundRect(borderRectF, rx, ry, paint);
    }
}
