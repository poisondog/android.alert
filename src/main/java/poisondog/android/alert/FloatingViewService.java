/*
 * Copyright (C) 2018 Adam Huang <poisondog@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package poisondog.android.alert;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * @author Adam Huang
 * @since 2018-06-27
 */
public class FloatingViewService extends Service {
	private WindowManager mWindowManager;
	private View mFloatingView;
	private WindowManager.LayoutParams mParams;

	@Override
	public void onCreate() {
		super.onCreate();
		mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
		final ImageView image = new ImageView(this);
		image.setImageResource(R.drawable.ic_launcher);

		mParams = new WindowManager.LayoutParams(
				WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.TYPE_PHONE,
				WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
				PixelFormat.TRANSLUCENT);
//		mParams.gravity = Gravity.RIGHT;
		mParams.gravity = Gravity.TOP | Gravity.LEFT;

		image.setOnTouchListener(new MoveListener());
		setFloatingView(image);

	}

	class MoveListener implements View.OnTouchListener {
		private int initialX;
		private int initialY;
		private float initialTouchX;
		private float initialTouchY;
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			WindowManager.LayoutParams lParams = (WindowManager.LayoutParams) v.getLayoutParams();
			switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
//					System.out.println("ACTION_DOWN");
//					System.out.println(lParams.x + ", " + lParams.y);
					initialX = lParams.x;
					initialY = lParams.y;
					initialTouchX = event.getRawX();
					initialTouchY = event.getRawY();
					return true;
				case MotionEvent.ACTION_UP:
//					System.out.println("ACTION_UP");
					return true;
				case MotionEvent.ACTION_MOVE:
//					System.out.println("ACTION_MOVE: ");
//					System.out.println(event.getRawX() + ", " + event.getRawY());
					lParams.x = initialX + (int) (event.getRawX() - initialTouchX);
					lParams.y = initialY + (int) (event.getRawY() - initialTouchY);
					mWindowManager.updateViewLayout(v, lParams);
					return true;
			}
			return false;
		}
	}

	public void setFloatingView(View view) {
		if (mFloatingView != null)
			mWindowManager.removeView(mFloatingView);
		mFloatingView = view;
		mWindowManager.addView(mFloatingView, mParams);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		//Not use this method
		return null;
	}

	@Override
	public void onDestroy() {
		Toast.makeText(getApplicationContext(), "onDestroy", Toast.LENGTH_SHORT);
		super.onDestroy();
		mWindowManager.removeView(mFloatingView);
	}
}
