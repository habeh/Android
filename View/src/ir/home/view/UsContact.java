package ir.home.view;

import ir.home.controller.ContactController;
import ir.home.habbeh.R;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UsContact extends Activity {

	private EditText UserName;
	private EditText UserDescription;
	private Button Send;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.uscontact);

		UserName = (EditText) findViewById(R.id.UserName);
		UserDescription = (EditText) findViewById(R.id.UserDescription);

		final SharedPreferences sp = this.getSharedPreferences(
				"UserInformation", MODE_PRIVATE);
		UserName.setText(sp.getString("UserName", "0"));
		UserName.setEnabled(false);
		
		Send = (Button) findViewById(R.id.Send);
		Send.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				String UserDescriptionCheck = UserDescription.getText()
						.toString();
				if (UserDescriptionCheck.matches("")) {
					Toast.makeText(getBaseContext(),
							"You did not enter a Description",
							Toast.LENGTH_SHORT).show();
				} else {

					if (IsConnectedToInternet() == true) {
						ContactController controller = new ContactController();
						try {
							controller.Create(Integer.parseInt(sp.getString(
									"UserId", "0")), UserDescription.getText()
									.toString());
						} catch (IOException e) {
							e.printStackTrace();
						} catch (XmlPullParserException e) {
							e.printStackTrace();
						}

						Toast.makeText(getBaseContext(), "Send Successfully",
								Toast.LENGTH_LONG).show();

					} else {
						Toast.makeText(
								getBaseContext(),
								"For Send Description Please Connect To Internet",
								Toast.LENGTH_LONG).show();
					}

				}

			}
		});

	}

	public boolean IsConnectedToInternet() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}

	@Override
	public void onBackPressed() {
		Intent myIntent = new Intent(UsContact.this, MainActivity.class);
		startActivityForResult(myIntent, 0);
		super.onBackPressed();
	}

}
