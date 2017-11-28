package com.danielhan.highlightguide.shape;

import android.graphics.Bitmap;
import android.graphics.RectF;

/**
 * @author DanielHan
 * @date 2017/11/22
 */

public class OvalHighlightShape extends BaseHighlightShape {

    public OvalHighlightShape(float blurRadius) {
        super(blurRadius);
    }

    @Override
    public void drawShape(Bitmap bitmap, RectF borderRectF) {
        super.drawShape(bitmap,borderRectF);
        mCanvas.drawOval(borderRectF, paint);
    }
}
