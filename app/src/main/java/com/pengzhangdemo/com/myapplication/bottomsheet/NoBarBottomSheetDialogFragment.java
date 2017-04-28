package com.pengzhangdemo.com.myapplication.bottomsheet;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;

import com.pengzhangdemo.com.myapplication.R;

/**
 * Created by zp on 4/27/17.
 */

public class NoBarBottomSheetDialogFragment extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new NoShadowBottomSheetDialog(getContext(), getTheme());
    }

}

