package com.pengzhangdemo.com.myapplication.bigbigpeng3.bottomsheet;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;

/**
 * Created by zp on 4/27/17.
 */

public class NoBarBottomSheetDialogFragment extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new NoShadowBottomSheetDialog(getContext(), getTheme());
    }

}

