package com.pengzhangdemo.com.myapplication.bigbigpeng3.gift;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.pengzhangdemo.com.myapplication.R;

/**
 * Created by ying on 2016/11/16.
 */

public class BackupGiftView extends RelativeLayout {
    GifManager gifManager1;
    private int viewCount = 1;

    public BackupGiftView(Context context) {
        this(context, null);
    }

    public BackupGiftView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BackupGiftView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setViewCount(int viewCount) {
        if (viewCount < 1) {
            throw new IllegalArgumentException("viewCount 不能小于1");
        }
        this.viewCount = viewCount;
    }

    // 在LinearLayout中添加View。需要修改一些view之间的间距
    private void addGiftView() {
        for (int i = 0; i < viewCount; i++) {
//            GiftFrameLayout giftFrameLayout = new GiftFrameLayout(getContext());
            GiftFrameLayout giftFrameLayout = new GiftFrameLayout(getContext());
            giftFrameLayout.setVisibility(View.INVISIBLE);

            LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

            if (i == 0) {
                lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                lp.bottomMargin = 0;
                giftFrameLayout.setId(R.id.gift_frame0);
                giftFrameLayout.setLayoutParams(lp);

            } else if (i == 1) {
                lp.addRule(RelativeLayout.ABOVE, R.id.gift_frame0);
//                lp.bottomMargin = 20;
                giftFrameLayout.setId(R.id.gift_frame1);
                giftFrameLayout.setLayoutParams(lp);
            } else if (i == 2) {
                lp.addRule(RelativeLayout.ABOVE, R.id.gift_frame1);
//                lp.bottomMargin = 20;
                giftFrameLayout.setId(R.id.gift_frame2);
                giftFrameLayout.setLayoutParams(lp);
            } else if (i == 3) {
                lp.addRule(RelativeLayout.ABOVE, R.id.gift_frame2);
//                lp.bottomMargin = 20;
                giftFrameLayout.setId(R.id.gift_frame3);
                giftFrameLayout.setLayoutParams(lp);
            }

            gifManager1.addView(giftFrameLayout);
            addView(giftFrameLayout);
        }
    }


    public void init() {
        gifManager1 = new GifManager();
//        setOrientation(VERTICAL);
        addGiftView();
    }

    public void addGift(GiftSendModel model) {
        gifManager1.addGift(model);
    }

}
