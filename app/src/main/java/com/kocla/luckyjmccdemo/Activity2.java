package com.kocla.luckyjmccdemo;

import android.os.Bundle;
import android.widget.TextView;

import com.kocla.toolbarlibrary.BaseToolBarActivity;
import com.kocla.toolbarlibrary.ui.SwipeBackLayout;

public class Activity2 extends BaseToolBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        SwipeBackLayout.DragEdge direction = SwipeBackLayout.DragEdge.valueOf(getIntent().getStringExtra(MainActivity.DIRECTION));
        //setDragEdge(SwipeBackLayout.DragEdge.LEFT);
        ((TextView) findViewById(R.id.direction)).setText(direction.name());
        setDragEdge(direction);
        setTitleText(direction.name());
    }


//    @Override
//    protected int statusBackgroundColor() {
//        return getResources().getColor(android.R.color.holo_red_dark);
//    }
}
