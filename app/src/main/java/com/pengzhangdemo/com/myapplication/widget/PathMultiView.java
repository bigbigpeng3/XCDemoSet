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
public class PathMultiView extends View {

    public static final int DELAY = 30 * 1000;

    private Paint beneathPaint;
    private Path path;
    private Path dst;
    PathMeasure pathMeasure;
    private int width, height;
    private int strokeWidth = 10;
    int start = -120;
    private float radius;

    public PathMultiView(Context context) {
        this(context, null);
    }

    public PathMultiView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathMultiView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        width = w;
        height = h;
        int temp = Math.min(w, h);
        radius = temp / 2 - 2 * strokeWidth;
        drawMultShape(6);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.translate(width, raduis);//
        canvas.translate(width / 2f, height / 2f);//

        beneathPaint.setColor(Color.GRAY);
        beneathPaint.setStrokeWidth(strokeWidth);
        canvas.drawPath(path, beneathPaint);
        beneathPaint.setStrokeWidth(strokeWidth + 2);
        beneathPaint.setColor(Color.YELLOW);
        canvas.drawPath(dst, beneathPaint);


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
//        pathMeasure = new PathMeasure(path, true);
        pathMeasure.setPath(path,true);
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
//                    if (value > 0) {
                    // 灰色每次都需要更新长度，这样就会有一个动画效果。
                    pathMeasure.getSegment(0, pathMeasure.getLength() - (pathMeasure.getLength() * value) / DELAY, dst, true);
//                    }
                    PathMultiView.this.postInvalidate();
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
