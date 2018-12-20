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

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import poisondog.core.Mission;

/**
 * @author Adam Huang
 * @since 2018-11-12
 */
public class SnackbarMission implements Mission<String> {
	private View mView;
	private int mDuration;
	private int mBackgroundColor;
	private int mActionColor;
	private String mActionString;
	private View.OnClickListener mListener;
	private boolean mOnTop;

	/**
	 * Constructor
	 */
	public SnackbarMission(View view) {
		mView = view;
		mDuration = Snackbar.LENGTH_LONG;
	}

	public void setAction(String str, View.OnClickListener listener) {
		mActionString = str;
		mListener = listener;
	}

	public void setDuration(int duration) {
		mDuration = duration;
	}

	public void setBackgroundColor(int color) {
		mBackgroundColor = color;
	}

	public void setActionTextColor(int color) {
		mActionColor = color;
	}

	public void setOnTop(boolean flag) {
		mOnTop = flag;
	}

	@Override
	public Void execute(String message) {
		Snackbar bar = Snackbar.make(mView, message, mDuration);
		if (mActionString != null && mListener != null)
			bar.setAction(mActionString, mListener);
		bar.setActionTextColor(mActionColor);
		View sbView = bar.getView();
		sbView.setBackgroundColor(mBackgroundColor);
		if (mOnTop) {
			FrameLayout.LayoutParams params =(FrameLayout.LayoutParams)sbView.getLayoutParams();
			params.gravity = Gravity.TOP;
			sbView.setLayoutParams(params);
		}
		bar.show();
		return null;
	}

	public static void red(View view, String message) {
		SnackbarMission mission = new SnackbarMission(view);
		mission.setBackgroundColor(Color.RED);
		mission.execute(message);
	}

	public static void green(View view, String message) {
		SnackbarMission mission = new SnackbarMission(view);
		mission.setBackgroundColor(Color.GREEN);
		mission.execute(message);
	}

}
