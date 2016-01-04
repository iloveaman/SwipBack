package com.kocla.toolbarlibrary.utils;

import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

@SuppressWarnings("deprecation")
public final class ViewUtils {
    //release all image resource from view
    public static void unbindDrawables(View view) {
        if (null == view) {
            return;
        }
        if (view.getBackground() != null) {
            view.getBackground().setCallback(null);
            if (Build.VERSION.SDK_INT >= 16)
                view.setBackground(null);
            else
                view.setBackgroundDrawable(null);
        }
        if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;

            if (imageView.getDrawable() != null) {
                imageView.getDrawable().setCallback(null);
                imageView.setImageDrawable(null);
            }
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                unbindDrawables(((ViewGroup) view).getChildAt(i));
            }
            if (!(view instanceof AdapterView<?>)) {
                ((ViewGroup) view).removeAllViews();
            }
        }
    }

    private ViewUtils() {
    }

    /**
     * 获取View宽高
     */
    public static int[] measureView(View view) {
        int[] widthHeight = new int[2];
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        int width = view.getMeasuredWidth();
        int height = view.getMeasuredHeight();
        widthHeight[0] = width;
        widthHeight[1] = height;
        return widthHeight;
    }


    /**
     * 置target不可点击，并在1秒钟后还原
     *
     * @param target
     */
    public static void freez(final View target) {
        if (target != null) {
            target.setClickable(false);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    target.setClickable(true);
                }
            }, 1000);
        }
    }

    /**
     * 置target不可点击，并在1秒钟后还原
     *
     * @param target
     */
    public static void freez(final View target, int duration) {
        if (target != null) {
            target.setClickable(false);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    target.setClickable(true);
                }
            }, duration);
        }
    }
}
