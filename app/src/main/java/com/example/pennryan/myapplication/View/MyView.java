package com.example.pennryan.myapplication.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


/**
 * custom view class.
 */
public class MyView extends View {

    Paint paint;

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 把整张画布绘制成白色
        canvas.drawColor(Color.GRAY);
        // 去锯齿
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        paint.setTextSize(20);
        // 绘制7个字符串
        canvas.drawText("圆形", 10, 0, paint);
        canvas.drawText("正方形", 10, 40, paint);
        canvas.drawText("长方形", 10, 80, paint);
        canvas.drawText("圆角矩形", 10, 120, paint);

    }

}
