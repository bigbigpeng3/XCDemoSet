package com.pengzhangdemo.com.myapplication.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.pengzhangdemo.com.myapplication.R;
import com.pengzhangdemo.com.myapplication.adapter.LiveMessagePageAdapter;
import com.pengzhangdemo.com.myapplication.bottomsheet.NoBarBottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPageDialogFragment extends NoBarBottomSheetDialogFragment {

    private List<Fragment> messageListFrags;

    String[] messageTitles;

    ViewPager bottomSheetViewPager;

    TabLayout bottomSheetTabLayout;

    BottomSheetDialog dialog;

    private View view;

//    @Override
//    public void setupDialog(Dialog dialog, int style) {
//        super.setupDialog(dialog, style);
//        dialog.setContentView(view);
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = View.inflate(getContext(), R.layout.bottom_sheet_live_message_list, null);

        bottomSheetTabLayout = (TabLayout) view.findViewById(R.id.tab_message);

        bottomSheetViewPager = (ViewPager) view.findViewById(R.id.vp_message);


        setupViewPager();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        dialog = (BottomSheetDialog) getDialog();
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        // 这两种 不影响systembar的方式不行。最后使用了 自定义的BottomSheetDialog wrap_content方式

//        dialog.getWindow().findViewById(android.support.design.R.id.design_bottom_sheet).setBackgroundColor(view.getContext().getApplicationContext().getResources().getColor(R.color.transparent));
//        dialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet).setBackgroundColor(view.getContext().getApplicationContext().getResources().getColor(R.color.transparent));

        dialog.getWindow().setDimAmount(0);
        super.onViewCreated(view, savedInstanceState);
    }


    private void setupViewPager() {
        if (messageListFrags == null) {
            messageListFrags = new ArrayList<>();
            messageListFrags.add(new MessageFirendsListFragment());
            messageListFrags.add(new MessageFirendsListFragment());
            messageListFrags.add(new MessageFirendsListFragment());
        }

        if (messageTitles == null) {
            messageTitles = new String[]{getResources().getString(R.string.live_message_friend), getResources().getString(R.string.live_message_stranger), getResources().getString(R.string.live_message_system)};
        }

        bottomSheetViewPager.setAdapter(new LiveMessagePageAdapter(getChildFragmentManager(), messageListFrags, messageTitles));
//
//        bottomSheetViewPager.setAdapter(new SimplePagerAdapter(getContext()));
        bottomSheetViewPager.setOffscreenPageLimit(3);
        bottomSheetViewPager.setCurrentItem(0);
        bottomSheetTabLayout.setupWithViewPager(bottomSheetViewPager);
//        BottomSheetUtils.setupViewPager(bottomSheetViewPager);
    }


    /**
     * 允许设置 dialog消失 监听器
     *
     * @param listener
     */
    public void setOnDismissListener(DialogInterface.OnDismissListener listener) {
        dialog.setOnDismissListener(listener);
    }


    /**
     * 会造成空指针异常。
     */
    public void setTransparentBackground(){
        dialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet).setBackgroundColor(view.getContext().getApplicationContext().getResources().getColor(R.color.transparent));
    }


}
