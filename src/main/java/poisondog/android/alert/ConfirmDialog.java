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

import android.app.AlertDialog;
import android.content.DialogInterface;
/**
 * @author Adam Huang <poisondog@gmail.com>
 */
public class ConfirmDialog extends AlertDialog {
	private DialogInterface.OnClickListener empty;
	private DialogParameter mParameter;

	public ConfirmDialog(DialogParameter parameter) {
		super(parameter.getContext());
		mParameter = parameter;
		empty = new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface di, int whichButton) {
			}
		};
		this.setTitle(parameter.getTitle());
		this.setMessage(parameter.getMessage());
		this.setButton(DialogInterface.BUTTON_POSITIVE, parameter.getPositiveText(), empty);
		this.setButton(DialogInterface.BUTTON_NEGATIVE, parameter.getNegativeText(), empty);
	}

	public void setPositiveListener(DialogInterface.OnClickListener listener) {
		this.setButton(DialogInterface.BUTTON_POSITIVE, mParameter.getPositiveText(), listener);
	}
}
