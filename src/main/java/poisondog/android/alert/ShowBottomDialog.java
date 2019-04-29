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

import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import poisondog.core.Mission;

/**
 * @author Adam Huang
 * @since 2017-04-27
 */
public class ShowBottomDialog implements Mission<DialogParameter> {
	private Context mContext;

	/**
	 * Constructor
	 */
	public ShowBottomDialog(Context context) {
		mContext = context;
	}

	@Override
	public DialogParameter execute(DialogParameter para) {
		final BottomSheetDialog dialog = new BottomSheetDialog(mContext);
		dialog.setContentView(para.getView());
		dialog.show();
		return para;
	}
}
