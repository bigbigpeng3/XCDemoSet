package com.pengzhangdemo.com.myapplication.bigbigpeng3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.pengzhangdemo.com.myapplication.R;
import com.pengzhangdemo.com.myapplication.app.BaseApplication;
import com.pengzhangdemo.com.myapplication.bigbigpeng3.tag.PrepareTagAdapter;
import com.pengzhangdemo.com.myapplication.utils.FakeDataUtils;
import com.pengzhangdemo.com.myapplication.utils.TagColorUtils;
import com.pengzhangdemo.com.myapplication.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class TagColorActivity extends AppCompatActivity {


    RecyclerView rvPrepareTag;
    EditText etPrepareTitle;
    TextView tvText;

    private MaxLengthWatcher maxLengthWatcher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_color);


        rvPrepareTag = (RecyclerView) findViewById(R.id.rv_prepare_tag);
        etPrepareTitle = (EditText) findViewById(R.id.et_prepare_title);
        tvText = (TextView) findViewById(R.id.tv_text);

        maxLengthWatcher = new MaxLengthWatcher(etPrepareTitle);


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
//                addEditText(mFinalData.get(position - 1), position);
                maxLengthWatcher.insert(mFinalData.get(position - 1), position);
            }
        });


    }


    /**
     * 第一次尝试无缝添加spannable String。但是删除时，效果并不是很好。
     *
     * @param addStr
     * @param position
     */
    private void addEditText(String addStr, int position) {


        Editable currentStr = etPrepareTitle.getText();

        // 可以直接获取到Editable，那么etPrepareTitle.getText()总是最新的。所以我不需要一个全局保存一个SpannableStringBuilder对象了。
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        // 首先添加目前的情况。
        spannableString.append(currentStr);

        // 添加有颜色的字符
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(TagColorUtils.switchTextColorStr(position));
        SpannableString tempStr = new SpannableString(addStr);
        tempStr.setSpan(colorSpan, 0, addStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.append(tempStr);

        //最后设置到EditText中，调整好位置
        etPrepareTitle.setText(spannableString);
        etPrepareTitle.setSelection(spannableString.length());

        // Test color of etPrepareTitle.getText()
        tvText.setText(etPrepareTitle.getText());

    }

    /**
     * 监听输入内容是否超出最大长度，并设置光标位置
     */
    public class MaxLengthWatcher implements TextWatcher {

        private final EditText mEditor;
        private final ArrayList<ForegroundColorSpan> mEmoticonsToRemove = new ArrayList<ForegroundColorSpan>();

        public MaxLengthWatcher(EditText editText) {
//            this.maxLen = maxLen;
            this.mEditor = editText;
            this.mEditor.addTextChangedListener(this);
        }


        public void insert(String str, int position) {
            // Create the ImageSpan
//            Drawable drawable = mEditor.getResources().getDrawable(resource);
//            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//            ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);

            ForegroundColorSpan colorSpan = new ForegroundColorSpan(TagColorUtils.switchTextColorStr(position));
            SpannableString tempStr = new SpannableString(str);
            tempStr.setSpan(colorSpan, 0, str.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

//            spannableString.append(tempStr);

            // Get the selected text.
            int start = mEditor.getSelectionStart();
            int end = mEditor.getSelectionEnd();
            Editable message = mEditor.getEditableText();

            // Insert the emoticon.
            message.replace(start, end, str);
            message.setSpan(colorSpan, start, start + str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }


        @Override
        public void beforeTextChanged(CharSequence text, int start, int count, int after) {

            // Check if some text will be removed.
            if (count > 0) {
                int end = start + count;
                Editable message = mEditor.getEditableText();
                ForegroundColorSpan[] list = message.getSpans(start, end, ForegroundColorSpan.class);

                for (ForegroundColorSpan span : list) {
                    // Get only the emoticons that are inside of the changed
                    // region.
                    int spanStart = message.getSpanStart(span);
                    int spanEnd = message.getSpanEnd(span);
                    if ((spanStart < end) && (spanEnd > start)) {
                        // Add to remove list
                        mEmoticonsToRemove.add(span);
                    }
                }
            }


        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {


        }

        @Override
        public void afterTextChanged(Editable text) {

            Editable message = mEditor.getEditableText();

            // Commit the emoticons to be removed.
            for (ForegroundColorSpan span : mEmoticonsToRemove) {
                int start = message.getSpanStart(span);
                int end = message.getSpanEnd(span);

                // Remove the span
                message.removeSpan(span);

                // Remove the remaining emoticon text.
                if (start != end) {
                    message.delete(start, end);
                }
            }
            mEmoticonsToRemove.clear();

        }


    }


}
