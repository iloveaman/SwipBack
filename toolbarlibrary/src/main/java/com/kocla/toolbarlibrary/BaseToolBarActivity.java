package com.kocla.toolbarlibrary;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.kocla.toolbarlibrary.ui.SwipeBackLayout;
import com.kocla.toolbarlibrary.utils.DecorUtils;
import com.kocla.toolbarlibrary.utils.SystemBarTintManager;
import com.kocla.toolbarlibrary.utils.ToolBarHelper;

public abstract class BaseToolBarActivity extends BaseActivity {

    protected ToolBarHelper mToolBarHelper;
    protected Toolbar toolbar;
    private View view;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    SystemBarTintManager mTintManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //InstanceStateManager.restoreInstanceState(this, savedInstanceState);
    }


    @Override
    public void setContentView(int layoutResID) {
        //------------------------------Added by Aman 2015年11月9日 15:35:29 begin------------------------------
        // 沉浸模式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (enableTranslucentStatus()) {
                // 状态栏
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                // 虚拟按键
                // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                SystemBarTintManager tintManager = new SystemBarTintManager(this);
                // 激活状态栏设置
                tintManager.setStatusBarTintEnabled(true);
                // 设置一个颜色给系统栏
                tintManager.setTintColor(statusBackgroundColor());
                if (holdReferenceOnfSystemBarTintManager()) {
                    mTintManager = tintManager;
                }
            }
        }
        //------------------------------Added by Aman 2015年11月9日 15:35:29 end------------------------------
        //ViewGroup decorViewGroup = (ViewGroup) getWindow().getDecorView();
        mToolBarHelper = new ToolBarHelper(this, layoutResID);
        toolbar = mToolBarHelper.getToolBar();
        view = mToolBarHelper.getContentView();

        //
        SwipeBackLayout swipeBackLayout = new SwipeBackLayout(this);
        setSwipeBackLayout(swipeBackLayout);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        swipeBackLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));//整個view的背景色
        swipeBackLayout.addView(view, params);
//        final View statusBarTintView = mTintManager.getStatusBarTintView();
//        swipeBackLayout.setOnSwipeBackListener(new SwipeBackLayout.SwipeBackListener() {
//            @Override
//            public void onViewPositionChanged(float fractionAnchor, float fractionScreen) {
//
//            }
//
//            @Override
//            public void smoothScrollToX(int finalLeft) {
//                Log.i("smoothScrollToX", finalLeft + "");
//                statusBarTintView.scrollBy(finalLeft, (int) statusBarTintView.getTranslationY());
//            }
//
//            @Override
//            public void smoothScrollToY(int finalTop) {
//            }
//        });
        //
        setContentView(swipeBackLayout);


        //setContentView(view);
        /*把 toolbar 设置到Activity 中*/
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);//设置右侧的菜单栏按钮是否显示
        toolbar.setNavigationIcon(null); //Navigation Icon 要設定在 setSupoortActionBar 才有作用
        /*自定义的一些操作*/
        onCreateCustomToolBar(toolbar);
        //int statusBarHeight = LollipopUtils.getStatusBarHeight(this);
        //------------------------------Added by Aman 2015年11月9日 15:35:29------------------------------
        DecorUtils.marginForStatusBar(swipeBackLayout);
        //------------------------------Added by Aman 2015年11月9日 15:35:29 end------------------------------
    }

    /**
     * Added by Aman 2015年11月9日 14:46:22
     * 是否需要激活沉浸式状态栏
     *
     * @return
     */
    protected boolean holdReferenceOnfSystemBarTintManager() {
        return true;
    }

    /**
     * Added by Aman 2015年11月9日 14:46:22
     * 是否需要激活沉浸式状态栏
     *
     * @return
     */
    protected boolean enableTranslucentStatus() {
        return true;
    }

    /**
     * Added by Aman 2015年11月9日 14:46:22
     * 状态栏背景色
     *
     * @return
     */
    protected int statusBackgroundColor() {
        return Color.parseColor("#94c447");
    }

    /**
     * Added by Aman 2015年11月5日 15:47:20
     */
    protected void setTitleText() {
        if (getTitleText() != 0) {
            mToolBarHelper.getTitleTextView().setText(getTitleText());
        }

    }

    /**
     * @param titlext
     */
    protected void setTitleText(String titlext) {
        if (titlext != null) {
            mToolBarHelper.getTitleTextView().setText(titlext);
        }
    }

    /**
     * 设置左边button的icon(一般用作返回键)
     */
    protected void setLeftIcon() {
        mToolBarHelper.setLeftIcon(getLeftIcon());
    }

    /**
     * 设置左边button的text(一般用作返回键)
     */
    protected void setLeftText() {
        mToolBarHelper.setLeftText(getLeftText());
        mToolBarHelper.setLeftTextColor(getResources().getColor(getRLTextColor()));
    }

    /**
     * 设置右边button的icon
     */
    protected void setRightIcon() {
        mToolBarHelper.setRightIcon(getRightIcon());
        mToolBarHelper.setRightTextColor(getResources().getColor(getRLTextColor()));
    }

    /**
     * 获取左右按键的文本颜色
     *
     * @return
     */
    protected int getRLTextColor() {
        return android.R.color.white;
    }

    /**
     * 设置右边button的文字
     */
    public void setRightText() {
        mToolBarHelper.setRightText(getRightText());
    }


    protected /*abstract*/ int getLeftIcon() {
        return R.drawable.icon_back_2;
    }


    protected /*abstract*/ int getLeftText() {
        return 0;
    }

    protected /*abstract*/ int getRightIcon() {
        return 0;
    }

    protected  /*abstract*/  int getRightText() {
        return 0;
    }

    /**
     * @return R.string.XXX
     */
    protected  /*abstract*/  int getTitleText() {
        return 0;
    }

    /**
     * toolbar 右側图片按钮点击事件
     */
    protected void onRightButtonIconClick(View view) {

    }

    /**
     * toolbar 右側文字按钮点击事件
     */
    protected void onRightButtonTextClick(View view) {

    }

//    protected void onLeftViewClick(View view) {
//
//    }


    /**
     * 自定义的一些操作
     *
     * @param toolbar
     */
    public void onCreateCustomToolBar(Toolbar toolbar) {
        toolbar.setContentInsetsRelative(0, 0);
        mToolBarHelper.getRightButtonIconClick().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRightButtonIconClick(v);
            }
        });
        mToolBarHelper.getRightButtonTextClick().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRightButtonTextClick(v);
            }
        });
        mToolBarHelper.getLeftView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (needInterceptBackPressed()) {
                    onInterceptBackPressed();
                } else {
                    onBackPressed();
                }
                //onLeftViewClick(v);
            }
        });

        toolbar.setTitle("");
        setLeftIcon();
        setLeftText();

        setRightIcon();
        setRightText();
        setTitleText();

    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //InstanceStateManager.saveInstanceState(this, outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @NonNull
    @Override
    public ActionBar getSupportActionBar() {
        ActionBar actionBar = super.getSupportActionBar();
        if (actionBar == null) throw new NullPointerException("Action bar was not initialized");
        return actionBar;
    }


    //Overload by Aman 2015-11-9 14:28:18
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (needInterceptBackPressed()) {
                    onInterceptBackPressed();
                } else {
                    onBackPressed();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * 拦截返回事件(Added by Aman 2015年11月5日 14:39:12)
     *
     * @return
     */
    protected boolean needInterceptBackPressed() {
        return false;
    }

    /**
     * 如果{@link #needInterceptBackPressed()}返回true就执行此方法,当需要拦截{@link  #onOptionsItemSelected}方法 中case android.R.id.home 的情况以做进行其他操作的时候可重新此方法
     */
    protected void onInterceptBackPressed() {
    }

}
