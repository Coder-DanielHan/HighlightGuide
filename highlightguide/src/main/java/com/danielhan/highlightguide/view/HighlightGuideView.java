package com.danielhan.highlightguide.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.danielhan.highlightguide.Item;
import com.danielhan.highlightguide.util.DensityUtil;

import java.util.List;

/**
 * @author DanielHan
 * @date 2017/11/23
 */

public class HighlightGuideView extends FrameLayout {

    private static final PorterDuffXfermode MODE_DST_OUT = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);

    private int mBgColorResId;
    private List<Item> items;
    private boolean enableNext;

    private Paint mPaint;
    private int getmBgColor;
    private Bitmap mLightBitmap;
    private Bitmap mMaskBitmap;
    private LayoutInflater mLayoutInflater;
    private final RectF mChildTmpRect = new RectF();
    private int mCurrentItem = -1;


    public HighlightGuideView(@NonNull Context context,@Nullable int mBgColorResId, @Nullable List<Item> items, boolean enableNext) {
        super(context);
        this.mBgColorResId = mBgColorResId;
        this.items = items;
        this.enableNext = enableNext;
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        getmBgColor = getResources().getColor(mBgColorResId);
        setWillNotDraw(false);
//        this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed || enableNext) {
            buildMask();
            updateDecorViewsLayout();
        }
    }

    /**
     * 更新所有decorview的layout
     */
    private void updateDecorViewsLayout() {
        if (enableNext) {
            updateSingleDecorViewLayout(getChildAt(0), items.get(mCurrentItem));
        } else {
            final int count = getChildCount();
            View child;
            for (int i = 0; i < count; i++) {
                child = getChildAt(i);
                if (child == null) {
                    continue;
                }
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (lp == null) {
                    continue;
                }
                updateSingleDecorViewLayout(child, items.get(i));
            }
        }

    }

    /**
     * 更新单个decorview的layout
     *
     * @param decorview
     * @param item
     */
    private void updateSingleDecorViewLayout(View decorview, Item item) {
        int decorPosition = item.getDecorPosition();
        switch (decorPosition) {
            case Item.ANCHOR_LEFT://左
                mChildTmpRect.right = item.getmBorderRectF().left;
                mChildTmpRect.left = mChildTmpRect.right - decorview.getMeasuredWidth();
                verticalChildPositionLayout(decorview, mChildTmpRect, item);
                break;
            case Item.ANCHOR_TOP://上
                mChildTmpRect.bottom = item.getmBorderRectF().top;
                mChildTmpRect.top = mChildTmpRect.bottom - decorview.getMeasuredHeight();
                horizontalChildPositionLayout(decorview, mChildTmpRect, item);
                break;
            case Item.ANCHOR_RIGHT://右
                mChildTmpRect.left = item.getmBorderRectF().right;
                mChildTmpRect.right = mChildTmpRect.left + decorview.getMeasuredWidth();
                verticalChildPositionLayout(decorview, mChildTmpRect, item);
                break;
            case Item.ANCHOR_BOTTOM://下
                mChildTmpRect.top = item.getmBorderRectF().bottom;
                mChildTmpRect.bottom = mChildTmpRect.top + decorview.getMeasuredHeight();
                horizontalChildPositionLayout(decorview, mChildTmpRect, item);
                break;
        }
        //额外的xy偏移
        mChildTmpRect.offset(DensityUtil.dip2px(getContext(), item.getOffsetX()),
                DensityUtil.dip2px(getContext(), item.getOffsetY()));
        decorview.layout((int) mChildTmpRect.left, (int) mChildTmpRect.top, (int) mChildTmpRect.right,
                (int) mChildTmpRect.bottom);
    }

    /**
     * 计算decorview水平位置
     *
     * @param child decorview
     * @param rect  decorview所在rect
     * @param item  decorview所对应的anchor信息
     */
    private void horizontalChildPositionLayout(View child, RectF rect, Item item) {
        switch (item.getFitPosition()) {
            case Item.FIT_START:
                rect.left = item.getmBorderRectF().left;
                rect.right = rect.left + child.getMeasuredWidth();
                break;
            case Item.FIT_CENTER:
                rect.left = (item.getmBorderRectF().width() - child.getMeasuredWidth()) / 2;
                rect.right = (item.getmBorderRectF().width() + child.getMeasuredWidth()) / 2;
                rect.offset(item.getmBorderRectF().left, 0);
                break;
            case Item.FIT_END:
                rect.right = item.getmBorderRectF().right;
                rect.left = rect.right - child.getMeasuredWidth();
                break;
        }
    }

    /**
     * 计算decorview垂直位置
     *
     * @param child decorview
     * @param rect  decorview所在rect
     * @param item  decorview所对应的anchor信息
     */
    private void verticalChildPositionLayout(View child, RectF rect, Item item) {
        switch (item.getFitPosition()) {
            case Item.FIT_START:
                rect.top = item.getmBorderRectF().top;
                rect.bottom = rect.top + child.getMeasuredHeight();
                break;
            case Item.FIT_CENTER:
                rect.top = (item.getmBorderRectF().height() - child.getMeasuredHeight()) / 2;
                rect.bottom = (item.getmBorderRectF().height() + child.getMeasuredHeight()) / 2;
                rect.offset(0, item.getmBorderRectF().top);
                break;
            case Item.FIT_END:
                rect.bottom = item.getmBorderRectF().bottom;
                rect.top = item.getmBorderRectF().bottom - child.getMeasuredHeight();
                break;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        buildMask();
        canvas.drawBitmap(mMaskBitmap, 0, 0, null);
        super.onDraw(canvas);
    }

    /**
     * 添加所有decorview
     */
    public void addDecorViews() {
        if (enableNext) {
            //校验mPosition
            if (mCurrentItem < -1 || mCurrentItem > items.size() - 1) {
                //重置位置
                mCurrentItem = 0;
            } else if (mCurrentItem == items.size() - 1) {
                //移除当前布局
                final ViewGroup vp = (ViewGroup) this.getParent();
                if (vp == null) {
                    return;
                }
                vp.removeView(this);
                return;
            } else {
                //mPosition++
                mCurrentItem++;
            }
            //移除所有tip再添加当前位置的tip布局
            removeAllViews();
            addSingleDecorView(items.get(mCurrentItem));

//            if (mHighLight != null) {
//                mHighLight.sendNextMessage();
//            }

        } else {
            for (Item item : items) {
                addSingleDecorView(item);
            }
        }
    }

    /**
     * 添加单个decorview
     *
     * @param item
     */
    private void addSingleDecorView(Item item) {
        View decorview = mLayoutInflater.inflate(item.getDecorViewId(), null);
        ViewGroup.LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        decorview.setLayoutParams(lp);
        addView(decorview);
    }

    private void buildMask() {
        recycleBitmap(mMaskBitmap);
        mMaskBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(mMaskBitmap);
        canvas.drawColor(getmBgColor);
        mPaint.setXfermode(MODE_DST_OUT);

        recycleBitmap(mLightBitmap);
        mLightBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredWidth(), Bitmap.Config.ARGB_4444);
        if (enableNext) {
            items.get(mCurrentItem).getmShape().drawShape(mLightBitmap, items.get(mCurrentItem).getmBorderRectF());
            canvas.drawBitmap(mLightBitmap, 0, 0, mPaint);
        } else {
            for (Item item : items) {
                item.getmShape().drawShape(mLightBitmap, item.getmBorderRectF());
                canvas.drawBitmap(mLightBitmap, 0, 0, mPaint);
            }
        }

    }

    /**
     * 主动回收之前创建的bitmap
     *
     * @param bitmap
     */
    private void recycleBitmap(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
            bitmap = null;
            System.gc();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        //optimize recycle bitmap
        recycleBitmap(mLightBitmap);
        recycleBitmap(mMaskBitmap);
    }
}
