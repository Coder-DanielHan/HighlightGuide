package com.danielhan.highlightguide;

import android.app.Activity;
import android.content.Context;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;

import com.danielhan.highlightguide.util.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DanielHan
 * @date 2017/11/22
 */

public class GuideBuilder {

    private Configuration mConfiguration;
    private List<Item> items = new ArrayList<>();

    /**
     * Builder被创建后，不允许在对它进行更改
     */
    private boolean mBuilt;
    private Context mContext;

    /**
     * 构造函数
     */
    public GuideBuilder(Context context) {
        this.mConfiguration = new Configuration();
        this.mContext = context;
    }

    /**
     * 设置蒙板颜色的资源id
     *
     * @param resId 资源id
     * @return GuideBuilder
     */
    public GuideBuilder setBgColorResId(int resId) {
        if (mBuilt) {
            throw new RuntimeException("Already created. rebuild a new one.");
        } else if (resId <= 0) {
            throw new IllegalArgumentException("Illegal color resource id.");
        }
        mConfiguration.mBgColorResId = resId;
        return this;
    }

    /**
     * 设置是否点击消失
     *
     * @param clickDismiss
     * @return
     */
    public GuideBuilder isClickDismiss(boolean clickDismiss) {
        if (mBuilt) {
            throw new RuntimeException("Already created. rebuild a new one.");
        }
        mConfiguration.clickDismiss = clickDismiss;
        return this;
    }

    /**
     * 设置是否拦截下层点击事件
     *
     * @param interceptClick
     * @return
     */
    public GuideBuilder isInterceptClick(boolean interceptClick) {
        if (mBuilt) {
            throw new RuntimeException("Already created. rebuild a new one.");
        }
        mConfiguration.interceptClick = interceptClick;
        return this;
    }

    /**
     * 设置是否支持点击切换下一个anchor
     *
     * @param enableNext
     * @return
     */
    public GuideBuilder setEnableNext(boolean enableNext) {
        if (mBuilt) {
            throw new RuntimeException("Already created. rebuild a new one.");
        }
        mConfiguration.enableNext = enableNext;
        return this;
    }

    /**
     * 添加一个控件
     *
     * @param item 被添加的控件
     * @return GuideBuilder
     */
    public GuideBuilder addItem(Item item) {
        if (mBuilt) {
            throw new RuntimeException("Already created, rebuild a new one.");
        }
        ViewGroup content = (ViewGroup) ((Activity) mContext).findViewById(android.R.id.content);
        View view = content.findViewById(item.getAnchorViewId());
        RectF rect = new RectF(ViewUtils.getLocationInView(content, view));
        item.setmBorderRectF(rect);
        items.add(item);
        return this;
    }

    /**
     * 创建Guide，非Fragment版本
     *
     * @return Guide
     */
    public Guide build() {
        Guide guide = new Guide();
        guide.setItems(items);
        guide.setConfiguration(mConfiguration);
        items = null;
        mConfiguration = null;
        mBuilt = true;
        return guide;
    }
}
