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

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;
import poisondog.core.Mission;
import android.os.Build;
import android.provider.Settings;

/**
 * @author Adam Huang
 * @since 2018-06-27
 */
public class FloatingNotification implements Mission<Context> {
	@Override
	public Void execute(Context context) {
		if (Build.VERSION.SDK_INT >= 23) {
//			if(!Settings.canDrawOverlays(context)) {
//				Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
//				context.startActivity(intent);
//			} else {
//			}
			//Android6.0以上
		} else {
			//Android6.0以下
		}
		context.startService(new Intent(context, FloatingViewService.class));
		return null;
	}
}
