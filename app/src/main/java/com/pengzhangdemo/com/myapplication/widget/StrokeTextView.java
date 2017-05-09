package com.pengzhangdemo.com.myapplication.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

import com.pengzhangdemo.com.myapplication.R;
import com.pengzhangdemo.com.myapplication.utils.TextTypeUtils;

import java.lang.reflect.Field;

public class StrokeTextView extends RandomTextView {

    TextPaint m_TextPaint;
    int mInnerColor;
    int mOuterColor;

    public StrokeTextView(Context context, int outerColor, int innnerColor) {
        super(context);
        m_TextPaint = this.getPaint();
        this.mInnerColor = innnerColor;
        this.mOuterColor = outerColor;

    }

    public StrokeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        m_TextPaint = this.getPaint();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.StrokeTextView);
        this.mInnerColor = a.getColor(R.styleable.StrokeTextView_innnerColor,0xffffff);
        this.mOuterColor = a.getColor(R.styleable.StrokeTextView_outerColor,0xffffff);

    }

    public StrokeTextView(Context context, AttributeSet attrs, int defStyle, int outerColor, int innnerColor) {
        super(context, attrs, defStyle);
        m_TextPaint = this.getPaint();
        this.mInnerColor = innnerColor;
        this.mOuterColor = outerColor;

    }

    private boolean m_bDrawSideLine = true; // 默认采用描边

    /**
     *
     */
    @Override
    protected void onDraw(Canvas canvas) {
        if (m_bDrawSideLine) {

            // 描外层
            setTextColorUseReflection(mOuterColor);
            m_TextPaint.setStrokeWidth(8);
            m_TextPaint.setAntiAlias(true);
            m_TextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
//            m_TextPaint.setStro
//            m_TextPaint.setTypeface(TextTypeUtils.getLevelTypeFace(getContext()));
            super.onDraw(canvas);

            // 描内层，恢复原先的画笔
            setTextColorUseReflection(mInnerColor);
            m_TextPaint.setStrokeWidth(0);
//            m_TextPaint.setStrokeColor
            m_TextPaint.setStyle(Paint.Style.FILL_AND_STROKE);

            m_TextPaint.setTypeface(TextTypeUtils.getLevelTypeFace(getContext()));


        }
        super.onDraw(canvas);
    }

    /**
     * 使用反射的方法进行字体颜色的设置
     * @param color
     */
    private void setTextColorUseReflection(int color) {
        Field textColorField;
        try {
            textColorField = TextView.class.getDeclaredField("mCurTextColor");
            textColorField.setAccessible(true);
            textColorField.set(this, color);
            textColorField.setAccessible(false);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        m_TextPaint.setColor(color);
    }


//    解决文字间的间距问题


//    private float spacing = Spacing.NORMAL;
//    private CharSequence originalText = "";
//
//
//
//    public float getSpacing() {
//        return this.spacing;
//    }
//
//    public void setSpacing(float spacing) {
//        this.spacing = spacing;
//        applySpacing();
//    }
//
//    @Override
//    public void setText(CharSequence text, BufferType type) {
//        originalText = text;
//        applySpacing();
//    }
//
//    @Override
//    public CharSequence getText() {
//        return originalText;
//    }
//
//
//    private void applySpacing() {
//        if (this == null || this.originalText == null) return;
//        StringBuilder builder = new StringBuilder();
//        for (int i = 0; i < originalText.length(); i++) {
//            builder.append(originalText.charAt(i));
//            if (i + 1 < originalText.length()) {
//                builder.append("\u00A0");
//            }
//        }
//        SpannableString finalText = new SpannableString(builder.toString());
//        if (builder.toString().length() > 1) {
//            for (int i = 1; i < builder.toString().length(); i += 2) {
//                finalText.setSpan(new ScaleXSpan((spacing + 1) / 10), i, i + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            }
//        }
//        super.setText(finalText, BufferType.SPANNABLE);
//    }
//
//    public class Spacing {
//        public final static float NORMAL = 0;
//    }

}
