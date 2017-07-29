package com.pengzhangdemo.com.myapplication.bigbigpeng3.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pengzhangdemo.com.myapplication.R;
import com.pengzhangdemo.com.myapplication.widget.AnimationBorderHexagonImageView;
import com.pengzhangdemo.com.myapplication.widget.FixedHexoRoundPathView;
import com.pengzhangdemo.com.myapplication.widget.PathMultiView;

public class GiftRemainActivity extends AppCompatActivity {



    AnimationBorderHexagonImageView fixedHexoRoundRecyclePathView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_remain);

        fixedHexoRoundRecyclePathView = (AnimationBorderHexagonImageView) findViewById(R.id.round_recycle);



    }

    public void onClick(View view) {
        ((PathMultiView) view).animation();
    }

    public void onClick1(View view) {
        FixedHexoRoundPathView pathView = (FixedHexoRoundPathView) view;
        pathView.timeTaskStart();



    }
}
