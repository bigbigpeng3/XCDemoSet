package com.pengzhangdemo.com.myapplication;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pengzhangdemo.com.myapplication.adapter.LiveMessagePageAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    public BlankFragment() {
        // Required empty public constructor
    }


    private List<Fragment> messageListFrags;

    String[] messageTitles;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.bottom_sheet_live_message_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (messageListFrags == null) {
            messageListFrags = new ArrayList<>();
            messageListFrags.add(new MessageFirendsListFragment());
            messageListFrags.add(new MessageFirendsListFragment());
            messageListFrags.add(new MessageFirendsListFragment());
        }

        if (messageTitles == null) {
            messageTitles = new String[]{getResources().getString(R.string.live_message_friend), getResources().getString(R.string.live_message_stranger), getResources().getString(R.string.live_message_system)};
        }
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_message);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.vp_message);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(new LiveMessagePageAdapter(getChildFragmentManager(), messageListFrags, messageTitles));
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(0);
    }


}
