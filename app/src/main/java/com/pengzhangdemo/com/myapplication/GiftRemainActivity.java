package com.pengzhangdemo.com.myapplication;

import android.animation.Animator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pengzhangdemo.com.myapplication.widget.FixedHexoRoundPathView;
import com.pengzhangdemo.com.myapplication.widget.PathMultiView;

public class GiftRemainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_remain);




    }

    public void onClick(View view) {
        ((PathMultiView) view).animation();
    }

    public void onClick1(View view) {
        FixedHexoRoundPathView pathView = (FixedHexoRoundPathView) view;
        pathView.timeTaskStart();



    }
}
