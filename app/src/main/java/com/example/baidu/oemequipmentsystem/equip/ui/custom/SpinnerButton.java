package com.example.baidu.oemequipmentsystem.equip.ui.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by zhangbinbin03 on 16/6/17.
 */
public class SpinnerButton extends Button{

    private static final String TAG = "SpinnerButton";

    private Paint paint;
    private int mWidth,mHeight;
    private int defaultWidth;
    private int defaultHeight;
    private int density;
    private Context context;

    public SpinnerButton(Context context) {
        super(context, null);
        this.context=context;
        init();
    }

    public SpinnerButton(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        this.context=context;
        init();
    }

    public SpinnerButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        init();
    }

    public void init(){

        density=(int)context.getResources().getDisplayMetrics().density;
        defaultWidth=90*density;
        defaultHeight=50*density;

        paint=new Paint();
        paint.setAntiAlias(true);
        paint.setColor(0xffffffff);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        }else if(widthMode == MeasureSpec.AT_MOST)
            mWidth = defaultWidth;
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        }else if(heightMode == MeasureSpec.AT_MOST)
            mHeight = defaultHeight;

        setMeasuredDimension(mWidth, mHeight);
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        Path path=new Path();
        path.moveTo(mWidth-3*density,3*density);
        path.lineTo(mWidth-16*density,3*density);
        path.lineTo(mWidth-10*density,10*density);
        path.close();

        canvas.drawPath(path,paint);
    }
}
