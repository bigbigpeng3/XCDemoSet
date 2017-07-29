package com.pengzhangdemo.com.myapplication.bigbigpeng3.activity;

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

import com.pengzhangdemo.com.myapplication.R;
import com.pengzhangdemo.com.myapplication.bigbigpeng3.adapter.LiveMessagePageAdapter;
import com.pengzhangdemo.com.myapplication.bigbigpeng3.fragment.MessageFirendsListFragment;
import com.pengzhangdemo.com.myapplication.bigbigpeng3.fragment.ViewPageDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPageInBottomSheetDialogActivity extends AppCompatActivity implements View.OnClickListener {


    public static final String TAG = "BottomSheetViewPage";

    private BottomSheetBehavior mSettingBehavior;
    private BottomSheetDialog mBottomSheetDialog;

    Button button;
    View includeView;
//    TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__view_page_in_bottom_sheet);

        includeView = findViewById(R.id.sheet_message_list);

        button = (Button) findViewById(R.id.btn_toast);
        button.setOnClickListener(this);


//        showMessageListBottom();

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

        TabLayout tabLayout = (TabLayout) includeView.findViewById(R.id.tab_message);
        ViewPager viewPager = (ViewPager) includeView.findViewById(R.id.vp_message);

        viewPager.setOffscreenPageLimit(3);

        viewPager.setAdapter(new LiveMessagePageAdapter(getSupportFragmentManager(), messageListFrags, messageTitles));

        tabLayout.setupWithViewPager(viewPager);

        viewPager.setCurrentItem(0);

    }

    @Override
    public void onClick(View v) {

        if (v == button) {

//            Log.e(TAG, "button mSettingBehavior.getState() = " + mSettingBehavior.getState());

//            if (mSettingBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
//                mSettingBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//            }else {
////                mSettingBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
//                mSettingBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//            }

            showBottomSheetFragment();

        }
    }

    private void showBottomSheetFragment() {
        final ViewPageDialogFragment dialogFragment = new ViewPageDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), dialogFragment.getTag());
//        dialogFragment.setOnDismissListener(new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialog) {
//
//            }
//        });
    }


}
