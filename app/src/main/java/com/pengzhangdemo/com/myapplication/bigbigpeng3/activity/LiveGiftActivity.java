package com.pengzhangdemo.com.myapplication.bigbigpeng3.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.pengzhangdemo.com.myapplication.R;
import com.pengzhangdemo.com.myapplication.bigbigpeng3.gift.GiftSendModel;
import com.pengzhangdemo.com.myapplication.bigbigpeng3.gift.GiftView;
import com.pengzhangdemo.com.myapplication.utils.LogUtils;

public class LiveGiftActivity extends AppCompatActivity implements View.OnClickListener {

    GiftView giftView;

    Button btnSend1;
    Button btnSend2;
    Button btnSend3;
    Button btnSend4;
    Button btnSend5;
    Button btnSend6;

    Button btnSend12;
    Button btnSend22;
    Button btnSend32;
    Button btnSend42;
    Button btnSend52;
    Button btnSend62;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_gift);


        btnSend1 = (Button) findViewById(R.id.btn_send_gift1);
        btnSend2 = (Button) findViewById(R.id.btn_send_gift2);
        btnSend3 = (Button) findViewById(R.id.btn_send_gift3);
        btnSend4 = (Button) findViewById(R.id.btn_send_gift4);
        btnSend5 = (Button) findViewById(R.id.btn_send_gift5);
        btnSend6 = (Button) findViewById(R.id.btn_send_gift6);

        btnSend12 = (Button) findViewById(R.id.btn_send_gift12);
        btnSend22= (Button) findViewById(R.id.btn_send_gift22);
        btnSend32 = (Button) findViewById(R.id.btn_send_gift32);
        btnSend42 = (Button) findViewById(R.id.btn_send_gift42);
        btnSend52 = (Button) findViewById(R.id.btn_send_gift52);
        btnSend62 = (Button) findViewById(R.id.btn_send_gift62);

        btnSend1.setOnClickListener(this);
        btnSend2.setOnClickListener(this);
        btnSend3.setOnClickListener(this);
        btnSend4.setOnClickListener(this);
        btnSend5.setOnClickListener(this);
        btnSend6.setOnClickListener(this);

        btnSend12.setOnClickListener(this);
        btnSend22.setOnClickListener(this);
        btnSend32.setOnClickListener(this);
        btnSend42.setOnClickListener(this);
        btnSend52.setOnClickListener(this);
        btnSend62.setOnClickListener(this);


        giftView = (GiftView) findViewById(R.id.giftView);
        giftView.setViewCount(4);
        giftView.init();

    }


    @Override
    public void onClick(View v) {

        if (v == btnSend1) {
            sendGift(btnSend1);
        } else if (v == btnSend2) {
            sendGift(btnSend2);
        } else if (v == btnSend3) {
            sendGift(btnSend3);
        } else if (v == btnSend4) {
            sendGift(btnSend4);
        } else if (v == btnSend5) {
            sendGift(btnSend5);
        } else if (v == btnSend6) {
            sendGift(btnSend6);
        }else if (v == btnSend12) {
            sendGiftTen(btnSend12);
        }else if (v == btnSend22) {
            sendGiftTen(btnSend22);
        } else if (v == btnSend32) {
            sendGiftTen(btnSend32);
        } else if (v == btnSend42) {
            sendGiftTen(btnSend42);
        } else if (v == btnSend52) {
            sendGiftTen(btnSend52);
        } else if (v == btnSend62) {
            sendGiftTen(btnSend62);
        }


    }


    private void sendGift(Button v) {

        LogUtils.e("sendGift  bttton = " + v.getText().toString());

        GiftSendModel giftSendModel = new GiftSendModel(1);
        String s = v.getText().toString();
        giftSendModel.setNickname(s);
        giftView.addGift(giftSendModel);
    }

    private void sendGiftTen(Button v) {

        LogUtils.e("sendGift  bttton = " + v.getText().toString());

        GiftSendModel giftSendModel = new GiftSendModel(99);
        String s = v.getText().toString();
        giftSendModel.setNickname(s);
        giftView.addGift(giftSendModel);
    }


}
