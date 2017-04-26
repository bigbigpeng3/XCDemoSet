package com.pengzhangdemo.com.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FragmentActivity extends AppCompatActivity implements View.OnClickListener {

    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        findViewById(R.id.btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                if (fragment == null) {
                    fragment = new BlankFragment();
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.chat_fragment_in, R.anim.chat_fragment_out);
                if (!fragment.isAdded()){
                    transaction.add(R.id.container,fragment);
                }
                if (fragment.isVisible()) {
                    transaction.hide(fragment);
                } else {
                    transaction.show(fragment);
                }
                transaction.commitAllowingStateLoss();
                break;
        }
    }
}
