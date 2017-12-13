/*
 * Copyright (C) 2017 Adam Huang <poisondog@gmail.com>
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

import android.support.design.widget.Snackbar;
import android.view.View;
import poisondog.core.Mission;

/**
 * @author Adam Huang
 * @since 2017-10-18
 */
public class ShowSnackbar implements Mission<ShowSnackbar.Parameter> {

	@Override
	public Void execute(ShowSnackbar.Parameter parameter) {
		Snackbar bar = Snackbar.make(parameter.mView, parameter.mMessage, parameter.mDuration);
		if (parameter.mActionString != null && parameter.mListener != null)
			bar.setAction(parameter.mActionString, parameter.mListener);
		View sbView = bar.getView();
		sbView.setBackgroundColor(parameter.mBackgroundColor);
		bar.show();
		return null;
	}

	public static class Parameter {
		private View mView;
		private String mMessage;
		private int mDuration;
		private String mActionString;
		private View.OnClickListener mListener;
		private int mBackgroundColor;
		/**
		 * Constructor
		 */
		public Parameter(View view, String message, int duration) {
			mView = view;
			mMessage = message;
			mDuration = duration;
		}
		public Parameter(View view, String message) {
			this(view, message, Snackbar.LENGTH_LONG);
		}
		public void setAction(String str, View.OnClickListener listener) {
			mActionString = str;
			mListener = listener;
		}
		public void setBackgroundColor(int color) {
			mBackgroundColor = color;
		}
	}
}
