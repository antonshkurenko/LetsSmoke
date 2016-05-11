package io.github.tonyshkurenko.letssmoke;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class SmokeActivity extends AppCompatActivity
    implements SeekBar.OnSeekBarChangeListener {

  CigaretteMeasureView mCigarette;
  SeekBar mCigaretteSeekBar;
  SeekBar mPeopleSeekBar;

  TextView mCopyright;

  private static final int MIN_PEOPLE_COUNT = 2;

  private int mDefaultHeight;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_smoke);

    mCigarette = ((CigaretteMeasureView) findViewById(R.id.cigarette_head));
    mCigaretteSeekBar = ((SeekBar) findViewById(R.id.cigarette_size_seek_bar));
    mPeopleSeekBar = ((SeekBar) findViewById(R.id.people_count_seek_bar));

    mCopyright = ((TextView) findViewById(R.id.copyright));

    mCopyright.setMovementMethod(LinkMovementMethod.getInstance());

    mDefaultHeight = (int) (getResources().getDimension(R.dimen.default_cigarette_height));

    mPeopleSeekBar.setOnSeekBarChangeListener(this);
    mCigaretteSeekBar.setOnSeekBarChangeListener(this);

    mCigaretteSeekBar.setProgress((int) (mCigaretteSeekBar.getMax() * 0.75f));
  }

  //region Implements SeekBar.OnSeekBarChangeListener
  @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    switch (seekBar.getId()) {
      case R.id.cigarette_size_seek_bar:
        final ViewGroup.LayoutParams params = mCigarette.getLayoutParams();

        params.height =
            mDefaultHeight + (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, progress,
                getResources().getDisplayMetrics());

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
    // ignored
  }
  //endregion
}
