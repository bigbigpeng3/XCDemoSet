package com.pengzhangdemo.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;

import com.pengzhangdemo.com.myapplication.app.BaseApplication;
import com.pengzhangdemo.com.myapplication.tag.PrepareTagAdapter;
import com.pengzhangdemo.com.myapplication.utils.FakeDataUtils;
import com.pengzhangdemo.com.myapplication.utils.TagColorUtils;
import com.pengzhangdemo.com.myapplication.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class TagColorActivity extends AppCompatActivity {


    RecyclerView rvPrepareTag;
    EditText etPrepareTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_color);


        rvPrepareTag = (RecyclerView) findViewById(R.id.rv_prepare_tag);
        etPrepareTitle = (EditText) findViewById(R.id.et_prepare_title);


        initTagRV();
    }



    private void initTagRV() {

        rvPrepareTag.setHasFixedSize(true);

        final List<String> mFinalData = new ArrayList<>();
        mFinalData.addAll(FakeDataUtils.getFakeTags());

        if (mFinalData.size() < 1) {
            return;
        }

        // 这里的布局需要由数据来决定由几列。
//        rvPrepareTag.setLayoutManager(new LinearLayoutManager();
        rvPrepareTag.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL));
        // 增加 左右的透明度 50为长度
//        rvPrepareTag.addItemDecoration(new EdgeItemAplhaDecoration(50, EdgeItemAplhaDecoration.HORIZONTAL));

        PrepareTagAdapter adapter = new PrepareTagAdapter(this, mFinalData);
        rvPrepareTag.setAdapter(adapter);

        adapter.setOnTagItemClickListener(new PrepareTagAdapter.TagItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {//将Tag内容添加到 对应的地方
                if (position == 0) {
                    return;
                }
                ToastUtils.makeText(BaseApplication.mAppContext, "点击了：" + mFinalData.get(position - 1) + "位置为：" + (position - 1));
                addEditText(mFinalData.get(position - 1), position);
            }
        });


    }



    SpannableStringBuilder spannableString = new SpannableStringBuilder();

    private void addEditText(String addStr, int position) {

        String currentStr = etPrepareTitle.getText().toString();
        String nextStr = currentStr + addStr;

        spannableString.append(addStr);

        ForegroundColorSpan colorSpan = new ForegroundColorSpan(TagColorUtils.switchTextColorStr(position));
        spannableString.setSpan(colorSpan, currentStr.length(), nextStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        //最后设置到EditText中，调整好位置
        etPrepareTitle.setText(spannableString);
        etPrepareTitle.setSelection(spannableString.length());

    }



}
