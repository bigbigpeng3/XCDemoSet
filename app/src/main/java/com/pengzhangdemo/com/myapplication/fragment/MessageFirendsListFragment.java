package com.pengzhangdemo.com.myapplication.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MessageFirendsListFragment extends Fragment implements View.OnClickListener {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(com.pengzhangdemo.com.myapplication.R.layout.fragment_live_message_friends, container, false);
        }


        initView();

        return view;
    }

    private void initView() {

    }

    @Override
    public void onClick(View v) {



    }



}
