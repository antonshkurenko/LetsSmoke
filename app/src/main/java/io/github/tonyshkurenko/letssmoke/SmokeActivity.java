package io.github.tonyshkurenko.letssmoke;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class SmokeActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

  View mContainer;

  CigaretteMeasureView mCigarette;
  SeekBar mCigaretteSeekBar;
  SeekBar mPeopleSeekBar;

  TextView mCopyright;

  private static final String PREFS_SAVED_LENGTH = "prefs_saved_length";

  private static final int MIN_PEOPLE_COUNT = 2;

  private int mDefaultHeight;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_smoke);

    mContainer = findViewById(R.id.container);

    mCigarette = ((CigaretteMeasureView) findViewById(R.id.cigarette_head));
    mCigaretteSeekBar = ((SeekBar) findViewById(R.id.cigarette_size_seek_bar));
    mPeopleSeekBar = ((SeekBar) findViewById(R.id.people_count_seek_bar));

    mCopyright = ((TextView) findViewById(R.id.copyright));

    mCopyright.setMovementMethod(LinkMovementMethod.getInstance());

    mDefaultHeight = (int) (getResources().getDimension(R.dimen.default_cigarette_height));

    mPeopleSeekBar.setOnSeekBarChangeListener(this);
    mCigaretteSeekBar.setOnSeekBarChangeListener(this);

    mContainer.post(new Runnable() {
      @Override public void run() {

        final float topY = mContainer.getY() + getResources().getDimension(R.dimen.activity_margin);
        final float bottomY = mCigarette.getY();

        mCigaretteSeekBar.setMax((int) (bottomY - topY));

        final int length = PreferenceManager.getDefaultSharedPreferences(SmokeActivity.this)
            .getInt(PREFS_SAVED_LENGTH, 0);

        if (length == 0) {
          mCigaretteSeekBar.setProgress((int) (mCigaretteSeekBar.getMax() * 0.75f));
        } else {
          mCigaretteSeekBar.setProgress(length);
        }
      }
    });
  }

  //region Implements SeekBar.OnSeekBarChangeListener
  @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    switch (seekBar.getId()) {
      case R.id.cigarette_size_seek_bar:
        final ViewGroup.LayoutParams params = mCigarette.getLayoutParams();

        params.height = mDefaultHeight + progress;

        mCigarette.setLayoutParams(params);
        break;
      case R.id.people_count_seek_bar:
        mCigarette.setPeopleCount(MIN_PEOPLE_COUNT + progress);
        mCigarette.invalidate();
        break;
    }
  }

  @Override public void onStartTrackingTouch(SeekBar seekBar) {
    // ignored
  }

  @Override public void onStopTrackingTouch(SeekBar seekBar) {
    PreferenceManager.getDefaultSharedPreferences(this)
        .edit()
        .putInt(PREFS_SAVED_LENGTH, seekBar.getProgress())
        .apply();
  }
  //endregion
}
