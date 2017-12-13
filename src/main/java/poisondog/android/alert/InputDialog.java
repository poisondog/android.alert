/*
 * Copyright (C) 2013 Adam Huang <poisondog@gmail.com>
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

import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * @author poisondog <poisondog@gmail.com>
 */
public class InputDialog extends ConfirmDialog {
	private EditText input;
	private LinearLayout layout;

	public InputDialog(DialogParameter parameter) {
		super(parameter);
		input = new EditText(parameter.getContext());
		input.setSelectAllOnFocus(true);
		input.setSingleLine();

		layout = new LinearLayout(parameter.getContext());
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.addView(input);

		this.setView(layout);
	}

	public void setInputText(String text) {
		input.setText(text);
	}

	public String getInputText() {
		return input.getText().toString();
	}
}
