package com.zireck.arithmetic.arithmeticchallenge.view.fragment;

import android.widget.TextView;

import com.zireck.arithmetic.arithmeticchallenge.R;

import butterknife.Bind;

public class ResultFragment extends BaseFragment {

    @Bind(R.id.result) TextView mResult;

    public static ResultFragment newInstance() {
        return new ResultFragment();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_result;
    }

    public void updateResult(String result) {
        if (result == null || result.length() <= 0) {
            mResult.setText("-");
        } else {
            mResult.setText(result);
        }
    }
}
