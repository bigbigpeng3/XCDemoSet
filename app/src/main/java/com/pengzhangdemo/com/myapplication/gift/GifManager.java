package com.pengzhangdemo.com.myapplication.gift;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;

import com.pengzhangdemo.com.myapplication.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ying on 2016/11/12.
 */

public class GifManager {


    GiftQueue queue;
    List<GiftFrameLayout> viewList;

    public GifManager() {
        viewList = new ArrayList<>();
        queue = new GiftQueue();
    }

    public void addView(GiftFrameLayout giftFrameLayout) {
        viewList.add(giftFrameLayout);
    }

    public void addGift(GiftSendModel giftSendModel) {

        GiftFrameLayout showView = getShowCurrView(giftSendModel);

        if (showView != null) {

            //当前添加的礼物已经在播放了，直接修改播放次数
//            int count = giftSendModel.getGiftCount() + showView.getCombo();

            showView.setModel(giftSendModel);
            showView.setCombo();
            LogUtils.e("GifManager   addGift。。。。 showView != null showView.setCombo(count);");

            return;
        }

        LogUtils.e("GifManager   addGift。。。。 showView == null queue.add(giftSendModel);");

        queue.add(giftSendModel);
        beingAnimotion();
    }

    public void beingAnimotion() {
        //有可用的控件就立即播放
        GiftFrameLayout hideView = getIdleView();
        if (hideView != null) {
            beginAnimotion(hideView);
        }
    }


    private GiftFrameLayout getShowCurrView(GiftSendModel model) {
        for (GiftFrameLayout giftFrameLayout : viewList) {
            if (giftFrameLayout.isShowing() && giftFrameLayout.equalsCurrentModel(model)) {
                return giftFrameLayout;
            }
        }
        return null;
    }

    /**
     * 获取当前没有展示的view
     * <p>
     * 1、从后往前获取。done
     * 2、
     *
     * @return
     */
    private GiftFrameLayout getIdleView() {
//        for (GiftFrameLayout giftFrameLayout : viewList) {
//            if (!giftFrameLayout.isShowing()) {
//                return giftFrameLayout;
//            }
//        }
        for (int i = viewList.size() - 1; i >= 0; i--) {

            GiftFrameLayout giftFrameLayout = viewList.get(i);

            if (!giftFrameLayout.isShowing()) {
                return giftFrameLayout;
            }
        }
        return null;
    }


    public void beginAnimotion(final GiftFrameLayout view) {

        GiftSendModel model = queue.removeTop();
        if (model == null) {
            return;
        }
        view.setModel(model);

        view.startAnimation();//model.getGiftCount()

        view.setOnGiftEndListener(new GiftFrameLayout.OnGiftEndListener() {
            @Override
            public void onGiftEnd() {
                LogUtils.e("GifManager   setOnGiftEndListener。。。。  synchronized (queue)");
                synchronized (queue) {
                    //礼物队列里还存在礼物的情况
                    if (!queue.isEmpty()) {
                        beginAnimotion(view);
                    }
                }
            }
        });

    }
}


//        AnimatorSet animatorSet = view.endAnimation();// 获取结束后动画的调用
//        view.endAnimation().addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//
//                LogUtils.e("GifManager   beginAnimotion。。。。  synchronized (queue)");
//                super.onAnimationEnd(animation);
//
//                synchronized (queue) {
//                    //礼物队列里还存在礼物的情况
//                    if (!queue.isEmpty()) {
//                        beginAnimotion(view);
//                    }
//                }
//            }
//        });