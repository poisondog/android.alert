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
public class ShowBottomCustomDialog implements Mission<View[]> {
	private BottomSheetDialog mDialog;
	private Context mContext;

	/**
	 * Constructor
	 */
	public ShowBottomCustomDialog(Context context) {
		mContext = context;
		mDialog = new BottomSheetDialog(mContext);
	}

	public BottomSheetDialog getDialog() {
		return mDialog;
	}

	@Override
	public BottomSheetDialog execute(View... input) throws Exception {
		View root = LayoutInflater.from(mContext).inflate(R.layout.bottom_list_view, null, false);
		LinearLayout linear = (LinearLayout) root.findViewById(R.id.container);
		for (final View b : input) {
			linear.addView(b);
		}
		mDialog.setContentView(root);
		mDialog.show();
		return mDialog;
	}

}
