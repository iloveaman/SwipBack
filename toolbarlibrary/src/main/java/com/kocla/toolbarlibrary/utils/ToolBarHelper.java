package com.kocla.toolbarlibrary.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kocla.toolbarlibrary.R;


/**
 * Created by admin on 2015/11/5.
 */
public class ToolBarHelper {
    //private boolean isSwipeOpen;
    /*上下文，创建view的时候需要用到*/
    private Context mContext;

    /*base view*/
    private FrameLayout mContentView;

    /*用户定义的view*/
    private View mUserView;

    /*toolbar*/
    private Toolbar mToolBar;

    /*视图构造器*/
    private LayoutInflater mInflater;

    private LinearLayout toolbar_left_button;
    private TextView tv_title;
    private TextView toolbar_right_button_text, toolbar_left_button_text;
    private ImageButton toolbar_right_button_icon, toolbar_left_button_icon;

    /*
    * 两个属性
    * 1、toolbar是否悬浮在窗口之上
    * 2、toolbar的高度获取
    * */
    private static int[] ATTRS = {
            R.attr.windowActionBarOverlay,
            R.attr.actionBarSize
    };

    public ToolBarHelper(Context context,/* boolean isSwipeOpen,*/ int layoutId) {
        this.mContext = context;
        //this.isSwipeOpen = isSwipeOpen;
        mInflater = LayoutInflater.from(mContext);
        /*初始化整个内容*/
        initContentView();
        /*初始化用户定义的布局*/
        initUserView(layoutId);
        /*初始化toolbar*/
        initToolBar();
    }

    private void initContentView() {
        /*直接创建一个帧布局，作为视图容器的父容器*/
        mContentView = new FrameLayout(mContext);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mContentView.setLayoutParams(params);
        mContentView.setBackgroundColor(mContext.getResources().getColor(android.R.color.transparent));

    }

    private void initToolBar() {
        /*通过inflater获取toolbar的布局文件*/
        View toolbar = mInflater.inflate(R.layout.toolbar, mContentView);
        tv_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbar_left_button = (LinearLayout) toolbar.findViewById(R.id.toolbar_left_button);
        toolbar_right_button_icon = (ImageButton) toolbar.findViewById(R.id.toolbar_right_button_icon);
        toolbar_left_button_icon = (ImageButton) toolbar.findViewById(R.id.toolbar_left_button_icon);
        toolbar_right_button_text = (TextView) toolbar.findViewById(R.id.toolbar_right_button_text);
        toolbar_left_button_text = (TextView) toolbar.findViewById(R.id.toolbar_left_button_text);
        mToolBar = (Toolbar) toolbar.findViewById(R.id.id_tool_bar);
    }

    private void initUserView(int id) {
        mUserView = mInflater.inflate(id, null);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        TypedArray typedArray = mContext.getTheme().obtainStyledAttributes(ATTRS);
        /*获取主题中定义的悬浮标志*/
        boolean overly = typedArray.getBoolean(0, false);
        /*获取主题中定义的toolbar的高度*/
//        int toolBarSize = (int) typedArray.getDimension(1, (int) mContext.getResources().getDimension(R.dimen.abc_action_bar_default_height_material));
        int toolBarSize = (int) mContext.getResources().getDimension(R.dimen.titlebar_height);
        typedArray.recycle();
        /*如果是悬浮状态，则不需要设置间距*/
        params.topMargin = overly ? 0 : toolBarSize;
        mContentView.addView(mUserView, params);

//        SwipeBackLayout swipeBackLayout = new SwipeBackLayout(mContext);
//        swipeBackLayout.addView(mUserView);
//        mContentView.addView(swipeBackLayout, params);
//        ((BaseActivity) mContext).setSwipeBackLayout(swipeBackLayout);
////        SwipeBackLayout swipeBackLayout = new SwipeBackLayout(mContext);
////        swipeBackLayout.addView(mUserView);
////        ((BaseActivity)mContext).setSwipeBackLayout(swipeBackLayout);
////        ImageView ivShadow;
////        ivShadow = new ImageView(mContext);
////        ivShadow.setBackgroundColor(mContext.getResources().getColor(R.color.black_7f000000));
////        RelativeLayout.LayoutParams lps = new RelativeLayout.LayoutParams(RelativeLayout.MarginLayoutParams.MATCH_PARENT, RelativeLayout.MarginLayoutParams.MATCH_PARENT);
////        mContentView.addView(ivShadow, lps);
////        mContentView.addView(swipeBackLayout, params);
    }

    /**
     * 设置左边button的icon(一般用作返回键)
     */
    public void setLeftIcon(@Nullable Drawable icon) {
        mToolBar.setNavigationIcon(icon);
    }

    /**
     * 设置左边button的icon(一般用作返回键)
     */
    public void setLeftIcon(@DrawableRes int resId) {
        //mToolBar.setNavigationIcon(resId);
        if (resId != 0) {
            //mToolBar.setTitle(resId);
            //mToolBar.setTitleTextColor(mRLTextColor);
            toolbar_left_button_icon.setImageResource(resId);
            toolbar_left_button_icon.setVisibility(View.VISIBLE);
            toolbar_left_button.setVisibility(View.VISIBLE);
        } else {
            toolbar_left_button_icon.setVisibility(View.GONE);
        }

    }

    /**
     * 设置左边button的文字(一般用作返回键)
     */
    public void setLeftText(@Nullable int resId) {
        if (resId != 0) {
            //mToolBar.setTitle(resId);
            //mToolBar.setTitleTextColor(mRLTextColor);
            toolbar_left_button_text.setText(resId);
            toolbar_left_button_text.setVisibility(View.VISIBLE);
            toolbar_left_button.setVisibility(View.VISIBLE);
        } else {
            toolbar_left_button_text.setVisibility(View.GONE);
        }
    }

    public void setLeftTextColor(@Nullable int color) {
        toolbar_left_button_text.setTextColor(color);
    }

    /**
     * 设置右边button的icon
     */
    public void setRightIcon(@Nullable Drawable icon) {
        toolbar_right_button_icon.setImageDrawable(icon);
    }

    /**
     * 设置右边button的icon
     */
    public void setRightIcon(@DrawableRes int resId) {
        if (resId != 0) {
            toolbar_right_button_icon.setVisibility(View.VISIBLE);
            toolbar_right_button_icon.setImageResource(resId);
        } else {
            toolbar_right_button_icon.setVisibility(View.GONE);
        }
    }

    /**
     * 设置右边button的icon
     */
    public void setRightText(int resId) {
        if (resId != 0) {
            toolbar_right_button_text.setVisibility(View.VISIBLE);
            toolbar_right_button_text.setText(resId);
        } else {
            toolbar_right_button_text.setVisibility(View.GONE);
        }
    }

    public void setRightTextColor(@Nullable int color) {
        toolbar_right_button_text.setTextColor(color);
    }

    private int mRLTextColor;

    public int getRLTextColor() {
        return mRLTextColor;
    }

    public void setRLTextColor(int mRLTextColor) {
        this.mRLTextColor = mRLTextColor;
    }

    public FrameLayout getContentView() {
        return mContentView;
    }

    public Toolbar getToolBar() {
        return mToolBar;
    }

    /**
     * title 对应的 TextView
     *
     * @return
     */
    public TextView getTitleTextView() {
        return tv_title;
    }

    /**
     * 右边按钮
     *
     * @return
     */
    public ImageButton getRightButtonIconClick() {
        return toolbar_right_button_icon;
    }

    public LinearLayout getLeftView() {
        return toolbar_left_button;
    }

    /**
     * 右边按钮
     *
     * @return
     */
    public TextView getRightButtonTextClick() {
        return toolbar_right_button_text;
    }


}
