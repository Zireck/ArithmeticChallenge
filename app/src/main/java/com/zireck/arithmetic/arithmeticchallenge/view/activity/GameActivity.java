package com.zireck.arithmetic.arithmeticchallenge.view.activity;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viewpagerindicator.CirclePageIndicator;
import com.zireck.arithmetic.arithmeticchallenge.R;
import com.zireck.arithmetic.arithmeticchallenge.model.Challenge;
import com.zireck.arithmetic.arithmeticchallenge.model.enums.BottomSheetInput;
import com.zireck.arithmetic.arithmeticchallenge.model.enums.Difficulty;
import com.zireck.arithmetic.arithmeticchallenge.presenter.GamePresenter;
import com.zireck.arithmetic.arithmeticchallenge.view.GameView;
import com.zireck.arithmetic.arithmeticchallenge.view.adapter.MovesPagerAdapter;
import com.zireck.arithmetic.arithmeticchallenge.view.fragment.ResultFragment;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameActivity extends AppCompatActivity implements GameView {

    private GamePresenter mPresenter;

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.press_start)
    TextView mPressStart;
    @Bind(R.id.viewpager_moves)
    ViewPager mViewPagerMoves;
    @Bind(R.id.indicator)
    CirclePageIndicator mIndicator;
    @Bind(R.id.bottom_sheet)
    LinearLayout mBottomSheet;

    @Bind(R.id.fab) FloatingActionButton mFloatingActionButton;
    private BottomSheetBehavior mBottomSheetBehavior;

    private Challenge mChallenge;
    private MovesPagerAdapter mMovesPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        mBottomSheetBehavior = BottomSheetBehavior.from(mBottomSheet);
        mBottomSheetBehavior.setHideable(false);
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                /*if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    mViewPagerMoves.setCurrentItem(mViewPagerMoves.getCurrentItem()-1);
                }*/

                if (newState == BottomSheetBehavior.STATE_COLLAPSED && mViewPagerMoves.getCurrentItem() == mViewPagerMoves.getAdapter().getCount() - 1) {
                    mViewPagerMoves.setCurrentItem(mViewPagerMoves.getAdapter().getCount() - 2);
                    mPresenter.pageSelected(mViewPagerMoves.getAdapter().getCount() - 2);
                } else if (newState == BottomSheetBehavior.STATE_DRAGGING && mViewPagerMoves.getCurrentItem() < mViewPagerMoves.getAdapter().getCount() - 1) {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                } else if (newState == BottomSheetBehavior.STATE_DRAGGING && mViewPagerMoves.getCurrentItem() == mViewPagerMoves.getAdapter().getCount() - 1) {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset) {

            }
        });

        // TODO: set difficulty
        mPresenter = new GamePresenter(Difficulty.EASY);
        mPresenter.setView(this);

        mViewPagerMoves.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mPresenter.pageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick(R.id.fab)
    public void onClickFab() {
        mPresenter.onClickFab();
    }

    @OnClick({ R.id.one, R.id.two, R.id.three, R.id.four, R.id.five,
            R.id.six, R.id.seven, R.id.eight, R.id.nine, R.id.zero,
            R.id.backspace, R.id.collapse})
    public void onClickBottomSheet(View view) {
        BottomSheetInput bottomSheetInput;
        if (view.getId() == R.id.backspace) {
            //Toast.makeText(this, "Backspace", Toast.LENGTH_SHORT).show();
            mPresenter.bottomSheetInput(BottomSheetInput.BACKSPACE);
        } else if (view.getId() == R.id.collapse) {
            mPresenter.bottomSheetInput(BottomSheetInput.COLLAPSE);
        } else {
            mPresenter.bottomSheetInput(((AppCompatButton) view).getText().toString());
        }
    }

    @OnClick({ R.id.previous, R.id.next})
    public void onClickNavigation(View view) {
        int n;
        if (view.getId() == R.id.previous) {
            n = mViewPagerMoves.getCurrentItem() <= 0 ? 0 : mViewPagerMoves.getCurrentItem() - 1;
            mViewPagerMoves.setCurrentItem(n);
        } else {
            n = mViewPagerMoves.getCurrentItem() >= mViewPagerMoves.getAdapter().getCount()-1 ? mViewPagerMoves.getAdapter().getCount()-1 : mViewPagerMoves.getCurrentItem() + 1;
            mViewPagerMoves.setCurrentItem(n);
        }

        mPresenter.pageSelected(n);
    }

    @Override
    public void initViewPager(List<Fragment> fragments) {
        mPressStart.setVisibility(View.VISIBLE);

        mViewPagerMoves.setEnabled(false);
        mViewPagerMoves.setVisibility(View.INVISIBLE);

        mMovesPagerAdapter = new MovesPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPagerMoves.setAdapter(mMovesPagerAdapter);
        mIndicator.setViewPager(mViewPagerMoves);
    }

    @Override
    public void startGame() {
        mFloatingActionButton.setImageResource(android.R.drawable.ic_media_pause);

        mPressStart.setVisibility(View.INVISIBLE);

        mViewPagerMoves.setCurrentItem(0);
        mViewPagerMoves.setEnabled(true);
        mViewPagerMoves.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopGame() {
        mFloatingActionButton.setImageResource(android.R.drawable.ic_media_play);


        mViewPagerMoves.setEnabled(false);
        mViewPagerMoves.setVisibility(View.INVISIBLE);
        mViewPagerMoves.setCurrentItem(0);

        mPressStart.setVisibility(View.VISIBLE);
    }

    @Override
    public void startInput() {
        mFloatingActionButton.hide();
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    @Override
    public void stopInput() {
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        mFloatingActionButton.show();
    }

    @Override
    public void setPage(int page) {
        mViewPagerMoves.setCurrentItem(page);
    }

    @Override
    public void setResult(String result) {
        //Fragment fragment = mMovesPagerAdapter.getItem(mViewPagerMoves.getAdapter().getCount() - 1);
        //if (fragment != null && fragment instanceof )

        Fragment currentFragment = mMovesPagerAdapter.getItem(mViewPagerMoves.getCurrentItem());
        if (currentFragment instanceof ResultFragment) {
            ResultFragment resultFragment = (ResultFragment) currentFragment;
            resultFragment.updateResult(result);
        }
    }

    @Override
    public void notify(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show();
    }
}
