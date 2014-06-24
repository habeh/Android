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

public class UserProfile extends Activity {

	private TbUser Result;
	private EditText FirstName;
	private EditText LastName;
	private EditText Email;
	private EditText Password;
	private EditText Status;
	private Button Update;
	private TextView UserName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userprofile);

		UserName = (TextView) findViewById(R.id.UserName);
		FirstName = (EditText) findViewById(R.id.UserFirstName);
		LastName = (EditText) findViewById(R.id.UserLastName);
		Email = (EditText) findViewById(R.id.UserEmail);
		Status = (EditText) findViewById(R.id.UserStatus);
		Password = (EditText) findViewById(R.id.UserPassword);

		final SharedPreferences sp = this.getSharedPreferences(
				"UserInformation", MODE_PRIVATE);
		final int UserIdP = Integer.parseInt(sp.getString("UserId", "0"));
		UserController controller = new UserController();
		try {
			Result = controller.getProfile(UserIdP);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}

		UserName.setText(Result.getUserName().toString());
		FirstName.setText(Result.getFirstName().toString());
		LastName.setText(Result.getLastName().toString());
		Status.setText(Result.getStatus().toString());
		Email.setText(Result.getEmail().toString());
		Update = (Button) findViewById(R.id.Update);

		Update.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				UserController controller1 = new UserController();
				try {
					controller1.SaveProfile(UserName.getText().toString(),
							FirstName.getText().toString(), LastName.getText()
									.toString(), Email.getText().toString(),
							Status.getText().toString(), Password.getText()
									.toString());

				} catch (IOException e) {
					e.printStackTrace();
				} catch (XmlPullParserException e) {
					e.printStackTrace();
				}
				Toast.makeText(getBaseContext(),
						"Update Your Profile Succesfully", Toast.LENGTH_LONG)
						.show();
				Intent myIntent = new Intent(arg0.getContext(),
						MainActivity.class);
				startActivityForResult(myIntent, 0);
				finish();

			}
		});

	}

	@Override
	public void onBackPressed() {
		Intent myIntent = new Intent(UserProfile.this, MainActivity.class);
		startActivityForResult(myIntent, 0);
		super.onBackPressed();
	}

}
