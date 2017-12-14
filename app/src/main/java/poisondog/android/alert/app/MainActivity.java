package poisondog.android.alert.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import poisondog.android.alert.DialogFactory;
import poisondog.android.alert.DialogParameter;
import poisondog.android.alert.ShowListDialog;
import poisondog.android.alert.ShowSnackbar;
import poisondog.core.Mission;


public class MainActivity extends Activity {
	private DialogInterface.OnClickListener mListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mListener = new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface di, int whichButton) {
				System.out.println("interface: " + di);
				System.out.println("whichButton: " + whichButton);
			}
		};
	}

	private void showDialog(DialogParameter para) {
		DialogFactory factory = new DialogFactory();
		AlertDialog dialog = factory.execute(para);
		dialog.show();
	}

	public void alertClick(View v) {
		DialogParameter para = new DialogParameter(this);
		para.setTitle("alert title");
		para.setMessage("alert message");
		showDialog(para);
	}

	public void okClick(View v) {
		DialogParameter para = new DialogParameter(this);
		para.setTitle("ok title");
		para.setMessage("ok message");
		para.setPositiveText("OK");
		para.setPositiveListener(mListener);
		showDialog(para);
	}

	public void yesnoClick(View v) {
		DialogParameter para = new DialogParameter(this);
		para.setTitle("yes no title");
		para.setMessage("yes no message");
		para.setPositiveText("Yes");
		para.setNegativeText("No");
		showDialog(para);
	}

	public void yesnootherClick(View v) {
		DialogParameter para = new DialogParameter(this);
		para.setTitle("yes no other title");
		para.setMessage("yes no other message");
		para.setPositiveText("Yes");
		para.setPositiveListener(mListener);
		para.setNegativeText("No");
		para.setNegativeListener(mListener);
		para.setNeutralText("Other");
		para.setNeutralListener(mListener);
		showDialog(para);
	}

	public void viewClick(View v) {
		ImageView image = new ImageView(this);
		image.setImageResource(R.drawable.ic_launcher);
		DialogParameter para = new DialogParameter(this);
		para.setTitle("view title");
		para.setMessage("view message");
		para.setView(image);
		para.setPositiveText("OK");
		showDialog(para);
	}

	public void listClick(View v) {
		ShowListDialog task = new ShowListDialog(this, "list title", new Mission<String>() {
			@Override
			public Void execute(String input) {
				DialogParameter para = new DialogParameter(MainActivity.this);
				para.setTitle(input);
				showDialog(para);
				return null;
			}
		});
		task.execute("str 1", "str 2", "str 3");
	}

	public void snackbarClick(View v) {
		ShowSnackbar.Parameter para = new ShowSnackbar.Parameter(findViewById(R.id.snackbar), "snack message");
		para.setBackgroundColor(Color.RED);
		ShowSnackbar task = new ShowSnackbar();
		task.execute(para);
	}

	public void snackbarActionClick(View v) {
		ShowSnackbar.Parameter para = new ShowSnackbar.Parameter(findViewById(R.id.snackbar), "snack message");
		para.setBackgroundColor(Color.BLUE);
		para.setAction("snack action", new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DialogParameter para = new DialogParameter(MainActivity.this);
				para.setTitle("click action");
				showDialog(para);
			}
		});
		ShowSnackbar task = new ShowSnackbar();
		task.execute(para);
	}

}
