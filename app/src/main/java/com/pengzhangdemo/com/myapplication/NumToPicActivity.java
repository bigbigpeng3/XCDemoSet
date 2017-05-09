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
import com.pengzhangdemo.com.myapplication.utils.LogUtils;
import com.pengzhangdemo.com.myapplication.utils.TextTypeUtils;
import com.pengzhangdemo.com.myapplication.widget.RandomTextView;
import com.pengzhangdemo.com.myapplication.widget.StrokeTextView;
import com.robinhood.ticker.MyTickerView;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

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
    MyTickerView myTickerView;
    TickerView mTickerView1;


    int myNum;

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
        myTickerView = (MyTickerView) findViewById(R.id.tv_num);
        mTickerView1 = (TickerView) findViewById(R.id.tv_num1);


        btnAdd.setOnClickListener(this);
        btnAddTen.setOnClickListener(this);


        initTvSpan();

        initNumToPic();


        numView.setNum(1);
//        ImageView

        tvTtf.setTypeface(TextTypeUtils.getLevelTypeFace(this));
//        tvTtf.setText(R.string.num_zero);
//        tvTtf.setText("1234567890");

        tvTtf.setText("1234567890");
        tvTtf.setLetterSpacing(0.1f);// 文字的间距问题
        tvTtf.setPianyilian(RandomTextView.FIRSTF_FIRST);
        tvTtf.start();


        myNum = 123456780;


        myTickerView.setCharacterList(TickerUtils.getDefaultNumberList());

        mTickerView1.setCharacterList(TickerUtils.getDefaultReverseNumberList());

        myTickerView.setTypeface(TextTypeUtils.getLevelTypeFace(this));
        mTickerView1.setTypeface(TextTypeUtils.getLevelTypeFace(this));



        myTickerView.setText(myNum + "");
        mTickerView1.setText(myNum + "");

        myTickerView.setTextColor(this.getResources().getColor(R.color.ticker_inner_color));
        mTickerView1.setTextColor(this.getResources().getColor(R.color.ticker_inner_color));


        // 设置文字间的间距
        myTickerView.setPadding(8);

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


        myNum = myNum + addNum;
        myTickerView.setText(myNum + "");
        mTickerView1.setText(myNum + "");


        LogUtils.e("myTickerView getText  = " + myTickerView.getText());

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
