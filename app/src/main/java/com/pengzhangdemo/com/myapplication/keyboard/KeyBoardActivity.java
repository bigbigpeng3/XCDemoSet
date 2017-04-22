package com.pengzhangdemo.com.myapplication.keyboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.pengzhangdemo.com.myapplication.R;
import com.pengzhangdemo.com.myapplication.utils.KeyboardUtils;

public class KeyBoardActivity extends AppCompatActivity implements View.OnClickListener {


    LinearLayout container_send;
    EditText edit_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_board);

//        container_send = (LinearLayout) findViewById(R.id.container_send);
//        edit_text = (EditText) findViewById(R.id.edit_text);



//        container_send.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {


    }



    /**
     * 显示输入框
     */
    private void showInputView() {
//        container_send.setVisibility(View.INVISIBLE);

//        edit_text.requestFocus();
//        edit_text.requestFocusFromTouch();
//        edit_text.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                KeyboardUtils.showKeyboard(KeyBoardActivity.this,edit_text);
//            }
//        }, 200);
    }




}
