package ir.home.view;

import ir.home.controller.MessageController;
import ir.home.habbeh.R;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SendMessage extends Activity {

	private EditText CategoryId;
	private EditText UserId;
	private EditText Description;
	private Button Send;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sendmessage);

		// Load Data From Shared
		final SharedPreferences sp = this.getSharedPreferences(
				"UserInformation", MODE_PRIVATE);
		final String UserIdP = sp.getString("UserId", "");

		CategoryId = (EditText) findViewById(R.id.CategoryId);
		UserId = (EditText) findViewById(R.id.UserId);
		Description = (EditText) findViewById(R.id.Description);
		Send = (Button) findViewById(R.id.Send);

		UserId.setText(UserIdP.toString());
		UserId.setEnabled(false);
		Send.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				MessageController controller = new MessageController();
				try {
					controller.InsertMessage(
							Integer.parseInt(CategoryId.getText().toString()),
							Integer.parseInt(UserId.getText().toString()),
							Description.getText().toString());
				} catch (IOException e) {
					e.printStackTrace();
				} catch (XmlPullParserException e) {
					e.printStackTrace();
				}
			}
		});

	}

}
