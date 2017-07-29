package com.pengzhangdemo.com.myapplication.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.pengzhangdemo.com.myapplication.R;

import java.lang.reflect.Field;

public class StrokeArcTextView extends android.support.v7.widget.AppCompatTextView {

    private int width, height;

    TextPaint m_TextPaint;
    Paint m_BorderPaint;
    private RectF refct;
    private Path arcPath;
    int mInnerColor;
    int mOuterColor;
    private CharSequence text;

    public StrokeArcTextView(Context context, int outerColor, int innnerColor) {
        super(context);
        m_TextPaint = this.getPaint();
        this.mInnerColor = innnerColor;
        this.mOuterColor = outerColor;
        initAll();
    }

    public StrokeArcTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        m_TextPaint = this.getPaint();

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.StrokeArcTextView);
        this.mInnerColor = a.getColor(R.styleable.StrokeArcTextView_arcinnnerColor, 0xffffff);
        this.mOuterColor = a.getColor(R.styleable.StrokeArcTextView_arcouterColor, 0xffffff);
        initAll();
    }

    public StrokeArcTextView(Context context, AttributeSet attrs, int defStyle, int outerColor, int innnerColor) {
        super(context, attrs, defStyle);
        m_TextPaint = this.getPaint();
        this.mInnerColor = innnerColor;
        this.mOuterColor = outerColor;
        initAll();
    }

    private boolean m_bDrawSideLine = true; // 默认采用描边

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        initAll();
    }


    private void initAll() {
        m_BorderPaint = new Paint();
        refct = new RectF(0, (float) (height / 1.4), width, height + 200);
        arcPath = new Path();
        arcPath.addArc(refct, 210, 120);
        invalidate();
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        this.text = text;
        super.setText(text, type);

    }

    @Override
    protected void onDraw(Canvas canvas) {


        int wOffset = width / 3;

        if (!TextUtils.isEmpty(text) && text.length() > 0) {
            if (text.length() >= 8) {
                wOffset = width / 4;
            }else if (text.length() > 5){
                wOffset = (int) (width / 3.5);
            }
        }


        // 描外层
        setTextColorUseReflection(mOuterColor);

        m_TextPaint.setStrokeWidth(6);
        m_TextPaint.setAntiAlias(true);
        m_TextPaint.setStyle(Paint.Style.STROKE);

        canvas.drawTextOnPath(text.toString(), arcPath, wOffset, 0, m_TextPaint);// 这里才是真正画出了 text


        setTextColorUseReflection(mInnerColor);
        m_TextPaint.setFakeBoldText(true);
        m_TextPaint.setStyle(Paint.Style.FILL);
        m_TextPaint.setAntiAlias(true);
        canvas.drawTextOnPath(text.toString(), arcPath, wOffset, 0, m_TextPaint);// 这里才是真正画出了 text
    }

    /**
     *
     */
//    @Override
//    protected void onDraw(Canvas canvas) {
//
//        int wOffset = width / 3;
//        // 描外层
//        setTextColorUseReflection(mOuterColor);
////        m_TextPaint.setColor(getContext().getResources().getColor(android.R.color.black));
//        m_TextPaint.setStrokeWidth(8);
//        m_TextPaint.setFakeBoldText(true);
//        m_TextPaint.setAntiAlias(true);
//
////        m_TextPaint.setStyle(Paint.Style.STROKE);
////            m_TextPaint.setTypeface(TextTypeUtils.getLevelTypeFace(getContext()));
////        canvas.drawArc(refct, 210, 120, false, m_TextPaint);// 这里是画出了弧线。
////        canvas.save();
//
//        canvas.drawTextOnPath(text.toString(), arcPath, wOffset, 0, m_TextPaint);// 这里才是真正画出了 text
////        super.onDraw(canvas);
//
//        // 描内层，恢复原先的画笔
//
////        m_TextPaint.setStrokeWidth(0);
////        m_TextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
////        m_TextPaint.setTypeface(TextTypeUtils.getLevelTypeFace(getContext()));
//
////        String text = "510倍";
//        setTextColorUseReflection(mInnerColor);
//        canvas.drawTextOnPath(text.toString(), arcPath, wOffset, 0, m_TextPaint);// 这里才是真正画出了 text
//    }

    /**
     * 使用反射的方法进行字体颜色的设置
     *
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
