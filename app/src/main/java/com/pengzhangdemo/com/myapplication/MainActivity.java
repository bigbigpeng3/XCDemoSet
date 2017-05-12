package com.pengzhangdemo.com.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pengzhangdemo.com.myapplication.keyboard.KeyBoardActivity;
import com.pengzhangdemo.com.myapplication.videopath.VideoListActivity;
import com.pengzhangdemo.com.myapplication.videorecord.RecordActvity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView spannableText;
    private Button btnVideo;
    private Button btnRecord;
    private Button btnkeyboard;
    private Button btnBottomSheet;
    private Button btnVPinBottomSheet;
    private Button btnLiveGift;
    private Button btnNumPic;
    private Button btnNumPic1;
    private Button btnGiftRemain;
    private Button btnDanmu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("MainActivity", "onCreate: " + getResources().getDisplayMetrics().density);


        initSpannableText();

        // 获取视频
        btnVideo = (Button) findViewById(R.id.btn_video);
        btnVideo.setOnClickListener(this);


        btnRecord = (Button) findViewById(R.id.btn_record);
        btnRecord.setOnClickListener(this);


        btnkeyboard = (Button) findViewById(R.id.btn_keyboard);
        btnkeyboard.setOnClickListener(this);

        btnBottomSheet = (Button) findViewById(R.id.btn_bottom_sheet);
        btnBottomSheet.setOnClickListener(this);

        btnVPinBottomSheet = (Button) findViewById(R.id.btn_vp_in_bottom_sheet);
        btnVPinBottomSheet.setOnClickListener(this);

        btnLiveGift = (Button) findViewById(R.id.btn_live_gift);
        btnLiveGift.setOnClickListener(this);

        btnNumPic = (Button) findViewById(R.id.btn_num_pic);
        btnNumPic.setOnClickListener(this);

        btnNumPic1 = (Button) findViewById(R.id.btn_num_pic1);
        btnNumPic1.setOnClickListener(this);

        btnGiftRemain = (Button) findViewById(R.id.btn_gift_remain);
        btnGiftRemain.setOnClickListener(this);

        btnDanmu = (Button) findViewById(R.id.btn_danmu);
        btnDanmu.setOnClickListener(this);

        findViewById(R.id.btn_normal_fragment).setOnClickListener(this);
//        RoundCornerProgressBar progress1 = (RoundCornerProgressBar) findViewById(R.id.progress_1);
//        progress1.setProgressColor(Color.parseColor("#ed3b27"));
//        progress1.setProgressBackgroundColor(Color.parseColor("#808080"));
//        progress1.setMax(70);
//        progress1.setProgress(15);

//        int progressColor1 = progress1.getProgressColor();
//        int backgroundColor1 = progress1.getProgressBackgroundColor();
//        int max1 = (int) progress1.getMax();
//        int progress1 = progress1.getProgress();

    }

    /**
     * 富文本的相关UI
     */
    private void initSpannableText() {
        spannableText = (TextView) findViewById(R.id.tv_spannable);
    }


    @Override
    public void onClick(View v) {
        if (v == btnVideo) {
            startActivity(new Intent(this, VideoListActivity.class));
        } else if (v == btnRecord) {
            startActivity(new Intent(this, RecordActvity.class));
        } else if (v == btnkeyboard) {
            startActivity(new Intent(this, KeyBoardActivity.class));
        } else if (v == btnBottomSheet) {
            startActivity(new Intent(this, BottomSheetViewPageActivity.class));
        } else if (v == btnVPinBottomSheet) {
            startActivity(new Intent(this, ViewPageInBottomSheetDialogActivity.class));
        } else if (v.getId() == R.id.btn_normal_fragment) {
            startActivity(new Intent(this, FragmentActivity.class));
        } else if (v == btnLiveGift) {//直播礼物界面
            startActivity(new Intent(this, LiveGiftActivity.class));
        } else if (v == btnNumPic) {// 数字转图片
            startActivity(new Intent(this, NumToPicActivity.class));
        } else if (v == btnNumPic1) {// 数字转图片1
            startActivity(new Intent(this, DigitalActivity.class));
        } else if (v == btnGiftRemain) {// 礼物倒计时
            startActivity(new Intent(this, GiftRemainActivity.class));
        } else if (v == btnDanmu) {// 用户弹幕
            startActivity(new Intent(this, DanmuActivity.class));
        }

    }
}
