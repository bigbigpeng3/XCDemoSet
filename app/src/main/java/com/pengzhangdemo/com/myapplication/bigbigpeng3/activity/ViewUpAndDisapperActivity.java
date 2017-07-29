package com.pengzhangdemo.com.myapplication.bigbigpeng3.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.pengzhangdemo.com.myapplication.R;
import com.pengzhangdemo.com.myapplication.app.BaseApplication;

public class ViewUpAndDisapperActivity extends AppCompatActivity {


    public int screenWidth;
    public int screenHeight;

    FrameLayout frameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_up_and_disapper);
        frameLayout = (FrameLayout) findViewById(R.id.fl_main);

        screenWidth = BaseApplication.getScreenWidth();
        screenHeight = BaseApplication.getScreenHeight();


        showAnim();

    }

    private void showAnim() {
        final ImageView image = new ImageView(this);

        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);

//        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
//                lp.bottomMargin = 0;
//        lp.bottomMargin = (int) getContext().getApplicationContext().getResources().getDimension(R.dimen.gift_margin_top);

//        giftFrameLayout.setId(R.id.gift_frame0);

        image.setImageResource(R.drawable.vip_lv00);
        image.setLayoutParams(lp);

        frameLayout.addView(image);

        Path path = new Path();

        path.moveTo(100, 300);
//        path.quadTo(image.getX(), image.getY(), screenWidth / 2, 0);
        path.quadTo(300, -500, screenWidth - 50, screenHeight + 20);


//        PathInterpolator pathInterpolator = new PathInterpolator(screenWidth / 2, 0);
        AnimatorSet animSet = new AnimatorSet();
        animSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                frameLayout.removeView(image);
            }
        });
        ObjectAnimator traslateAnimator = ObjectAnimator.ofFloat(image, "x", "y", path);

        animSet.playTogether(traslateAnimator);
        animSet.setInterpolator(new AccelerateInterpolator());
//        animSet.setInterpolator(pathInterpolator);
        animSet.setDuration(2000);
        animSet.start();
    }




}
