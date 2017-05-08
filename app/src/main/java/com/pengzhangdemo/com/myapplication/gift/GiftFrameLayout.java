package com.pengzhangdemo.com.myapplication.gift;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
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


public class GiftFrameLayout extends FrameLayout implements Handler.Callback {

    private static final int RESTART_GIFT_ANIMATION_CODE = 1002;

    private LayoutInflater mInflater;

    LinearLayout anim_rl;
    ImageView anim_gift, anim_header;//anim_light
    TextView anim_nickname, anim_sign;
    StrokeTextView anim_num;

    /**
     * 礼物数量的起始值
     */
    int starNum = 0;
    int combo = 0;// 用户的点击送礼物次数。
    private boolean isShowing = false;

    private String nick;

    private ObjectAnimator scaleGiftNum;

    private Handler mHandler = new Handler(this);

    @Override
    public boolean handleMessage(Message msg) {//暂时不需要任何的事件
        switch (msg.what) {
            case RESTART_GIFT_ANIMATION_CODE:
//                comboAnimation();
                break;
            default:
                break;
        }
        return true;
    }


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
            starNum += model.getGiftCount();
//            this.combo = model.getGiftCount();
//            setCombo(model.getGiftCount());
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

    public int getCombo() {
        return combo;
    }


    public void setCombo() {

        LogUtils.e("GiftFrameLayout   setCombo ");
        this.combo++;
        if (scaleGiftNum != null) {

            if (scaleGiftNum.isRunning()) {// 在运行就返回
                if (combo > 0) { // 返回就说明这个动画没有执行。也是需要减去的。
                    --combo;
                }
                return;
            }


//            mHandler.sendEmptyMessage(RESTART_GIFT_ANIMATION_CODE);//没有运行就 让它运行起来。
            scaleGiftNum.start();
//            mHandler.removeCallbacksAndMessages(null);// 每次的礼物连击，都要清除当前handle的Message

        } else {
            LogUtils.e("GiftFrameLayout   setCombo scaleGiftNum == null ");
        }

    }

    public void startAnimation() {

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
                comboAnimation();
            }

        });

        flyFromLtoR.start();
    }


    public void comboAnimation() {

        //数量增加
        scaleGiftNum = GiftAnimationUtil.scaleGiftNum(anim_num);
        scaleGiftNum.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                anim_num.setVisibility(View.VISIBLE);
                anim_num.setText("x " + starNum);
//                anim_num.setText("x " + (++starNum));
                mHandler.removeCallbacksAndMessages(null);//每个动画开始时，都需要清除之前的postDelayed事件。不然会出现bug。
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (combo > 0) {
                    --combo;
                }

                if (combo == 0) {
                    mHandler.postDelayed(giftEndRun, 3000);// 执行礼物消失动画
                }

            }
        });
        scaleGiftNum.start();
    }


    private Runnable giftEndRun = new Runnable() {
        @Override
        public void run() {
            endAnimation().start();// 执行礼物消失动画
        }
    };


    public AnimatorSet endAnimation() {

        //向右渐变消失 startDuration可以控制 整个框框停留的时间。
        ObjectAnimator fadeAnimator = GiftAnimationUtil.createFadeAnimator(GiftFrameLayout.this, 0, 100, 500, 0);
        fadeAnimator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                GiftFrameLayout.this.setVisibility(View.INVISIBLE);
            }
        });
        // 复原 原本View的位置。
        ObjectAnimator fadeAnimator2 = GiftAnimationUtil.createFadeAnimator(GiftFrameLayout.this, 100, 0, 20, 0);

//        if (endAnimatorSet == null)
        AnimatorSet endAnimatorSet = new AnimatorSet();
        endAnimatorSet.play(fadeAnimator2).after(fadeAnimator);
        endAnimatorSet.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                LogUtils.e("endAnimation() onAnimationEnd");
                starNum = 0;// 恢复状态
                isShowing = false;

                if (giftEndListener != null) {// 能让Manager监听到动画结束
                    giftEndListener.onGiftEnd();
                }

            }

        });

        return endAnimatorSet;
    }


    OnGiftEndListener giftEndListener;

    public void setOnGiftEndListener(OnGiftEndListener listener) {
        giftEndListener = listener;
    }

    public interface OnGiftEndListener {
        void onGiftEnd();
    }


}


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