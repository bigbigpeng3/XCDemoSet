package com.pengzhangdemo.com.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pengzhangdemo.com.myapplication.adapter.LiveMessagePageAdapter;

import java.util.ArrayList;
import java.util.List;

public class BottomSheetViewPageActivity extends AppCompatActivity {

    private BottomSheetBehavior mSettingBehavior;
    private BottomSheetDialog mBottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_view_page);

        showMessageListBottom();

    }


    private int messageListPage = 0;
    private List<Fragment> messageListFrags ;

    String[] messageTitles ;

    /**
     * 展示 消息列表页
     */
    private void showMessageListBottom() {


        if (messageListFrags == null){
            messageListFrags = new ArrayList<>();
            messageListFrags.add(new MessageFirendsListFragment());
            messageListFrags.add(new MessageFirendsListFragment());
            messageListFrags.add(new MessageFirendsListFragment());
        }

        if (messageTitles == null){
            messageTitles = new String[]{getResources().getString(R.string.live_message_friend), getResources().getString(R.string.live_message_stranger), getResources().getString(R.string.live_message_system)};
        }

        View view = this.getLayoutInflater().inflate(R.layout.bottom_sheet_live_message_list, null);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_message);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.vp_message);

        viewPager.setOffscreenPageLimit(2);

        viewPager.setAdapter(new LiveMessagePageAdapter(getSupportFragmentManager(),messageListFrags, messageTitles));

        tabLayout.setupWithViewPager(viewPager);

        viewPager.setCurrentItem(0);

        ViewCompat.setNestedScrollingEnabled(viewPager,false);

//        TabLayoutIndicatorUtils.setIndicator(this.mActivity, tabLayout, TabLayoutIndicatorUtils.LIVE_MESSAGE_TAB_MARGIN_DIP, TabLayoutIndicatorUtils.LIVE_MESSAGE_TAB_MARGIN_DIP);

        mBottomSheetDialog = new BottomSheetDialog(this);
        mBottomSheetDialog.setContentView(view);
        mBottomSheetDialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet).setBackgroundColor(this.getApplicationContext().getResources().getColor(R.color.transparent));
        mBottomSheetDialog.getWindow().setDimAmount(0);
        mBottomSheetDialog.show();
        mBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mBottomSheetDialog = null;
            }
        });


    }

}
