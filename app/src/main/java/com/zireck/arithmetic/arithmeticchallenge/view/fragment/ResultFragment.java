package com.zireck.arithmetic.arithmeticchallenge.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.zireck.arithmetic.arithmeticchallenge.R;

import butterknife.Bind;

public class ResultFragment extends BaseFragment {

    private static final String EXTRA_TEXT_COLOR = "text_color";

    @Bind(R.id.message) TextView mMessage;
    @Bind(R.id.value) TextView mResult;

    public static ResultFragment newInstance(int textColor) {
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_TEXT_COLOR, textColor);

        ResultFragment resultFragment = new ResultFragment();
        resultFragment.setArguments(bundle);

        return resultFragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMessage.setText("type your answer:");
        mResult.setText("-");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_result;
    }

    private void initialize() {
        if (getArguments() == null) {
            throw new IllegalArgumentException("InitialValueFragment has to be launched using a valid initial value as extra");
        }

        mMessage.setTextColor(getResources().getColor(getArguments().getInt(EXTRA_TEXT_COLOR)));
    }

    public void updateResult(String result) {
        if (result == null || result.length() <= 0) {
            mResult.setText("-");
        } else {
            mResult.setText(result);
        }
    }
}
