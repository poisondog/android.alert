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
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import poisondog.core.Mission;

/**
 * @author Adam Huang
 * @since 2017-04-27
 */
public class ShowListDialog implements Mission<String[]> {
	private Context mContext;
	private String mTitle;
	private Mission<String> mHandler;

	/**
	 * Constructor
	 */
	public ShowListDialog(Context context, String title, Mission<String> handler) {
		mContext = context;
		mTitle = title;
		mHandler = handler;
	}

	@Override
	public String[] execute(String... input) {
		ListView list = new ListView(mContext);
		DialogParameter parameter = new DialogParameter(mContext);
		if (!mTitle.isEmpty())
			parameter.setTitle(mTitle);
		parameter.setView(list);
		DialogFactory factory = new DialogFactory();
		final AlertDialog dialog = factory.execute(parameter);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> adapter,View v, int position, long id){
				try {
					mHandler.execute(adapter.getItemAtPosition(position).toString());
				} catch(Exception e) {
					e.printStackTrace();
				}
				dialog.dismiss();
			}
		});
		list.setAdapter(new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, input));
		dialog.show();
		return input;
	}
}
