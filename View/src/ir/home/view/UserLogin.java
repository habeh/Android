package ir.home.view;

import ir.home.controller.UserController;
import ir.home.habbeh.R;
import ir.home.model.TbUser;

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
import android.widget.Toast;

public class UserLogin extends Activity {

	private EditText UserName;
	private EditText UserPassword;
	private Button Login;
	private Button Forgiv;
	private TbUser Result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userlogin);

		UserName = (EditText) findViewById(R.id.UserName);
		UserPassword = (EditText) findViewById(R.id.UserPassword);
		Login = (Button) findViewById(R.id.Login);

		Login.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				UserController controller = new UserController();
				try {
					Result = controller.login(UserName.getText().toString(),
							UserPassword.getText().toString());

				} catch (IOException e) {
					e.printStackTrace();
				} catch (XmlPullParserException e) {
					e.printStackTrace();
				}

				if (Result == null) {
					Toast.makeText(getBaseContext(), "Login Failed",
							Toast.LENGTH_LONG).show();

				} else {

					// Get And Save Data
					SavePrefs("UserName", Result.getUserName().toString());
					SavePrefs("UserId", Integer.toString(Result.getId()));
					Toast.makeText(getBaseContext(),
							Result.getEmail().toString(), Toast.LENGTH_LONG)
							.show();

					// Go To Next Page
					Intent myIntent = new Intent(getBaseContext(),
							UserProfile.class);
					startActivityForResult(myIntent, 0);

				}

			}

		});

		Forgiv = (Button) findViewById(R.id.Forgiv);
		Forgiv.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				Intent myIntent = new Intent(arg0.getContext(),
						UserForgiveInformation.class);
				startActivityForResult(myIntent, 0);
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
