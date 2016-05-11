package io.github.tonyshkurenko.letssmoke;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SmokeActivity extends AppCompatActivity
    implements SeekBar.OnSeekBarChangeListener, NumberPicker.OnValueChangeListener {

  @BindView(R.id.cigarette_head) CigaretteMeasureView mCigarette;
  @BindView(R.id.cigarette_size_seek_bar) SeekBar mSeekBar;
  @BindView(R.id.people_count_picker) NumberPicker mPeopleCountPicker;

  private int mDefaultHeight;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_smoke);
    ButterKnife.bind(this);

    mDefaultHeight = (int) (getResources().getDimension(R.dimen.default_cigarette_height));

    mPeopleCountPicker.setMinValue(2);
    mPeopleCountPicker.setMaxValue(5);
    mPeopleCountPicker.setWrapSelectorWheel(true);
    mPeopleCountPicker.setOnValueChangedListener(this);

    mSeekBar.setOnSeekBarChangeListener(this);
  }

  //region Implements SeekBar.OnSeekBarChangeListener
  @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
    final ViewGroup.LayoutParams params = mCigarette.getLayoutParams();

    params.height =
        mDefaultHeight + (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, progress,
            getResources().getDisplayMetrics());

    mCigarette.setLayoutParams(params);
  }

  @Override public void onStartTrackingTouch(SeekBar seekBar) {
    // ignored
  }

  @Override public void onStopTrackingTouch(SeekBar seekBar) {
    // ignored
  }
  //endregion

  //region Implements NumberPicker.OnValueChangeListener
  @Override public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
    mCigarette.setPeopleCount(newVal);
    mCigarette.invalidate();
  }
  //endregion
}
