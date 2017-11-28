package com.danielhan.highlightguide;

import android.graphics.RectF;

import com.danielhan.highlightguide.shape.BaseHighlightShape;

/**
 * @author DanielHan
 * @date 2017/11/22
 */

public class Item {

    public static final int ANCHOR_LEFT = 0x01;
    public static final int ANCHOR_TOP = 0x02;
    public static final int ANCHOR_RIGHT = 0x03;
    public static final int ANCHOR_BOTTOM = 0x04;

    public static final int FIT_START = 0x10;
    public static final int FIT_CENTER = 0x20;
    public static final int FIT_END = 0x30;

    /**
     * 高亮形状
     */
    private BaseHighlightShape mShape;
    /**
     * 锚点view的资源id
     */
    private int anchorViewId;
    /**
     * 修饰view的资源id
     */
    private int decorViewId;
    /**
     * 修饰view相对于锚点view的位置
     */
    private int decorPosition = ANCHOR_LEFT;
    /**
     * 修饰view对齐方式
     */
    private int fitPosition = FIT_START;
    /**
     * x位移
     */
    private float offsetX;
    /**
     * y位移
     */
    private float offsetY;
    /**
     * 边界矩形
     */
    private RectF mBorderRectF;

    public Item(BaseHighlightShape mShape, int anchorViewId, int decorViewId, int decorPosition, int fitPosition, float offsetX, float offsetY, RectF mBorderRectF) {
        this.mShape = mShape;
        this.anchorViewId = anchorViewId;
        this.decorViewId = decorViewId;
        this.decorPosition = decorPosition;
        this.fitPosition = fitPosition;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.mBorderRectF = mBorderRectF;
    }

    public BaseHighlightShape getmShape() {
        return mShape;
    }

    public void setmShape(BaseHighlightShape mShape) {
        this.mShape = mShape;
    }

    public int getDecorViewId() {
        return decorViewId;
    }

    public void setDecorViewId(int decorViewId) {
        this.decorViewId = decorViewId;
    }

    public int getDecorPosition() {
        return decorPosition;
    }

    public void setDecorPosition(int decorPosition) {
        this.decorPosition = decorPosition;
    }

    public float getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(float offsetX) {
        this.offsetX = offsetX;
    }

    public float getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(float offsetY) {
        this.offsetY = offsetY;
    }

    public RectF getmBorderRectF() {
        return mBorderRectF;
    }

    public void setmBorderRectF(RectF mBorderRectF) {
        this.mBorderRectF = mBorderRectF;
    }

    public int getAnchorViewId() {
        return anchorViewId;
    }

    public void setAnchorViewId(int anchorViewId) {
        this.anchorViewId = anchorViewId;
    }

    public int getFitPosition() {
        return fitPosition;
    }

    public void setFitPosition(int fitPosition) {
        this.fitPosition = fitPosition;
    }
}
