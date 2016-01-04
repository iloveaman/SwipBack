package com.kocla.toolbarlibrary;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alexvasilkov.android.commons.state.InstanceStateManager;
import com.kocla.toolbarlibrary.ui.SwipeBackLayout;
import com.kocla.toolbarlibrary.utils.ViewUtils;

public class BaseActivity extends AppCompatActivity {
    Dialog mProgress;
    protected SwipeBackLayout swipeBackLayout;
    protected ImageView ivShadow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InstanceStateManager.restoreInstanceState(this, savedInstanceState);

    }

    protected boolean isSwipeOpen() {
        return true;
    }



    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //Events.register(this);
        setTitle("");//去掉Activity默认的title
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        InstanceStateManager.saveInstanceState(this, outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Events.unregister(this);
        dismissProgress();
        ViewUtils.unbindDrawables(findViewById(android.R.id.content));
        System.gc();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @NonNull
    @Override
    public ActionBar getSupportActionBar() {
        // Making getSupportActionBar() method to be @NonNull
        ActionBar actionBar = super.getSupportActionBar();
        if (actionBar == null) throw new NullPointerException("Action bar was not initialized");
        return actionBar;
    }


    public void setDragEdge(SwipeBackLayout.DragEdge dragEdge) {
        swipeBackLayout.setDragEdge(dragEdge);
    }

    public SwipeBackLayout getSwipeBackLayout() {
        return swipeBackLayout;
    }

    public void setSwipeBackLayout(SwipeBackLayout swipeBackLayout) {
        this.swipeBackLayout = swipeBackLayout;
    }


    protected void showProgress(String msg, boolean canceledOnTouchOutside) {
        View mView = LayoutInflater.from(this).inflate(R.layout.dialog_progerss, null);
        //
        TextView message = (TextView) mView.findViewById(android.R.id.message);//
        if (!TextUtils.isEmpty(msg)) {
            message.setText(msg);
        } else {
            message.setVisibility(View.GONE);
        }
        //
        mProgress = new Dialog(this, R.style.ProgressDialogStyle);
        LinearLayout.LayoutParams lps = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lps.gravity = Gravity.CENTER;
        mProgress.setContentView(mView, lps);
        Window window = mProgress.getWindow();
        // 设置显示动画
        WindowManager.LayoutParams wl = window.getAttributes();// 居中时lp.x=0,lp.y=0,即屏幕的坐标原点正好位于屏幕的正中间。
        wl.x = 0;
        wl.y = 0;
        // 以下这两句是为了保证按钮可以水平满屏
        //wl.width =  Dip2PxUtil.dip2px(ctx,300.0f);
        wl.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        mProgress.onWindowAttributesChanged(wl);
        // 设置点击外围解散
        mProgress.setCanceledOnTouchOutside(canceledOnTouchOutside);
        mProgress.show();
    }

    protected void dismissProgress() {
        if (mProgress != null && mProgress.isShowing()) {
            mProgress.dismiss();
            mProgress = null;
        }
    }

}
