package com.pengzhangdemo.com.myapplication;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pengzhangdemo.com.myapplication.numpic.NumToPicView;
import com.pengzhangdemo.com.myapplication.numpic.NumView;
import com.pengzhangdemo.com.myapplication.utils.TextTypeUtils;
import com.pengzhangdemo.com.myapplication.widget.RandomTextView;
import com.pengzhangdemo.com.myapplication.widget.StrokeTextView;

/**
 * 将数字转化成为图片
 */
public class NumToPicActivity extends AppCompatActivity implements View.OnClickListener {


    TextView tvSpan;
    StrokeTextView tvTtf;
    NumToPicView numToPicView;
    Button btnAdd;
    Button btnAddTen;
    NumView numView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num_to_pic);


        tvSpan = (TextView) findViewById(R.id.tv_span);
        tvTtf = (StrokeTextView) findViewById(R.id.tv_ttf);

        numToPicView = (NumToPicView) findViewById(R.id.num_pic_view);
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnAddTen = (Button) findViewById(R.id.btn_add_ten);

        numView = (NumView) findViewById(R.id.nv_change);

        btnAdd.setOnClickListener(this);
        btnAddTen.setOnClickListener(this);


        initTvSpan();

        initNumToPic();


        numView.setNum(1);
//        ImageView

        tvTtf.setTypeface(TextTypeUtils.getLevelTypeFace(this));
//        tvTtf.setText(R.string.num_zero);
//        tvTtf.setText("1234567890");

        tvTtf.setText("12345");
        tvTtf.setLetterSpacing(0.1f);// 文字的间距问题
        tvTtf.setPianyilian(RandomTextView.FIRSTF_FIRST);
        tvTtf.start();
    }


    @Override
    public void onClick(View v) {
        if (v == btnAdd) {
            addNum(1);
        } else if (v == btnAddTen) {
            addNum(10);
        }
    }


    /**
     * 通过LinearLayout来添加View
     */
    private void initNumToPic() {
        numToPicView.setNum(1234567890);
    }


    /**
     * 设置添加的数量，来观察效果
     *
     * @param addNum
     */
    private void addNum(int addNum) {
        int num = numToPicView.getNum();
        numToPicView.setNum(num + addNum);


        String tvStr = tvTtf.getText().toString().trim();

        int tvNum = Integer.valueOf(tvStr);


        // 每次添加都转一次。
        tvTtf.setText("" + (tvNum + addNum));
        tvTtf.setPianyilian(RandomTextView.FIRSTF_FIRST);
        tvTtf.start();



    }


    /**
     * 这种方式还是得放弃。因为很难做动画。每次都设置bounds也不优美。
     */
    private void initTvSpan() {

        SpannableString spannableString = new SpannableString("在文本中添加表情（表情）");

        Drawable drawable = getResources().getDrawable(R.drawable.gno_ya_0);

        drawable.setBounds(0, 0, 42, 42);

        ImageSpan imageSpan = new ImageSpan(drawable);

        spannableString.setSpan(imageSpan, 6, 8, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tvSpan.setText(spannableString);
    }




}
