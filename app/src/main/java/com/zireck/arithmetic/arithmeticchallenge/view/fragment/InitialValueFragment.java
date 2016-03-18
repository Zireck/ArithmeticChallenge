package com.zireck.arithmetic.arithmeticchallenge.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.zireck.arithmetic.arithmeticchallenge.R;

import butterknife.Bind;

public class InitialValueFragment extends BaseFragment {

    private static final String EXTRA_INITIAL_VALUE = "initial_value";
    private static final String EXTRA_TEXT_COLOR = "text_color";

    @Bind(R.id.message) TextView mMessage;
    @Bind(R.id.value) TextView mValue;

    private int mInitialValue;

    public static InitialValueFragment newInstance(int initialValue, int textColor) {
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_INITIAL_VALUE, initialValue);
        bundle.putInt(EXTRA_TEXT_COLOR, textColor);

        InitialValueFragment initialValueFragment = new InitialValueFragment();
        initialValueFragment.setArguments(bundle);

        return initialValueFragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMessage.setText("initial value");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
    }

    private void initialize() {
        if (getArguments() == null) {
            throw new IllegalArgumentException("InitialValueFragment has to be launched using a valid initial value as extra");
        }

        mMessage.setTextColor(getResources().getColor(getArguments().getInt(EXTRA_TEXT_COLOR)));

        mInitialValue = getArguments().getInt(EXTRA_INITIAL_VALUE);
        mValue.setText(String.valueOf(mInitialValue));
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_result;
    }
}
