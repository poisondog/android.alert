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

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import poisondog.core.Mission;

/**
 * @author Adam Huang
 * @since 2018-01-05
 */
public class Notification implements Mission<Notification.Parameter> {

	@Override
	public Void execute(Notification.Parameter para) {
		NotificationManager manager = (NotificationManager) para.mContext.getSystemService(Context.NOTIFICATION_SERVICE);
		manager.notify(1, para.mBuilder.build());
		return null;
	}

	public static class Parameter {
		private Context mContext;
		private NotificationCompat.Builder mBuilder;

		public Parameter(Context context) {
			mContext = context;
			mBuilder = new NotificationCompat.Builder(context);
		}

		public Parameter(Context context, String title, String text, int smallIcon) {
			this(context);
		}

		public void setTitle(String title) {
			mBuilder.setContentTitle(title);
		}

		public void setText(String text) {
			mBuilder.setContentText(text);
		}

		public void setSmallIcon(int smallIcon) {
			mBuilder.setSmallIcon(smallIcon);
		}

		public void setProgress(int max, int progress, boolean indeterminate) {
			mBuilder.setProgress(max, progress, indeterminate);
		}
	}
}
