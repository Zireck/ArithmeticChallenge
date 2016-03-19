package com.zireck.arithmetic.arithmeticchallenge.view.activity;

import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.zireck.arithmetic.arithmeticchallenge.R;
import com.zireck.arithmetic.arithmeticchallenge.model.Challenge;
import com.zireck.arithmetic.arithmeticchallenge.model.enums.BottomSheetInput;
import com.zireck.arithmetic.arithmeticchallenge.model.enums.Difficulty;
import com.zireck.arithmetic.arithmeticchallenge.presenter.GamePresenter;
import com.zireck.arithmetic.arithmeticchallenge.view.GameView;
import com.zireck.arithmetic.arithmeticchallenge.view.adapter.MovesPagerAdapter;
import com.zireck.arithmetic.arithmeticchallenge.view.custom.NonSwipeableCirclePageIndicator;
import com.zireck.arithmetic.arithmeticchallenge.view.custom.NonSwipeableViewPager;
import com.zireck.arithmetic.arithmeticchallenge.view.fragment.ResultFragment;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameActivity extends AppCompatActivity implements GameView {

    private GamePresenter mPresenter;

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.time_left) TextView mTimeLeft;
    @Bind(R.id.circular_progress_bar) CircularProgressBar mCircularProgressBar;
    @Bind(R.id.press_start)
    TextView mPressStart;
    @Bind(R.id.viewpager_moves)
    NonSwipeableViewPager mViewPagerMoves;
    @Bind(R.id.indicator)
    NonSwipeableCirclePageIndicator mIndicator;
    @Bind(R.id.previous)
    ImageView mButtonPrevious;
    @Bind(R.id.next) ImageView mButtonNext;
    @Bind(R.id.bottom_sheet)
    LinearLayout mBottomSheet;
    @Bind(R.id.win_fail) TextView mWinFail;

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mBottomSheetBehavior = BottomSheetBehavior.from(mBottomSheet);
        //mBottomSheetBehavior.setHideable(false);
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                System.out.println("k9d3 new State: " + newState);
                if (newState == BottomSheetBehavior.STATE_COLLAPSED && mViewPagerMoves.getCurrentItem() == mViewPagerMoves.getAdapter().getCount() - 1) {
                    if (!mPresenter.isGameOver()) {
                        mViewPagerMoves.setCurrentItem(mViewPagerMoves.getAdapter().getCount() - 2);
                        mPresenter.pageSelected(mViewPagerMoves.getAdapter().getCount() - 2);
                    }
                } else if (newState == BottomSheetBehavior.STATE_DRAGGING && mViewPagerMoves.getCurrentItem() < mViewPagerMoves.getAdapter().getCount() - 1) {
                    //mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    stopInput();
                } else if (newState == BottomSheetBehavior.STATE_DRAGGING && mViewPagerMoves.getCurrentItem() == mViewPagerMoves.getAdapter().getCount() - 1) {
                    if (mPresenter.isGameOver()) {
                        //mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        stopInput();
                    } else {
                        //mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        startInput();
                    }
                }
            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset) {
                System.out.println("k9d3 slides " + slideOffset);
            }
        });

        mCircularProgressBar.setProgress(100);
        mButtonPrevious.setEnabled(false);
        mButtonNext.setEnabled(false);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_game, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_difficulty_easy:
                mPresenter.setDifficulty(Difficulty.EASY);
                break;
            case R.id.action_difficulty_medium:
                mPresenter.setDifficulty(Difficulty.MEDIUM);
                break;
            case R.id.action_difficulty_hard:
                mPresenter.setDifficulty(Difficulty.HARD);
                break;
        }

        return super.onOptionsItemSelected(item);
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

        mViewPagerMoves.setEnabledSwipe(false);
        mViewPagerMoves.setVisibility(View.INVISIBLE);
        mIndicator.setEnabledSwipe(false);

        mMovesPagerAdapter = new MovesPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPagerMoves.setAdapter(mMovesPagerAdapter);
        mIndicator.setViewPager(mViewPagerMoves);

        mFloatingActionButton.show();
    }

    @Override
    public void startGame() {
        mWinFail.setVisibility(View.INVISIBLE);

        mFloatingActionButton.setImageResource(android.R.drawable.ic_media_pause);
        mFloatingActionButton.show();

        mPressStart.setVisibility(View.INVISIBLE);

        mViewPagerMoves.setCurrentItem(0);
        mViewPagerMoves.setEnabledSwipe(true);
        mViewPagerMoves.setVisibility(View.VISIBLE);
        mIndicator.setEnabledSwipe(true);

        mCircularProgressBar.setProgress(100);

        mButtonPrevious.setEnabled(true);
        mButtonNext.setEnabled(true);
    }

    @Override
    public void stopGame() {
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        mButtonPrevious.setEnabled(false);
        mButtonNext.setEnabled(false);

        mFloatingActionButton.setImageResource(android.R.drawable.ic_popup_sync);
        mFloatingActionButton.show();

        mViewPagerMoves.setVisibility(View.VISIBLE);
        mViewPagerMoves.setEnabledSwipe(false);
        mIndicator.setEnabledSwipe(false);
    }

    @Override
    public void readyGame() {
        mButtonPrevious.setEnabled(false);
        mButtonNext.setEnabled(false);

        mFloatingActionButton.setImageResource(android.R.drawable.ic_media_play);
        mFloatingActionButton.show();

        mViewPagerMoves.setCurrentItem(0);
        mViewPagerMoves.setEnabledSwipe(false);
        mViewPagerMoves.setVisibility(View.INVISIBLE);
        mIndicator.setEnabledSwipe(false);

        mWinFail.setVisibility(View.INVISIBLE);
        mPressStart.setVisibility(View.VISIBLE);

        mTimeLeft.setText("30.0s");
        mCircularProgressBar.setProgress(100);
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
        //Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show();
        mWinFail.setVisibility(View.VISIBLE);
        mWinFail.setText(message);
    }

    @Override
    public void updateTimeLeft(String timeLeft) {
        mTimeLeft.setText(timeLeft);
    }

    @Override
    public void updateProgress(float progress) {
        mCircularProgressBar.setProgress(progress);
        //mCircularProgressBar.setProgressWithAnimation(progress, 500);
    }

    @Override
    public void setColor(Difficulty difficulty) {

        int color = difficulty.getColor();
        mCircularProgressBar.setColor(getResources().getColor(color));
        mIndicator.setFillColor(getResources().getColor(color));
        mIndicator.setStrokeColor(getResources().getColor(color));
        mFloatingActionButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(color)));
        mBottomSheet.setBackgroundColor(getResources().getColor(color));
        mWinFail.setTextColor(getResources().getColor(color));

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int colorDarker = R.color.grey_3;
            switch (difficulty) {
                case EASY:
                    colorDarker = R.color.easy_darker;
                    break;
                case MEDIUM:
                    colorDarker = R.color.medium_darker;
                    break;
                case HARD:
                    colorDarker = R.color.hard_darker;
                    break;
            }

            getWindow().setStatusBarColor(ContextCompat.getColor(this, colorDarker));
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, colorDarker));
        }
    }
}
