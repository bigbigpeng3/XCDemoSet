package com.pengzhangdemo.com.myapplication.gift;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pengzhangdemo.com.myapplication.R;
import com.pengzhangdemo.com.myapplication.utils.LogUtils;
import com.pengzhangdemo.com.myapplication.widget.StrokeTextView;


public class GiftFrameLayout extends FrameLayout {

    private LayoutInflater mInflater;

    LinearLayout anim_rl;
    ImageView anim_gift, anim_header;//anim_light
    TextView anim_nickname, anim_sign;
    StrokeTextView anim_num;

    /**
     * 礼物数量的起始值
     */
    int starNum = 1;
    int repeatCount = 0;
    private boolean isShowing = false;

    private String nick;

    private ObjectAnimator scaleGiftNum;
    private boolean scaleEnded = false;

    public GiftFrameLayout(Context context) {
        this(context, null);
    }

    public GiftFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInflater = LayoutInflater.from(context);
        initView();
    }


    private void initView() {
        View view = mInflater.inflate(R.layout.my_gift_frame, this, false);
        anim_rl = (LinearLayout) view.findViewById(R.id.animation_person_rl);
        anim_gift = (ImageView) view.findViewById(R.id.animation_gift);
//        anim_light = (ImageView) view.findViewById(R.id.animation_light);
        anim_num = (StrokeTextView) view.findViewById(R.id.animation_num);
        anim_header = (ImageView) view.findViewById(R.id.gift_userheader_iv);
        anim_nickname = (TextView) view.findViewById(R.id.gift_usernickname_tv);
        anim_sign = (TextView) view.findViewById(R.id.gift_usersign_tv);
        this.addView(view);
    }

    public void hideView() {
        anim_gift.setVisibility(INVISIBLE);
//        anim_light.setVisibility(INVISIBLE);
        anim_num.setVisibility(INVISIBLE);
    }

    public String getNick() {
        return nick;
    }

    private GiftSendModel model;

    public GiftSendModel getModel() {
        return model;
    }

    public boolean equalsCurrentModel(GiftSendModel model) {
        return this.model.equals(model);
    }


    public void setModel(GiftSendModel model) {
        this.model = model;
        if (0 != model.getGiftCount()) {
            this.repeatCount = model.getGiftCount();
            setRepeatCount(model.getGiftCount());
        }
        if (!TextUtils.isEmpty(model.getNickname())) {
            anim_nickname.setText(model.getNickname());
        }
        if (!TextUtils.isEmpty(model.getSig())) {
            anim_sign.setText(model.getSig());
        }
        this.nick = anim_nickname.getText().toString();
    }

    public boolean isShowing() {
        return isShowing;
    }

    public int getRepeatCount() {
        return repeatCount;
    }


    public void setRepeatCount(int repeatCount) {
        LogUtils.e("GiftFrameLayout   setRepeatCount ");
        this.repeatCount = repeatCount;
        if (scaleGiftNum != null) {

            if (scaleGiftNum.isRunning()) {
                scaleGiftNum.setRepeatCount(repeatCount - 1);
                LogUtils.e("GiftFrameLayout   setRepeatCount scaleGiftNum != null scaleGiftNum.isRunning()");
                return;
            }
            //最好是在上面解决问题。那么就需要一个 零动作 动画，
            //因为上面已经return了。所以不用担心动画在没有执行完成的情况，会执行到这里。

        } else {
            LogUtils.e("GiftFrameLayout   setRepeatCount scaleGiftNum == null ");
        }

    }

    public AnimatorSet startAnimation(final int repeatCount) {
        hideView();
        //布局飞入
        ObjectAnimator flyFromLtoR = GiftAnimationUtil.createFlyFromLtoR(anim_rl, -getWidth(), 0, 500, new AccelerateInterpolator());
        flyFromLtoR.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                GiftFrameLayout.this.setVisibility(View.VISIBLE);
                GiftFrameLayout.this.setAlpha(1f);
                isShowing = true;
                anim_num.setText("x " + 1);
                Log.i("TAG", "flyFromLtoR A start");

                anim_gift.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
//                GiftAnimationUtil.startAnimationDrawable(anim_light);
                anim_num.setVisibility(View.VISIBLE);
            }

        });
        //礼物飞入
//        ObjectAnimator flyFromLtoR2 = GiftAnimationUtil.createFlyFromLtoR(anim_gift, -getWidth(), 0, 400,new DecelerateInterpolator());
//        flyFromLtoR2.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//                anim_gift.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                    GiftAnimationUtil.startAnimationDrawable(anim_light);
//                     anim_num.setVisibility(View.VISIBLE);
//            }
//        });
        //数量增加-*+/
        scaleGiftNum = GiftAnimationUtil.scaleGiftNum(anim_num, repeatCount);
        scaleGiftNum.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationRepeat(Animator animation) {
                anim_num.setText("x " + (++starNum));
            }

        });
        //向右渐变消失 startDuration可以控制 整个框框停留的时间。
        ObjectAnimator fadeAnimator = GiftAnimationUtil.createFadeAnimator(GiftFrameLayout.this, 0, 100, 500, 3000);
        fadeAnimator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                GiftFrameLayout.this.setVisibility(View.INVISIBLE);
            }
        });
        // 复原 原本View的位置。
        ObjectAnimator fadeAnimator2 = GiftAnimationUtil.createFadeAnimator(GiftFrameLayout.this, 100, 0, 20, 0);

        AnimatorSet animatorSet = GiftAnimationUtil.startAnimation(flyFromLtoR, scaleGiftNum, fadeAnimator, fadeAnimator2);
        animatorSet.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                starNum = 1;
                isShowing = false;
            }

        });
        return animatorSet;
    }


    public void comboAnimation() {
        //数量增加
        ObjectAnimator scaleGiftNum = GiftAnimationUtil.scaleGiftNum(anim_num);
        scaleGiftNum.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                anim_num.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

//                if (mHandler != null) {
//                    if (mGiftCount > mCombo) {//连击
//                        mHandler.sendEmptyMessage(RESTART_GIFT_ANIMATION_CODE);
//                    } else {
//                        mCurrentAnimRunnable = new GiftNumAnimaRunnable();
//                        mHandler.postDelayed(mCurrentAnimRunnable, GIFT_DISMISS_TIME);
//                        checkGiftCountSubscribe();
//                    }
//                }


            }
        });
        scaleGiftNum.start();
    }


}
