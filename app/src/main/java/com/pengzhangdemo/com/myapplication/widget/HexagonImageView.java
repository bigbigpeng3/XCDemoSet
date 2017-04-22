package com.pengzhangdemo.com.myapplication.widget;

/**
 * Created by zp on 4/7/17.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.pengzhangdemo.com.myapplication.R;


public class HexagonImageView extends ImageView {

    private static final ScaleType SCALE_TYPE = ScaleType.CENTER_CROP;

    private static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;
    private static final int COLORDRAWABLE_DIMENSION = 1;

    private static final int DEFAULT_BORDER_WIDTH = 0;
    private static final int DEFAULT_BORDER_COLOR = Color.BLACK;

    private final RectF mDrawableRect = new RectF();
    private final RectF mBorderRect = new RectF();

    private final Matrix mShaderMatrix = new Matrix();
    private final Paint mBitmapPaint = new Paint();
    private final Paint mBorderPaint = new Paint();

    private int mBorderColor = DEFAULT_BORDER_COLOR;
    private int mBorderWidth = DEFAULT_BORDER_WIDTH;

    private Bitmap mBitmap;
    private BitmapShader mBitmapShader;
    private int mBitmapWidth;
    private int mBitmapHeight;

    private float mDrawableRadius;
    private float mBorderRadius;

    private boolean mReady;
    private boolean mSetupPending;

    public HexagonImageView(Context context) {
        super(context);
    }

    public HexagonImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HexagonImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        super.setScaleType(SCALE_TYPE);
        initView(context,attrs,defStyle);

    }

    private void initView(Context context, AttributeSet attrs,int defStyle) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.HexagonImageView, defStyle, 0);
        mBorderWidth = a.getDimensionPixelSize(R.styleable.HexagonImageView_borders_width, DEFAULT_BORDER_WIDTH);
        mBorderColor = a.getColor(R.styleable.HexagonImageView_borders_color, DEFAULT_BORDER_COLOR);
        a.recycle();
        mReady = true;

        if (mSetupPending) {
            setup();
            mSetupPending = false;
        }
    }
    @Override
    public ScaleType getScaleType() {
        return SCALE_TYPE;
    }

    @Override
    public void setScaleType(ScaleType scaleType) {
        if (scaleType != SCALE_TYPE) {
            throw new IllegalArgumentException(String.format("ScaleType %s not supported.", scaleType));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable() == null) {
            return;
        }
        drawRadius(canvas,mDrawableRadius,mBitmapPaint);
        drawRadius(canvas,mBorderRadius,mBorderPaint);

        //float radius = getWidth()/2;

//        float a1 = (float) (mDrawableRadius*Math.sin(radian30));
//        float b1 = (float) (mDrawableRadius*Math.cos(radian30));
//        画正六边形
//        Path drawablePath = new Path();
//        drawablePath.moveTo(centerX, 0);
//        drawablePath.lineTo(centerX + b1, centerY-a1);
//        drawablePath.lineTo(centerX+b1, centerY+a1);
//        drawablePath.lineTo(centerX, getHeight());
//        drawablePath.lineTo(centerX-b1, centerY+a1);
//        drawablePath.lineTo(centerX - b1, centerY - a1);
//        drawablePath.close();
//        canvas.drawPath(drawablePath, mBitmapPaint);

//        float a2 = (float) (mBorderRadius*Math.sin(radian30));
//        float b2 = (float) (mBorderRadius*Math.cos(radian30));
//
//        Path borderPath = new Path();
//        borderPath.moveTo(centerX, 0);
//        borderPath.lineTo(centerX + b2, centerY-a2);
//        borderPath.lineTo(centerX+b2, centerY+a2);
//        borderPath.lineTo(centerX, getHeight());
//        borderPath.lineTo(centerX-b2, centerY+a2);
//        borderPath.lineTo(centerX - b2, centerY - a2);
//        borderPath.close();
//        canvas.drawPath(drawablePath, mBitmapPaint);
//        canvas.drawPath(borderPath,mBorderPaint);

        //画圆
        // canvas.drawCircle(getWidth() / 2, getHeight() / 2, mDrawableRadius, mBitmapPaint);
        // canvas.drawCircle(getWidth() / 2, getHeight() / 2, mBorderRadius, mBorderPaint);
    }
    //画一个带倒角的正六边形，主要是一个算法，描点。
    private void drawRadius(Canvas canvas, float radius,Paint paint) {
        double radian30 = 30*Math.PI/180;
        double radian60 = 60*Math.PI/180;

        float centerX = getWidth()/2;
        float centerY = getHeight()/2;
        //  通过此值修改正六边形的倒角弧度
        float z1 = radius/8;
        float x1 = (float) (z1*Math.cos(radian30));
        float y1 = (float) (z1*Math.sin(radian30));
        float m1 = (float) Math.abs(Math.sqrt(z1 * z1 + radius * radius - radius * z1));
        float o1 = (float) Math.asin(z1 * Math.sin(radian60) / m1);
        float p1 = (float) (m1*Math.sin(radian60-o1));
        float q1 = (float) (m1*Math.cos(radian60 - o1));
        float e1 = (float) (m1*Math.cos(radian30-o1));
        float f1 = (float) (m1*Math.sin(radian30 - o1));
        float a1 = (float) (radius*Math.sin(radian30));
        float b1 = (float) (radius*Math.cos(radian30));

        Path drawablePath = new Path();
        drawablePath.moveTo(centerX - x1, y1);//起始点坐标
        drawablePath.quadTo(centerX, 0, centerX + x1, y1); //第一个点是辅助点  //第二个点是终止点  画弧

        drawablePath.lineTo(centerX + p1, centerY - q1);
        drawablePath.quadTo(centerX + b1, centerY - a1, centerX + e1, centerY - f1);

        drawablePath.lineTo(centerX + e1, centerY + f1);
        drawablePath.quadTo(centerX+b1, centerY+a1 , centerX + p1, centerY + q1);

        drawablePath.lineTo(centerX + x1, getHeight() - y1);
        drawablePath.quadTo(centerX , getHeight() , centerX - x1, getHeight() - y1);

        drawablePath.lineTo(centerX - p1, centerY + q1);
        drawablePath.quadTo(centerX-b1, centerY+a1 , centerX - e1, centerY + f1);

        drawablePath.lineTo(centerX - e1, centerY - f1);
        drawablePath.quadTo(centerX - b1, centerY - a1 , centerX - p1, centerY - q1);

        drawablePath.close();
        canvas.drawPath(drawablePath, paint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        setup();
    }

    public int getBorderColor() {
        return mBorderColor;
    }

    public void setBorderColor(int borderColor) {
        if (borderColor == mBorderColor) {
            return;
        }

        mBorderColor = borderColor;
        mBorderPaint.setColor(mBorderColor);
        invalidate();
    }

    public int getBorderWidth() {
        return mBorderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        if (borderWidth == mBorderWidth) {
            return;
        }

        mBorderWidth = borderWidth;
        setup();
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        mBitmap = bm;
        setup();
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        mBitmap = getBitmapFromDrawable(drawable);
        setup();
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        mBitmap = getBitmapFromDrawable(getDrawable());
        setup();
    }

    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        if (drawable == null) {
            return null;
        }

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        try {
            Bitmap bitmap;

            if (drawable instanceof ColorDrawable) {
                bitmap = Bitmap.createBitmap(COLORDRAWABLE_DIMENSION, COLORDRAWABLE_DIMENSION, BITMAP_CONFIG);
            } else {
                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), BITMAP_CONFIG);
            }

            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (OutOfMemoryError e) {
            return null;
        }
    }

    private void setup() {
        if (!mReady) {
            mSetupPending = true;
            return;
        }

        if (mBitmap == null) {
            return;
        }

        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        mBitmapPaint.setAntiAlias(true);
        mBitmapPaint.setShader(mBitmapShader);

        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setColor(mBorderColor);
        mBorderPaint.setStrokeWidth(mBorderWidth);

        mBitmapHeight = mBitmap.getHeight();
        mBitmapWidth = mBitmap.getWidth();

        mBorderRect.set(0, 0, getWidth(), getHeight());
        mBorderRadius = Math.min((mBorderRect.height() - mBorderWidth) / 2, (mBorderRect.width() - mBorderWidth) / 2);

        mDrawableRect.set(mBorderWidth, mBorderWidth, mBorderRect.width() - mBorderWidth, mBorderRect.height() - mBorderWidth);
        mDrawableRadius = Math.min(mDrawableRect.height() / 2, mDrawableRect.width() / 2);

        updateShaderMatrix();
        invalidate();
    }

    private void updateShaderMatrix() {
        float scale;
        float dx = 0;
        float dy = 0;

        mShaderMatrix.set(null);

        if (mBitmapWidth * mDrawableRect.height() > mDrawableRect.width() * mBitmapHeight) {
            scale = mDrawableRect.height() / (float) mBitmapHeight;
            dx = (mDrawableRect.width() - mBitmapWidth * scale) * 0.5f;
        } else {
            scale = mDrawableRect.width() / (float) mBitmapWidth;
            dy = (mDrawableRect.height() - mBitmapHeight * scale) * 0.5f;
        }

        mShaderMatrix.setScale(scale, scale);
        mShaderMatrix.postTranslate((int) (dx + 0.5f) + mBorderWidth, (int) (dy + 0.5f) + mBorderWidth);

        mBitmapShader.setLocalMatrix(mShaderMatrix);
    }
}