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
import android.app.NotificationChannel;
import android.media.RingtoneManager;
import android.net.Uri;
import android.graphics.Color;
import android.os.Build;

/**
 * @author Adam Huang
 * @since 2018-01-05
 */
public class Notification implements Mission<Notification.Parameter> {
	private Context mContext;

	/**
	 * Constructor
	 */
	public Notification(Context context) {
		mContext = context;
	}

	@Override
	public Void execute(Notification.Parameter para) {
		NotificationManager manager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
		manager.notify(para.mID, para.build(mContext));
		return null;
	}

	public static class Parameter {
		private int mID;
		private Channel mChannel;
		private String mTitle;
		private String mText;
		private int mSmallIcon;
		private int mProgress = -1;
		private int mMax = -1;
		private Uri mSound;

		private Parameter(int id) {
			mID = id;
		}

		public Parameter(int id, String title, String text, int smallIcon) {
			this(id);
			setTitle(title);
			setText(text);
			setSmallIcon(smallIcon);
		}

		public Parameter(String title, String text, int smallIcon) {
			this(1, title, text, smallIcon);
		}

		public void setChannel(Channel channel) {
			mChannel = channel;
		}

		public void setTitle(String title) {
			mTitle = title;
		}

		public void setText(String text) {
			mText = text;
		}

		public void setSmallIcon(int smallIcon) {
			mSmallIcon = smallIcon;
		}

		public void setProgress(int progress, int max) {
			mProgress = progress;
			mMax = max;
		}

		public void removeProgress() {
			setProgress(-1, -1);
		}

		public void setSound(Uri uri) {
			mSound = uri;
		}

		public android.app.Notification build(Context context) {
			if (Build.VERSION.SDK_INT >= 26) {
				Build26 builder = new Build26(context);
				return builder.execute(this).build();
			}
			Build24 builder = new Build24(context);
			return builder.execute(this).build();
		}

	}

	static class Build24 implements Mission<Parameter> {
		private Context mContext;
		public Build24(Context context) {
			mContext = context;
		}
		@Override
		public android.app.Notification.Builder execute(Parameter para) {
			android.app.Notification.Builder builder = new android.app.Notification.Builder(mContext);
			if (para.mChannel != null)
				builder = new android.app.Notification.Builder(mContext, para.mChannel.getID());
			if (para.mTitle != null && !para.mTitle.isEmpty())
				builder.setContentTitle(para.mTitle);
			if (para.mText != null && !para.mText.isEmpty())
				builder.setContentText(para.mText);
			if (para.mSmallIcon != 0)
				builder.setSmallIcon(para.mSmallIcon);
			if (para.mMax == 0 && para.mProgress == 0)
				builder.setProgress(0, 0, true);
			else
				builder.setProgress(para.mMax, para.mProgress, false);
			if (para.mMax < 0 && para.mProgress < 0)
				builder.setProgress(0, 0, false);
			if (para.mSound == null) {
				builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
			} else {
				builder.setSound(para.mSound);
			}
			return builder;
		}
	}

	static class Build26 implements Mission<Parameter> {
		private Context mContext;
		public Build26(Context context) {
			mContext = context;
		}
		@Override
		public android.app.Notification.Builder execute(Parameter para) {
			if (para.mChannel == null)
				para.setChannel(new Channel("alert.channel", "default channel"));

			Channel pChannel = para.mChannel;
			NotificationManager manager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
			NotificationChannel channel = new NotificationChannel(pChannel.getID(), pChannel.getDescription(), pChannel.getImportance());
			channel.setDescription(pChannel.getDescription());
			//  channel.enableLights(true);
			//  channel.setLightColor(Color.RED);
			//  channel.enableVibration(true);
			//  channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
			manager.createNotificationChannel(channel);

			Build24 task = new Build24(mContext);
			android.app.Notification.Builder builder = task.execute(para);
			builder.setAutoCancel(true);
			return builder;

		}
	}

	static class Channel {
		private String mID;
		private String mDescription;
		private int mImportance;
		public Channel(String id, String description) {
			mID = id;
			mDescription = description;
			mImportance = NotificationManager.IMPORTANCE_DEFAULT;
		}
		public static Channel high(String id, String description) {
			Channel result = new Channel(id, description);
			result.mImportance = NotificationManager.IMPORTANCE_HIGH;
			return result;
		}
		public static Channel low(String id, String description) {
			Channel result = new Channel(id, description);
			result.mImportance = NotificationManager.IMPORTANCE_LOW;
			return result;
		}
		public String getID() {
			return mID;
		}
		public String getDescription() {
			return mDescription;
		}
		public int getImportance() {
			return mImportance;
		}
	}

}
