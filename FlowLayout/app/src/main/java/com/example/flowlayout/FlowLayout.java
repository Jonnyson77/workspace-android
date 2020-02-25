package com.example.flowlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class FlowLayout extends ViewGroup {
    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d("ljq " ,"ljq -----------");
        // 获取子控件的个数
        int childCounts = getChildCount();
        int lineWidth = 0;
        int lineHeight = 0;
        int top = 0; // 头顶距离
        int left = 0; // 左边距距离
        for (int i = 0 ; i < childCounts ; i++) {
            View childView = getChildAt(i);
            MarginLayoutParams layoutParams = (MarginLayoutParams) childView.getLayoutParams();

            int childwidth = childView.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            int childHeight = childView.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;

            if (childwidth + lineWidth > getMeasuredWidth()){
                // 换行
                top += lineHeight;
                left = 0;

                lineHeight = childHeight;
                lineWidth = childwidth;
            }else {
                lineHeight = Math.max(lineHeight,childHeight);
                lineWidth += childwidth;
            }
            // 计算子view 的left ,top ,right ,bottom

            int lc = left + layoutParams.leftMargin;
            int tc = top + layoutParams.topMargin;
            int rc = lc + childView.getMeasuredWidth();
            int bc = tc + childView.getMeasuredHeight();
            childView.layout(lc,tc,rc,bc);
            // 将left 设置为下一个子控件的起始点
            left += childwidth;
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 1. 获取基本位置参数
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        int measuremodeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int measuremodeHeight = MeasureSpec.getMode(heightMeasureSpec);

        int lineWidth = 0;
        int lineHeight = 0;
        int height = 0;
        int width = 0;

        // 2. 获取子view 控件的参数
        int childCounts = getChildCount();
        for (int i = 0 ; i < childCounts ; i++){
            View childView = getChildAt(i);
            measureChild(childView,widthMeasureSpec,heightMeasureSpec);

            MarginLayoutParams layoutParams = (MarginLayoutParams)childView.getLayoutParams();
            int childwidth = childView.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            int childHeight = childView.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;

         // 3.调整控件摆放位置
            if (lineWidth + childwidth > measureHeight){
                // 需要换行
                width = Math.max(lineWidth,width);
                height += lineHeight;
                // 控件位置不够，换行
                lineHeight = childHeight;
                lineWidth = childwidth;
            }else {
                // 不需要换行，累加lineWidth值，取lineheight 最大值
                lineHeight = Math.max(childHeight,lineHeight);
                lineWidth += childwidth;
            }

            // 4. 最后一行的布局
            if (i == childCounts -1){
                height +=lineHeight;
                width = Math.max(width,lineWidth);
            }
        }

        setMeasuredDimension((measuremodeWidth==MeasureSpec.EXACTLY)? measureWidth:width,
                (measuremodeHeight == MeasureSpec.EXACTLY)?measureHeight:height);
    }



    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
    }
}
