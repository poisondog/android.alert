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
import poisondog.core.Mission;

/**
 * @author Adam Huang
 * @since 2017-04-05
 */
public class DialogFactory implements Mission<DialogParameter> {

	@Override
	public AlertDialog execute(DialogParameter parameter) {
		AlertDialog.Builder builder = new AlertDialog.Builder(parameter.getContext());
		if (!parameter.getTitle().isEmpty())
			builder.setTitle(parameter.getTitle());
		if (!parameter.getMessage().isEmpty())
			builder.setMessage(parameter.getMessage());
		if (!parameter.getPositiveText().isEmpty())
			builder.setPositiveButton(parameter.getPositiveText(), parameter.getPositiveListener());
		if (!parameter.getNegativeText().isEmpty())
			builder.setNegativeButton(parameter.getNegativeText(), parameter.getNegativeListener());
		if (!parameter.getNeutralText().isEmpty())
			builder.setNeutralButton(parameter.getNeutralText(), parameter.getNeutralListener());
		if (parameter.getView() != null) {
			builder.setView(parameter.getView());
		}
		return builder.create();
	}
}
