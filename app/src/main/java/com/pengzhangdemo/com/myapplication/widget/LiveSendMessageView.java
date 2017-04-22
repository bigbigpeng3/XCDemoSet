package com.pengzhangdemo.com.myapplication.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pengzhangdemo.com.myapplication.R;

/**
 * Created by zp on 4/22/17.
 */

public class LiveSendMessageView extends LinearLayout implements View.OnClickListener{


    public static final int STATUS_ROOM = 0;
    public static final int STATUS_ALL_ROOM = 1;
    public static final int STATUS_TRANSFER = 2;


    /**
     * 上下文对象
     */
    private Context mContext;


    /**
     * 输入框
     */
    private EditText etInput;

    /**
     * 删除键
     */
    private ImageView ivDelete;

    /**
     * 发送按钮
     */
    private Button btnSend;

    /**
     *  更换发送方式 按钮
     */
    private TextView tvChangeSpace;

    private boolean isBarrage = false; // 默认不是弹幕

    private int spaceStatus = STATUS_ROOM;// 默认为房间


    public LiveSendMessageView(Context context) {
        super(context);
    }

    public LiveSendMessageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LiveSendMessageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LiveSendMessageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);


    }



    public void initView(Context context){

        LayoutInflater.from(context).inflate(R.layout.custom_send_message_view, this);


        etInput = (EditText) findViewById(R.id.edit_text);
        ivDelete = (ImageView) findViewById(R.id.iv_delete);
        btnSend = (Button) findViewById(R.id.btn_send);



    }


    @Override
    public void onClick(View v) {

    }
}
