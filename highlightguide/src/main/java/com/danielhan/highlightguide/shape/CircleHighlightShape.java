package com.danielhan.highlightguide.shape;

import android.graphics.Bitmap;
import android.graphics.RectF;

/**
 * 圆形
 *
 * @author DanielHan
 * @date 2017/11/22
 */

public class CircleHighlightShape extends BaseHighlightShape {
    public CircleHighlightShape(float blurRadius) {
        super(blurRadius);
    }

    @Override
    public void drawShape(Bitmap bitmap, RectF borderRectF) {
        super.drawShape(bitmap,borderRectF);
        mCanvas.drawCircle(borderRectF.left + (borderRectF.width() / 2), borderRectF.top + (borderRectF.height() / 2),
                Math.max(borderRectF.width(), borderRectF.height()) / 2, paint);
    }
}
