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
        if (v == btnVideo){
            startActivity(new Intent(this, VideoListActivity.class));
        }else if (v == btnRecord){
            startActivity(new Intent(this, RecordActvity.class));
        }else if (v == btnkeyboard){
            startActivity(new Intent(this, KeyBoardActivity.class));
        }

    }
}
