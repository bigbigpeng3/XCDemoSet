package com.pengzhangdemo.com.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.pengzhangdemo.com.myapplication.adapter.LiveMessagePageAdapter;

import java.util.ArrayList;
import java.util.List;

public class BottomSheetViewPageActivity extends AppCompatActivity implements View.OnClickListener {


    public static final String TAG = "BottomSheetViewPage";

    private BottomSheetBehavior mSettingBehavior;
    private BottomSheetDialog mBottomSheetDialog;

    Button button;
    View includeView;
//    TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_view_page);

        includeView = findViewById(R.id.sheet_message_list);

        button = (Button) findViewById(R.id.btn_toast);
        button.setOnClickListener(this);


        showMessageListBottom();

    }


    private int messageListPage = 0;
    private List<Fragment> messageListFrags;

    String[] messageTitles;

    /**
     * 展示 消息列表页
     */
    private void showMessageListBottom() {


        mSettingBehavior = BottomSheetBehavior.from(includeView);
        mSettingBehavior.setHideable(true);

        Log.e(TAG, "mSettingBehavior.getState() = " + mSettingBehavior.getState());
//        mSettingBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        if (messageListFrags == null) {
            messageListFrags = new ArrayList<>();
            messageListFrags.add(new MessageFirendsListFragment());
            messageListFrags.add(new MessageFirendsListFragment());
            messageListFrags.add(new MessageFirendsListFragment());
        }

        if (messageTitles == null) {
            messageTitles = new String[]{getResources().getString(R.string.live_message_friend), getResources().getString(R.string.live_message_stranger), getResources().getString(R.string.live_message_system)};
        }

//        View view = this.getLayoutInflater().inflate(R.layout.bottom_sheet_live_message_list, null);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_message);
        ViewPager viewPager = (ViewPager) findViewById(R.id.vp_message);

        viewPager.setOffscreenPageLimit(3);

        viewPager.setAdapter(new LiveMessagePageAdapter(getSupportFragmentManager(), messageListFrags, messageTitles));

        tabLayout.setupWithViewPager(viewPager);

        viewPager.setCurrentItem(0);

//        BottomSheetUtils.setupViewPager(viewPager);


//        ViewCompat.setNestedScrollingEnabled(viewPager,false);
//        ViewCompat.setNestedScrollingEnabled(tabLayout,false);

//        BottomSheetUtils.setupViewPager(viewPager);


//        mBottomSheetDialog = new BottomSheetDialog(this);
//
//        mBottomSheetDialog.setContentView(includeView);
//        mBottomSheetDialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet).setBackgroundColor(this.getApplicationContext().getResources().getColor(R.color.transparent));
//        mBottomSheetDialog.getWindow().setDimAmount(0);
//        mBottomSheetDialog.show();
//        mBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialog) {
//                mBottomSheetDialog = null;
//            }
//        });


    }

    @Override
    public void onClick(View v) {

        if (v == button) {

            Log.e(TAG, "button mSettingBehavior.getState() = " + mSettingBehavior.getState());

            if (mSettingBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                mSettingBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }else {
//                mSettingBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                mSettingBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        }
    }


}
