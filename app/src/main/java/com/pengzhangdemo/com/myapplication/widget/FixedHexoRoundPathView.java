package com.pengzhangdemo.com.myapplication.widget;

/**
 * Created by Administrator on 2017/5/11.
 */

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * 绘制多边形
 */
public class FixedHexoRoundPathView extends View {

    public static final int DELAY = 3 * 1000;

    private Paint beneathPaint;
    private Path path;
    private Path dst;
    private PathMeasure pathMeasure;
    private int width, height;
    private int strokeWidth = 10;
    int start = -120;
    private float radius;

    public FixedHexoRoundPathView(Context context) {
        this(context, null);
    }

    public FixedHexoRoundPathView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FixedHexoRoundPathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {

        width = 300;
        height = 300;

        path = new Path();
        dst = new Path();


//        dst.setColor(Color.YELLOW);
        pathMeasure = new PathMeasure();
        beneathPaint = new Paint();

        beneathPaint.setAntiAlias(true);
        beneathPaint.setStrokeWidth(strokeWidth);

        beneathPaint.setColor(Color.YELLOW);
        beneathPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w ;
        height = h ;
        int temp = Math.min(w, h);
        radius = temp / 2 - 2 * strokeWidth;

//        drawMultShape(6);
        drawRadius();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.translate(width, raduis);//
////        canvas.translate(width / 2f, height / 2f);//
        canvas.save();
        canvas.scale(0.8f,0.8f,width / 2f, height / 2f);

        beneathPaint.setColor(Color.GRAY);
        beneathPaint.setStrokeWidth(strokeWidth);
        canvas.drawPath(path, beneathPaint);

        beneathPaint.setStrokeWidth(strokeWidth + 1);
        beneathPaint.setColor(Color.YELLOW);

        canvas.drawPath(dst, beneathPaint);

        canvas.restore();
    }

    /**
     * @param count 绘制几边形
     */
    public void drawMultShape(int count) {
        path.reset();
        dst.reset();
        if (count < 5) {
            return;
        }
        for (int i = 0; i < count; i++) {
            if (i == 0) {
                path.moveTo(radius * cos(360 / count * i) + radius / 2, radius * sin(360 / count * i));//绘制起点
                //加的 radius / 2  是将起始位置移到上面的中点
            } else {
                path.lineTo(radius * cos(360 / count * i), radius * sin(360 / count * i));
            }
        }

        path.lineTo(radius * cos(0), radius * sin(0));//绘制终点
        path.close();

        // 赋值给 pathMeasure 这样 之前画的六边形路径 就被记录下来了。然后直接将dst画笔直接画一遍，就形成了初始的外环颜色。
        pathMeasure = new PathMeasure(path, true);
        pathMeasure.getSegment(0, pathMeasure.getLength(), dst, true);

        //因为我下面不再绘制内容了 所以画布就不恢复了
    }


    float sin(int num) {
        num += start;
        return (float) Math.sin(num * Math.PI / 180);
    }

    float cos(int num) {
        num += start;
        return (float) Math.cos(num * Math.PI / 180);
    }


    ValueAnimator animator;

    public void animation() {

        if (animator == null) {

            animator = ValueAnimator.ofInt(DELAY);
            animator.setDuration(DELAY);
            animator.setInterpolator(new LinearInterpolator());
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int value = (int) animation.getAnimatedValue();
                    Log.e("animation", "onAnimationUpdate: " + value);
                    dst.reset();
                    if (value > 0) {
                        // 灰色每次都需要更新长度，这样就会有一个动画效果。
                        pathMeasure.getSegment(0, pathMeasure.getLength() - (pathMeasure.getLength() * value) / DELAY, dst, true);
                    }
                    FixedHexoRoundPathView.this.postInvalidate();
                }
            });

            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    dst.reset();
                    pathMeasure.getSegment(0, pathMeasure.getLength(), dst, true);
                    postInvalidate();
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    dst.reset();
                    pathMeasure.getSegment(0, pathMeasure.getLength(), dst, true);
                    postInvalidate();
                }
            });

        }
        if (animator.isRunning()) {
            animator.cancel();
        }
        animator.start();
    }

    //pathMeasure.getLength() -

    public void setAnimationListener(ValueAnimator.AnimatorListener listener) {
        if (animator != null) {
            animator.addListener(listener);
        }
    }

    /**
     * 自己实现的 正六边形 + 圆角
     */
    private void drawRadius() {

        int mWidth = width;
        int mHeight = height;

        int mLenght = mWidth / 2;

        double radian30 = 30 * Math.PI / 180;
        double radian60 = 60 * Math.PI / 180;

        float a1 = (float) (width / 2 * Math.sin(radian30));// 圆半径的4分之1
        float b1 = (float) (width / 2 * Math.cos(radian30));// 等边三角形的高
        float c = (mHeight - 2 * b1) / 2;//圆半径 - 等边三角形的高

        // 画一个圆 测试 能否通过圆的最远顶点画 圆角 图片 如果按顶点是是小圆的圆心的话，就超出了图形的范围。还有就是 line 的位置也不太对。
//        canvas.drawCircle(getWidth(),getHeight() / 2,mLenght/8,mBorderPaint);

        // 六个顶点作为 控制点。多出来的两个点，分别作为 起点和终点。
        // 确定方法后，要做的就是计算出 多出来的 2 * 6 个点了。 设定它的总长度为r/4 那么在一边的长度就是 r/8

        float radius = mWidth / 32;// 作为需要切分的长度

        float verticalHalf = (float) (radius * Math.tan(radian60));// 算出 最左边两个点的 差值。是全部竖直于 圆的直径的长度的一半

        float diffY = radius;// 上下 8 个点的差距 Y
        float diffX = (float) Math.abs(Math.sqrt(verticalHalf * verticalHalf + radius * radius));// 上下 8 个点的较长的差距 总长X 是 (radius^2 + verticalHalf^2)开根号


        path.moveTo(getWidth() / 2, getWidth() / 2 - b1);//绘制起点  就是 上方的中间值。因为动画的起点需要在中间上方。

        //        path.lineTo(getWidth() - a1, c);// 上2 (由左边数)
        path.lineTo(getWidth() - a1 - diffX, c);// 上2 (由左边数)
        path.quadTo(getWidth() - a1, c, getWidth() - a1 + radius, c + verticalHalf);


        path.lineTo(getWidth() - radius, getHeight() / 2 - verticalHalf);// 最右上

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


        path.close();// 连接上2 和 最右

//        canvas.drawPath(path, mPaint);

//        canvas.drawText();

//        pathMeasure = new PathMeasure(path, true);
        pathMeasure.setPath(path, true);
        pathMeasure.getSegment(0, pathMeasure.getLength(), dst, true);

    }

}


/**
 * 实现倒计时 定时实现的效果不佳，放弃
 */
//    public void startCount() {

//        final int animNum = 16;
//
//        if (timer == null) {
//
//            timer = new CountDownTimer(DELAY, animNum) {
//
//                @Override
//                public void onTick(long millisUntilFinished) {
//
//                    progress = DELAY / animNum - (millisUntilFinished / animNum);
//                    dst.reset();
//                    pathMeasure.getSegment(0, (pathMeasure.getLength() / (DELAY / animNum)) * progress, dst, true);
//                    PathMultiView.this.postInvalidate();
//                    Log.e("startCount", "onTick: " + progress + " -- " + millisUntilFinished);
//
//                }
//
//                @Override
//                public void onFinish() {
//                    Log.e("startCount", "onFinish: ");
//                    // 画完
//                    pathMeasure.getSegment(0, pathMeasure.getLength(), dst, true);
//                    PathMultiView.this.postInvalidate();
//                }
//            };
//
//        }

//        timer.start();


//    }
