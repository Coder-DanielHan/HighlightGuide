package com.danielhan.highlightguide;

/**
 * @author DanielHan
 * @date 2017/11/22
 */

public class Configuration {

    private static final int DEFAULT_ALPHA = 255;
    private static final int DEFAULT_BG_COLOR = android.R.color.black;

    /**
     * 背景颜色id
     */
    protected int mBgColorResId = DEFAULT_BG_COLOR;
    /**
     * 点击蒙层是否消失
     */
    protected boolean clickDismiss;
    /**
     * 是否拦截下层点击事件
     */
    protected boolean interceptClick;
    /**
     * 是否支持点击切换下一个anchor
     */
    protected boolean enableNext;

}
