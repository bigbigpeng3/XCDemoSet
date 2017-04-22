package com.pengzhangdemo.com.myapplication.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pengzhangdemo.com.myapplication.R;

/**
 * Created by zp on 4/22/17.
 */

public class LiveSendMessageView extends LinearLayout implements View.OnClickListener{

    public static final String TAG = "LiveSendMessageView";


    public static final String STATUS_ROOM = "房间";
    public static final String STATUS_ALL_ROOM = "全站";
    public static final String STATUS_TRANSFER = "传送";




    /**
     * 上下文对象
     */
    private Context mContext;


    /**
     * 弹幕 选择
     */
    private CustomSwitch mSwitch;

    /**
     *  更换发送方式 按钮
     */
    private TextView tvChangeSpace;

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



    private boolean isBarrage = false; // 默认不是弹幕

    private String spaceStatus = STATUS_ROOM;// 默认为房间

    private int spaceCount = 0;


    public LiveSendMessageView(Context context) {
        super(context);
        initView(context);
    }

    public LiveSendMessageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public LiveSendMessageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context);
    }

    public LiveSendMessageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    public void initView(Context context){

        Log.e(TAG,"initView");

        this.mContext = context;

        LayoutInflater.from(context).inflate(R.layout.custom_send_message_view, this);

        etInput = (EditText) findViewById(R.id.edit_text);
        ivDelete = (ImageView) findViewById(R.id.iv_delete);
        btnSend = (Button) findViewById(R.id.btn_send);
        mSwitch = (CustomSwitch) findViewById(R.id.cs_switch);
        tvChangeSpace = (TextView) findViewById(R.id.tv_change_space);


        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    tvChangeSpaceShowAnim();
                }else {
                    tcChangeSpaceDismissAnim();
                }
            }
        });


        initTvChangeSpace();
        tvChangeSpace.setOnClickListener(this);


    }




    @Override
    public void onClick(View v) {

        if (v == tvChangeSpace){
            changeSpace();
        }

    }

    private void changeSpace() {

        if (spaceCount == 0){
            tvChangeSpace.setText(STATUS_ALL_ROOM);
            tvChangeSpace.setBackground(mContext.getApplicationContext().getDrawable(R.drawable.shape_message_room_button_bg2));
            tvChangeSpace.setTextColor(mContext.getApplicationContext().getResources().getColor(R.color.white));
            spaceCount++;
        }else if (spaceCount == 1){
            tvChangeSpace.setText(STATUS_TRANSFER);
            tvChangeSpace.setBackground(mContext.getApplicationContext().getDrawable(R.drawable.shape_message_room_button_bg3));
            tvChangeSpace.setTextColor(mContext.getApplicationContext().getResources().getColor(R.color.common_btn_blue_normal));
            spaceCount++;
        }else if (spaceCount == 2){
            initTvChangeSpace();
        }

    }

    private void initTvChangeSpace() {
        tvChangeSpace.setText(STATUS_ROOM);
        tvChangeSpace.setBackground(mContext.getApplicationContext().getDrawable(R.drawable.shape_message_room_button_bg1));
        tvChangeSpace.setTextColor(mContext.getApplicationContext().getResources().getColor(R.color.white));
        spaceCount = 0;
    }


    /**
     * 按钮的 展示动画效果
     */
    public void tvChangeSpaceShowAnim(){
        tvChangeSpace.setVisibility(VISIBLE);

    }

    /**
     * 按钮的消失效果
     */
    private void tcChangeSpaceDismissAnim() {
        tvChangeSpace.setVisibility(GONE);
    }


}
