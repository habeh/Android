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
import android.widget.TextView;
import android.widget.Toast;

public class UserLogin extends Activity {

	private EditText userName;
	private EditText userPassword;
	private Button login;
	private TextView forgiv;
	private TextView registerTextView;
	private TbUser result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userlogin);

		userName = (EditText) findViewById(R.id.UserName);
		userPassword = (EditText) findViewById(R.id.UserPassword);
		login = (Button) findViewById(R.id.Login);
		
		login.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				UserController controller = new UserController();
				try {
					result = controller.login(userName.getText().toString(),
							userPassword.getText().toString());

				} catch (IOException e) {
					e.printStackTrace();
				} catch (XmlPullParserException e) {
					e.printStackTrace();
				}

				if (result == null) {
					Toast.makeText(getBaseContext(), "Login Failed",
							Toast.LENGTH_LONG).show();

				} else {

					SavePrefs("UserName", result.getUserName().toString());
					SavePrefs("UserId", Integer.toString(result.getId()));
					Toast.makeText(getBaseContext(),
							result.getEmail().toString(), Toast.LENGTH_LONG)
							.show();

					// Go To Next Page
					Intent myIntent = new Intent(getBaseContext(),
							UserProfile.class);
					startActivityForResult(myIntent, 0);

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
		
		registerTextView= (TextView) findViewById(R.id.registerTextView);
		registerTextView.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				Intent myIntent = new Intent(arg0.getContext(),
						UserRegister.class);
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
