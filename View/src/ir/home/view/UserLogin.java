package ir.home.view;

import ir.home.controller.UserController;
import ir.home.habbeh.R;
import ir.home.model.TbUser;
import ir.home.view.utility.ConnectedToInternet;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserLogin extends Activity {

	private EditText UserName;
	private EditText UserPassword;
	private Button login;
	private TextView forgiv;
	private TextView registerTextView;
	private TbUser result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userlogin);

		UserName = (EditText) findViewById(R.id.UserName);
		UserPassword = (EditText) findViewById(R.id.UserPassword);
		login = (Button) findViewById(R.id.Login);
		login.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				String UserNameCheck = UserName.getText().toString();
				String UserPasswordCheck = UserPassword.getText().toString();
				if (UserNameCheck.matches("")) {
					Toast.makeText(getBaseContext(),
							"You did not enter a UserName", Toast.LENGTH_SHORT)
							.show();
				}

				else if (UserPasswordCheck.matches("")) {
					Toast.makeText(getBaseContext(),
							"You did not enter a Password", Toast.LENGTH_SHORT)
							.show();
				} else {
					if (ConnectedToInternet.isOnline(getBaseContext()) == true) {

						UserController controller = new UserController();
						try {
							result = controller.login(UserName.getText()
									.toString(), UserPassword.getText()
									.toString());

						} catch (IOException e) {
							e.printStackTrace();
						} catch (XmlPullParserException e) {
							e.printStackTrace();
						}

						if (result == null) {
							Toast.makeText(getBaseContext(), "Login Failed",
									Toast.LENGTH_LONG).show();

						} else {

							SavePrefs("UserName", result.getUserName()
									.toString());
							SavePrefs("UserId",
									Integer.toString(result.getId()));
							Toast.makeText(getBaseContext(),
									"You Login Successfully In Habbeh",
									Toast.LENGTH_LONG).show();

							// Go To Next Page
							Intent myIntent = new Intent(getBaseContext(),
									OfflineTextMessage.class);
							startActivityForResult(myIntent, 0);

						}
					}
				}

			}

		});

		forgiv = (TextView) findViewById(R.id.Forgiv);
		forgiv.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				Intent myIntent = new Intent(arg0.getContext(),
						UserForgiveInformation.class);
				startActivityForResult(myIntent, 0);
			}
		});

		registerTextView = (TextView) findViewById(R.id.registerTextView);
		registerTextView.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				Intent myIntent = new Intent(arg0.getContext(),
						UserRegister.class);
				startActivityForResult(myIntent, 0);
				finish();
			}
		});
	}

	private void SavePrefs(String key, String value) {
		SharedPreferences sp = this.getSharedPreferences("UserInformation",
				MODE_PRIVATE);
		SharedPreferences.Editor edit = sp.edit();
		edit.putString(key, value);
		edit.commit();

	}

}
