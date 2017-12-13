/*
 * Copyright (C) 2014 Adam Huang <poisondog@gmail.com>
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

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

/**
 * @author poisondog <poisondog@gmail.com>
 */
public class DialogParameter {
	private Context mContext;
	private String mTitle;
	private String mMessage;
	private String mPositiveText;
	private String mNegativeText;
	private String mNeutralText;
	private View mView;
	private DialogInterface.OnClickListener mPositiveListener;
	private DialogInterface.OnClickListener mNegativeListener;
	private DialogInterface.OnClickListener mNeutralListener;

	public DialogParameter(Context context) {
		mContext = context;
		mTitle = "";
		mMessage = "";
		mPositiveText = "";
		mNegativeText = "";
		mNeutralText = "";
		mPositiveListener = new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface di, int whichButton) {
			}
		};
		mNegativeListener = mPositiveListener;
		mNeutralListener = mPositiveListener;
	}

	public Context getContext() {
		return mContext;
	}

	public String getTitle() {
		return mTitle;
	}

	public String getMessage() {
		return mMessage;
	}

	public String getPositiveText() {
		return mPositiveText;
	}

	public String getNegativeText() {
		return mNegativeText;
	}

	public String getNeutralText() {
		return mNeutralText;
	}

	public View getView() {
		return mView;
	}

	public DialogInterface.OnClickListener getPositiveListener() {
		return mPositiveListener;
	}

	public DialogInterface.OnClickListener getNegativeListener() {
		return mNegativeListener;
	}

	public DialogInterface.OnClickListener getNeutralListener() {
		return mNeutralListener;
	}

	public void setTitle(String title) {
		mTitle = title;
	}

	public void setMessage(String message) {
		mMessage = message;
	}

	public void setPositiveText(String text) {
		mPositiveText = text;
	}

	public void setNegativeText(String text) {
		mNegativeText = text;
	}

	public void setNeutralText(String text) {
		mNeutralText = text;
	}

	public void setPositiveListener(DialogInterface.OnClickListener listener) {
		mPositiveListener = listener;
	}

	public void setNegativeListener(DialogInterface.OnClickListener listener) {
		mNegativeListener = listener;
	}

	public void setNeutralListener(DialogInterface.OnClickListener listener) {
		mNeutralListener = listener;
	}

	public void setView(View view) {
		mView = view;
	}

}
