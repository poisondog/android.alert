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

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import poisondog.core.Mission;

/**
 * @author Adam Huang
 * @since 2018-07-24
 */
public class SimpleProgress implements Mission<DialogParameter> {

	@Override
	public AlertDialog execute(DialogParameter para) {
		LayoutInflater inflater = LayoutInflater.from(para.getContext());
		View content = inflater.inflate(R.layout.progress_simple, null);
		TextView messageView = (TextView) content.findViewById(R.id.message);
		messageView.setText(para.getMessage());
		para.setMessage("");
		para.setView(content);
		DialogFactory factory = new DialogFactory(para.getContext());
		AlertDialog dialog = factory.execute(para);
		return dialog;
	}
}
