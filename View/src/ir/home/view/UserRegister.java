package ir.home.view;

import ir.home.controller.UserController;
import ir.home.habbeh.R;
import ir.home.model.TbUser;

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

public class UserRegister extends Activity {

	private EditText userName;
	private EditText userEmail;
	private EditText userPassword;
	private Button register;
	private TbUser result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userregister);

		userName = (EditText) findViewById(R.id.userName);
		userEmail = (EditText) findViewById(R.id.userEmail);
		userPassword = (EditText) findViewById(R.id.userPassword);
		register = (Button) findViewById(R.id.register);

		register.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				String UserNameCheck = userName.getText().toString();
				String UserEmailCheck = userEmail.getText().toString();
				String UserPasswordCheck = userPassword.getText().toString();
				if (UserNameCheck.matches("")) {
					Toast.makeText(getBaseContext(),
							"You did not enter a UserName", Toast.LENGTH_SHORT)
							.show();
				}

				else if (UserEmailCheck.matches("")) {
					Toast.makeText(getBaseContext(),
							"You did not enter a Email", Toast.LENGTH_SHORT)
							.show();
				} else if (UserPasswordCheck.matches("")) {
					Toast.makeText(getBaseContext(),
							"You did not enter a Password", Toast.LENGTH_SHORT)
							.show();
				} else {

					if (IsConnectedToInternet() == true) {
						UserController controller = new UserController();
						try {
							controller.register(userName.getText().toString(),
									userEmail.getText().toString(),
									userPassword.getText().toString());
						} catch (IOException e) {
							e.printStackTrace();
						} catch (XmlPullParserException e) {
							e.printStackTrace();
						}
						// after register for load UserInformation And Save In
						// Local
						UserController Logincontroller = new UserController();
						try {
							result = Logincontroller.login(userName.getText()
									.toString(), userPassword.getText()
									.toString());

						} catch (IOException e) {
							e.printStackTrace();
						} catch (XmlPullParserException e) {
							e.printStackTrace();
						}

						SavePrefs("UserName", result.getUserName().toString());
						SavePrefs("UserId", Integer.toString(result.getId()));
						Toast.makeText(getBaseContext(),
								"Register Successfully", Toast.LENGTH_LONG)
								.show();

						Intent myIntent = new Intent(arg0.getContext(),
								UserProfile.class);
						startActivityForResult(myIntent, 0);
						finish();
					} else {
						Toast.makeText(getBaseContext(),
								"For Register Please Connect To Internet",
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

	private void SavePrefs(String key, String value) {
		SharedPreferences sp = this.getSharedPreferences("UserInformation",
				MODE_PRIVATE);
		SharedPreferences.Editor edit = sp.edit();
		edit.putString(key, value);
		edit.commit();

	}

	@Override
	public void onBackPressed() {
		Intent myIntent = new Intent(UserRegister.this, MainActivity.class);
		startActivityForResult(myIntent, 0);
		super.onBackPressed();
	}

}
