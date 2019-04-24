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
public class ShowBottomListDialog implements Mission<String[]> {
	private Context mContext;
	private Mission<String> mHandler;

	/**
	 * Constructor
	 */
	public ShowBottomListDialog(Context context, Mission<String> handler) {
		mContext = context;
		mHandler = handler;
	}

	@Override
	public String[] execute(String... input) {
		final BottomSheetDialog dialog = new BottomSheetDialog(mContext);
		View root = LayoutInflater.from(mContext).inflate(R.layout.bottom_list_view, null, false);
		LinearLayout linear = (LinearLayout) root.findViewById(R.id.container);
		for (final String text : input) {
			Button b = new Button(mContext);
			b.setText(text);
			b.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					try {
						mHandler.execute(text);
					} catch(Exception e) {
						e.printStackTrace();
					}
					dialog.dismiss();
				}
			});
			linear.addView(b);
		}
		dialog.setContentView(root);
		dialog.show();
		return input;
	}
}
