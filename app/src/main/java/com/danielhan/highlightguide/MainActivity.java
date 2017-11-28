package com.danielhan.highlightguide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.danielhan.highlightguide.interfaces.HighlightGuideInterface;
import com.danielhan.highlightguide.shape.CircleHighlightShape;
import com.danielhan.highlightguide.shape.RectHighlightShape;

public class MainActivity extends AppCompatActivity {

    private Guide mGuide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showTipView(View view) {
        mGuide = new GuideBuilder(MainActivity.this)
                .isInterceptClick(true)
                .isClickDismiss(true)
                .setEnableNext(false)
                .setBgColorResId(R.color.highlight)
                .addItem(new Item(new CircleHighlightShape(40), R.id.btn_rightLight, R.layout.info_gravity_left_down, Item.ANCHOR_LEFT, Item.FIT_END, -5, 0, null))
                .addItem(new Item(new RectHighlightShape(40, 10, 10), R.id.btn_light, R.layout.info_gravity_left_down, Item.ANCHOR_RIGHT, Item.FIT_START, 5, 0, null))
                .build();
        mGuide.show(MainActivity.this);
    }

    public void showNextTipView(View view) {
        mGuide = new GuideBuilder(MainActivity.this)
                .isInterceptClick(true)
                .isClickDismiss(false)
                .setEnableNext(true)
                .setBgColorResId(R.color.highlight)
                .addItem(new Item(new CircleHighlightShape(40), R.id.btn_rightLight, R.layout.info_gravity_left_down, Item.ANCHOR_LEFT, Item.FIT_END, -5, 0, null))
                .addItem(new Item(new RectHighlightShape(40, 10, 10), R.id.btn_light, R.layout.info_gravity_left_down, Item.ANCHOR_RIGHT, Item.FIT_START, 5, 0, null))
                .build();
        mGuide.setOnClickListener(new HighlightGuideInterface.OnClickListener() {
            @Override
            public void onClick() {
                Toast.makeText(MainActivity.this, "clicked and show next tip view by yourself", Toast.LENGTH_SHORT).show();
                mGuide.next();
            }
        });
        mGuide.show(MainActivity.this);
    }
}
