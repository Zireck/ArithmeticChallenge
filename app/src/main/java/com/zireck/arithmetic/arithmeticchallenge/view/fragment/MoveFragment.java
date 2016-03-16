package com.zireck.arithmetic.arithmeticchallenge.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.zireck.arithmetic.arithmeticchallenge.R;
import com.zireck.arithmetic.arithmeticchallenge.model.Move;

import butterknife.Bind;

public class MoveFragment extends BaseFragment {

    private Move mMove;
    @Bind(R.id.move)
    TextView mMoveText;

    public static MoveFragment newInstance(Move move) {
        MoveFragment fragment = new MoveFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("move", move);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getMove();
        mMoveText.setText(mMove.getText());
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_move;
    }

    private void getMove() {
        if (getArguments() != null && getArguments().getParcelable("move") != null) {
            mMove = getArguments().getParcelable("move");
        }
    }
}
