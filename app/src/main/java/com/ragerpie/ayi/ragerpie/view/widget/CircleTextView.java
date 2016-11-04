package com.ragerpie.ayi.ragerpie.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.ragerpie.ayi.ragerpie.R;
import com.ragerpie.ayi.ragerpie.util.DpAndPx;

/**
 * Created by WangBo on 2016/11/4.
 */

public class CircleTextView extends View {
    private int bgColor = Color.parseColor("#3F51B5");
    private String text = "";
    private int textSize = DpAndPx.sp2px(18);
    private int textColor = Color.parseColor("#FFFFFF");

    private Paint paint;
    private Rect textBound = new Rect();

    public CircleTextView(Context context) {
        super(context);
    }

    public CircleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.CircleTextView);
        bgColor = t.getColor(R.styleable.CircleTextView_bgColor, bgColor);
        text = t.getString(R.styleable.CircleTextView_text);
        textSize = t.getDimensionPixelSize(R.styleable.CircleTextView_textSize, textSize);
        textColor = t.getColor(R.styleable.CircleTextView_textColor, textColor);
        t.recycle();
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int radius = getMeasuredWidth() / 2;
        canvas.save();
        //把坐标轴移动到圆心
        canvas.translate(radius, getMeasuredHeight() / 2);
        //画圆
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(bgColor);
        canvas.drawCircle(0, 0, radius, paint);
        //写文字
        if (text != null) {
            paint.getTextBounds(text, 0, text.length(), textBound);
            paint.setColor(textColor);
            paint.setTextSize(textSize);
            Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
            int baseLine =
            canvas.drawText(text, -textBound.width() / 2, textBound.height() / 2, paint);
//            canvas.drawText(text, 0, 0, paint);

        }
        canvas.restore();
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }
}
