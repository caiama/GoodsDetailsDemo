package com.dxngxhl.goodsdetailsdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;

public class GoodsScrollView extends NestedScrollView {

    public GoodsScrollView(@NonNull Context context) {
        super(context);
    }

    public GoodsScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GoodsScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (onScrollListener != null){
            onScrollListener.onScrollChanged(l,t,oldl,oldt);
        }
//        Log.e("onScrollChanged","l=="+l+"  t=="+t+"  oldl=="+oldl+"   oldt=="+oldt);
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        if (onScrollListener != null){
            onScrollListener.onOverScrolled(scrollX,scrollY,clampedX,clampedY);
        }
//        Log.e("onOverScrolled","scrollX=="+scrollX+"  scrollY=="+scrollY+"  clampedX=="+clampedX+"   clampedY=="+clampedY);
    }


    public void setOnScrollListener(OnScrollListener onScrollListener){
        this.onScrollListener = onScrollListener;
    }
    private OnScrollListener onScrollListener;
    public interface OnScrollListener{
        void onScrollChanged(int l, int t, int oldl, int oldt);
        void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY);
    }
}
