package com.danielhan.highlightguide;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;

import com.danielhan.highlightguide.interfaces.HighlightGuideInterface;
import com.danielhan.highlightguide.view.HighlightGuideView;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * @author DanielHan
 * @date 2017/11/23
 */

public class Guide {

    private static final int MESSAGE_CLICK = 0x10;

    private List<Item> items;
    private Configuration mConfiguration;
    private HighlightGuideView mHighlightGuideView;

    private Message clickMessage;
    private GuideHandler mHandler;

    public Guide() {
        if (mHandler == null) {
            mHandler = new GuideHandler(this);
        }
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setConfiguration(Configuration configuration) {
        this.mConfiguration = configuration;
    }

    /**
     * 显示该遮罩, <br>
     * 外部借助{@link com.danielhan.highlightguide.GuideBuilder}
     * 创建好一个Guide实例后，使用该实例调用本函数遮罩才会显示
     *
     * @param activity 目标Activity
     */
    public void show(Activity activity) {
        if (mHighlightGuideView == null) {
            mHighlightGuideView = onCreateView(activity);
        }
        if (mHighlightGuideView.getParent() == null) {
            ViewGroup content = (ViewGroup) activity.findViewById(android.R.id.content);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            content.addView(mHighlightGuideView, lp);
        }
        mHighlightGuideView.addDecorViews();
    }

    /**
     * 隐藏遮罩层
     */
    public void hide() {
        if (mHighlightGuideView == null) {
            return;
        }
        final ViewGroup vp = (ViewGroup) mHighlightGuideView.getParent();
        if (vp == null) {
            return;
        }
        vp.removeView(mHighlightGuideView);
    }

    /**
     * 设置点击
     *
     * @param listener
     */
    public void setOnClickListener(HighlightGuideInterface.OnClickListener listener) {
        if (listener != null && mHandler != null) {
            clickMessage = mHandler.obtainMessage(MESSAGE_CLICK, listener);
        } else {
            clickMessage = null;
        }
    }

    private HighlightGuideView onCreateView(Activity activity) {
        HighlightGuideView highlightGuideView = new HighlightGuideView(activity, mConfiguration.mBgColorResId, items, mConfiguration.enableNext);
        if (mConfiguration.interceptClick) {
            highlightGuideView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mConfiguration.clickDismiss)
                        hide();
                    if (clickMessage != null) {
                        Message.obtain(clickMessage).sendToTarget();
                    }
                }
            });
        }
        return highlightGuideView;
    }

    public static final class GuideHandler extends Handler {
        private WeakReference<Guide> guideRef;
        private HighlightGuideView guideView;

        public GuideHandler(Guide guide) {
            guideRef = new WeakReference<Guide>(guide);
        }

        @Override
        public void handleMessage(Message msg) {
            guideView = guideRef.get() == null ? null : guideRef.get().getmHighlightGuideView();
            switch (msg.what) {
                case MESSAGE_CLICK:
                    ((HighlightGuideInterface.OnClickListener) msg.obj).onClick();
                    break;
            }
        }
    }

    /**
     * 切换下一个anchor
     */
    public void next() {
        if (mConfiguration.enableNext) {
            if (mHighlightGuideView != null) mHighlightGuideView.addDecorViews();
            else throw new NullPointerException("The mHighlightGuideView is null");
        }
    }

    public HighlightGuideView getmHighlightGuideView() {
        return mHighlightGuideView;
    }
}
