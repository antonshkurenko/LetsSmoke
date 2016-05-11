package io.github.tonyshkurenko.letssmoke;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SmokeActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

  @BindView(R.id.cigarette_head) View mCigarette;
  @BindView(R.id.seek_bar) SeekBar mSeekBar;

  private int mDefaultHeight;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_smoke);
    ButterKnife.bind(this);

    mDefaultHeight = (int) (getResources().getDimension(R.dimen.default_cigarette_height));

    mSeekBar.setOnSeekBarChangeListener(this);
  }

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
}
