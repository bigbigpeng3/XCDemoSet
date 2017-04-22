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


public class FixedHexagonImageView extends ImageView {

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

    public FixedHexagonImageView(Context context) {
        super(context);
    }

    public FixedHexagonImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FixedHexagonImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        super.setScaleType(SCALE_TYPE);
        initView(context, attrs, defStyle);

    }

    private void initView(Context context, AttributeSet attrs, int defStyle) {
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

//        drawRadius(canvas,mDrawableRadius,mBitmapPaint);
//        drawRadius(canvas,mBorderRadius,mBorderPaint);

        drawRadius(canvas,mBitmapPaint);
        drawRadius(canvas,mBorderPaint);

    }

    /**
     * 自己实现的 正六边形 + 圆角
     * @param canvas
     * @param mPaint
     */
    private void drawRadius(Canvas canvas, Paint mPaint) {

        int mWidth = getWidth();
        int mHeight = getHeight();

        int mLenght = mWidth / 2;

        double radian30 = 30 * Math.PI / 180;
        double radian60 = 60 * Math.PI / 180;

        float a1 = (float) (mDrawableRadius * Math.sin(radian30));// 圆半径的4分之1
        float b1 = (float) (mDrawableRadius * Math.cos(radian30));// 等边三角形的高
        float c = (mHeight - 2 * b1) / 2;//圆半径 - 等边三角形的高


        // 画一个圆 测试 能否通过圆的最远顶点画 圆角 图片 如果按顶点是是小圆的圆心的话，就超出了图形的范围。还有就是 line 的位置也不太对。
//        canvas.drawCircle(getWidth(),getHeight() / 2,mLenght/8,mBorderPaint);

        // 六个顶点作为 控制点。多出来的两个点，分别作为 起点和终点。
        // 确定方法后，要做的就是计算出 多出来的 2 * 6 个点了。 设定它的总长度为r/4 那么在一边的长度就是 r/8

        float radius = getWidth() / 32;// 作为需要切分的长度

        float verticalHalf = (float) (radius * Math.tan(radian60));// 算出 最左边两个点的 差值。是全部竖直于 圆的直径的长度的一半

        float diffY = radius;// 上下 8 个点的差距 Y
        float diffX = (float) Math.abs(Math.sqrt(verticalHalf * verticalHalf + radius * radius));// 上下 8 个点的较长的差距 总长X 是 (radius^2 + verticalHalf^2)开根号


        Path path = new Path();

        //        path.moveTo(getWidth(), getHeight() / 2);// 最右
        path.moveTo(getWidth() - radius, getHeight() / 2 - verticalHalf);// 最右上

        path.quadTo(getWidth(), getHeight() / 2, getWidth() - radius, getHeight() / 2 + verticalHalf);//第一个点是辅助点 第二个点是终止点 画弧


//        path.lineTo(getWidth() - a1, getHeight() - c);// 下2 (由左边数) 分裂成下面两个点
        path.lineTo(getWidth() - a1 + radius, getHeight() - c - diffY);// 下2 (由左边数)

        path.quadTo(getWidth() - a1, getHeight() - c, getWidth() - a1 - diffX, getHeight() - c);

//        path.lineTo(getWidth() - a1 - mLenght, getHeight() - c);// 下1 (由左边数)分裂成下面两个点
        path.lineTo(getWidth() - a1 - mLenght + diffX, getHeight() - c);// 下1 (由左边数)
        path.quadTo(getWidth() - a1 - mLenght, getHeight() - c, getWidth() - a1 - mLenght - radius, getHeight() - c - diffY);

//        path.lineTo(0, getHeight() / 2);// 最左
        // 将最左点  变成两个点
        path.lineTo(0 + radius, getHeight() / 2 + verticalHalf);// 下方点
        path.quadTo(0, getHeight() / 2, radius, getHeight() / 2 - verticalHalf);

//        path.lineTo(a1, c);// 上1 (由左边数) 分裂成下面两个点
        path.lineTo(a1 - radius, c + verticalHalf);// 上1 (由左边数)
        path.quadTo(a1, c, a1 + diffX, c);

//        path.lineTo(getWidth() - a1, c);// 上2 (由左边数)
        path.lineTo(getWidth() - a1 - diffX, c);// 上2 (由左边数)


        path.quadTo(getWidth() - a1, c, getWidth() - a1 + radius, c + verticalHalf);

        path.close();// 连接上2 和 最右

        canvas.drawPath(path, mPaint);

    }



    //画一个带倒角的正六边形，主要是一个算法，描点。
    private void drawRadius(Canvas canvas, float radius, Paint paint) {

        double radian30 = 30 * Math.PI / 180;
        double radian60 = 60 * Math.PI / 180;

        float centerX = getWidth() / 2;
        float centerY = getHeight() / 2;

        //  通过此值修改正六边形的倒角弧度
        float z1 = radius / 8;
        float x1 = (float) (z1 * Math.cos(radian30));
        float y1 = (float) (z1 * Math.sin(radian30));
        float m1 = (float) Math.abs(Math.sqrt(z1 * z1 + radius * radius - radius * z1));
        float o1 = (float) Math.asin(z1 * Math.sin(radian60) / m1);
        float p1 = (float) (m1 * Math.sin(radian60 - o1));
        float q1 = (float) (m1 * Math.cos(radian60 - o1));
        float e1 = (float) (m1 * Math.cos(radian30 - o1));
        float f1 = (float) (m1 * Math.sin(radian30 - o1));

        float a1 = (float) (radius * Math.sin(radian30));
        float b1 = (float) (radius * Math.cos(radian30));

        Path drawablePath = new Path();

        drawablePath.moveTo(centerX - x1, y1);//起始点坐标

        drawablePath.quadTo(centerX, 0, centerX + x1, y1); //第一个点是辅助点  //第二个点是终止点  画弧

        drawablePath.lineTo(centerX + p1, centerY - q1);

        drawablePath.quadTo(centerX + b1, centerY - a1, centerX + e1, centerY - f1);

        drawablePath.lineTo(centerX + e1, centerY + f1);

        drawablePath.quadTo(centerX + b1, centerY + a1, centerX + p1, centerY + q1);

        drawablePath.lineTo(centerX + x1, getHeight() - y1);

        drawablePath.quadTo(centerX, getHeight(), centerX - x1, getHeight() - y1);

        drawablePath.lineTo(centerX - p1, centerY + q1);

        drawablePath.quadTo(centerX - b1, centerY + a1, centerX - e1, centerY + f1);

        drawablePath.lineTo(centerX - e1, centerY - f1);

        drawablePath.quadTo(centerX - b1, centerY - a1, centerX - p1, centerY - q1);

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