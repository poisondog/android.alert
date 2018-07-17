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

import android.app.AlertDialog;
import android.content.Context;
import poisondog.core.Mission;
import android.util.TypedValue;
import poisondog.android.alert.R;

/**
 * @author Adam Huang
 * @since 2017-04-05
 */
public class DialogFactory implements Mission<DialogParameter> {
	private AlertDialog.Builder mBuilder;

	/**
	 * Constructor
	 */
	public DialogFactory(Context context) {
		TypedValue typedValue = new TypedValue();
		context.getTheme().resolveAttribute(R.attr.DialogStyle, typedValue, true);
		int resourceId = typedValue.resourceId;
		mBuilder = new AlertDialog.Builder(context, resourceId);
	}

	/**
	 * Constructor
	 */
	public DialogFactory(Context context, int styleID) {
		mBuilder = new AlertDialog.Builder(context, styleID);
	}

	@Override
	public AlertDialog execute(DialogParameter parameter) {
		if (!parameter.getTitle().isEmpty())
			mBuilder.setTitle(parameter.getTitle());
		if (!parameter.getMessage().isEmpty())
			mBuilder.setMessage(parameter.getMessage());
		if (!parameter.getPositiveText().isEmpty())
			mBuilder.setPositiveButton(parameter.getPositiveText(), parameter.getPositiveListener());
		if (!parameter.getNegativeText().isEmpty())
			mBuilder.setNegativeButton(parameter.getNegativeText(), parameter.getNegativeListener());
		if (!parameter.getNeutralText().isEmpty())
			mBuilder.setNeutralButton(parameter.getNeutralText(), parameter.getNeutralListener());
		if (parameter.getView() != null) {
			mBuilder.setView(parameter.getView());
		}
		return mBuilder.create();
	}
}
