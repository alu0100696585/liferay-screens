package com.liferay.mobile.screens.dlfile.display.pdf;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.liferay.mobile.screens.util.LiferayLogger;

/**
 * @author Sarai Díaz García
 */

public class OnSwipeTouchListener implements View.OnTouchListener {

	private final GestureDetector gestureDetector;
	private final SwipeListener swipeListener;

	public OnSwipeTouchListener(Context ctx, SwipeListener swipeListener) {
		gestureDetector = new GestureDetector(ctx, new GestureListener());
		this.swipeListener = swipeListener;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		return gestureDetector.onTouchEvent(event);
	}

	private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

		private static final int SWIPE_THRESHOLD = 100;
		private static final int SWIPE_VELOCITY = 100;

		@Override
		public boolean onDown(MotionEvent e) {
			return true;
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			boolean result = false;
			try {
				float diffY = e2.getY() - e1.getY();
				float diffX = e2.getX() - e1.getX();
				if (Math.abs(diffX) > Math.abs(diffY)) {
					if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY) {
						if (diffX > 0) {
							swipeListener.onSwipeRight();
						} else {
							swipeListener.onSwipeLeft();
						}
					}
					result = true;
				}
				result = true;
			} catch (Exception exception) {
				LiferayLogger.e("Error onFling " + exception);
			}
			return result;
		}
	}
}
