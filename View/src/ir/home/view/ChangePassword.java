package ir.home.view;

import ir.home.controller.UserController;
import ir.home.habbeh.R;
import ir.home.utility.HabehException;

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

public class ChangePassword extends Activity {

	private EditText oldPass;
	private EditText newPass;
	private EditText renewPass;
	private Button changePassword;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.changepassword);

		// Load Data From Shared
		final SharedPreferences sp = this.getSharedPreferences(
				"UserInformation", MODE_PRIVATE);

		initChangePassword(sp);

	}

	private void initChangePassword(final SharedPreferences sp) {
		oldPass = (EditText) findViewById(R.id.changepassword_edittext_oldPassword);
		newPass = (EditText) findViewById(R.id.changepassword_edittext_newPassword);
		renewPass = (EditText) findViewById(R.id.changepassword_edittext_renewPassword);
		changePassword = (Button) findViewById(R.id.changepassword_button_Changepassword);
		changePassword.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				String oldPassText = oldPass.getText().toString();
				String newPassText = newPass.getText().toString();
				String renewPassText = renewPass.getText().toString();
				String error = "";

				if (oldPassText.isEmpty()) {
					error = "You did not enter a oldPassword";
				}

				if (newPassText.isEmpty()) {
					error = "Please Type New Password";
				}

				if (renewPassText.isEmpty()) {
					error = "Please Re-Type New Password";
				}

				if (!newPassText.equals(renewPassText)) {
					error = "Please Match Password";
				}
				if (error.isEmpty()) {
					UserController controller = new UserController();

					try {
						controller.ChangePassword(
								sp.getString("UserName", "0"), oldPass
										.getText().toString(), newPass
										.getText().toString());
					} catch (IOException e) {
						e.printStackTrace();
					} catch (XmlPullParserException e) {
						e.printStackTrace();
					} catch (HabehException e) {
						Toast.makeText(getBaseContext(), e.getMessage(),
								Toast.LENGTH_LONG).show();
					}

				} else {
					Toast.makeText(getBaseContext(), error, Toast.LENGTH_LONG)
							.show();
				}
			}
		});
	}
	public void onBackPressed() {
        Intent myIntent = new Intent(ChangePassword.this, UserProfile.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivityForResult(myIntent, 0);
        super.onBackPressed();
    }
}
