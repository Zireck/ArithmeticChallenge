package com.zireck.arithmetic.arithmeticchallenge.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zireck.arithmetic.arithmeticchallenge.R;
import com.zireck.arithmetic.arithmeticchallenge.model.Move;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MoveFragment extends Fragment {

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_move, container, false);
        ButterKnife.bind(this, view);

        getMove();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMoveText.setText(mMove.getText());
    }

    private void getMove() {
        if (getArguments() != null && getArguments().getParcelable("move") != null) {
            mMove = getArguments().getParcelable("move");
        }
    }
}
