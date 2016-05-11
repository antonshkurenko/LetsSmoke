package io.github.tonyshkurenko.letssmoke;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by: Anton Shkurenko (anthonyshkurenko)
 * Project: LetsSmoke
 * Date: 5/11/16
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
public class CigaretteMeasureView extends View {

  private final static Paint PAINT = new Paint();

  static {
    PAINT.setStrokeWidth(8); // 8px
    PAINT.setColor(Color.RED);
  }

  private int mPeopleCount = 2;

  public CigaretteMeasureView(Context context) {
    super(context);
  }

  public CigaretteMeasureView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public CigaretteMeasureView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public CigaretteMeasureView(Context context, AttributeSet attrs, int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  //region Getters and setters
  public void setPeopleCount(int peopleCount) {
    mPeopleCount = peopleCount;
  }
  //endregion

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    final int height = getHeight();
    final int width = getWidth();
    final int sectionHeight = height / mPeopleCount;


    for (int i = 1; i < mPeopleCount; i++) {
      final int y = i * sectionHeight;
      canvas.drawLine(0, y, width, y, PAINT);
    }
  }
}
