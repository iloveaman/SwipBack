package com.kocla.luckyjmccdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kocla.toolbarlibrary.BaseToolBarActivity;
import com.kocla.toolbarlibrary.ui.SwipeBackLayout;


public class MainActivity extends BaseToolBarActivity implements View.OnClickListener {

    public final static String DIRECTION = "direction";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);

    }

    @Override
    protected int getTitleText() {
        return R.string.title_activity_main;
    }

    @Override
    protected boolean isSwipeOpen() {
        return false;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button1:
                startActivity(new Intent(this, Activity2.class).putExtra(DIRECTION, SwipeBackLayout.DragEdge.LEFT.name()));
                break;
            case R.id.button2:
                startActivity(new Intent(this, Activity2.class).putExtra(DIRECTION, SwipeBackLayout.DragEdge.TOP.name()));
                break;
            case R.id.button3:
                startActivity(new Intent(this, Activity2.class).putExtra(DIRECTION, SwipeBackLayout.DragEdge.RIGHT.name()));
                break;
            case R.id.button4:
                startActivity(new Intent(this, Activity2.class).putExtra(DIRECTION, SwipeBackLayout.DragEdge.BOTTOM.name()));
                break;
        }
    }
}
