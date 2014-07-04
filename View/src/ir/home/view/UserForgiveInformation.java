package ir.home.view;

import ir.home.controller.UserController;
import ir.home.habbeh.R;
import ir.home.utility.HabehException;
import ir.home.view.utility.ConnectedToInternet;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserForgiveInformation extends Activity {

	private EditText userEmail;
	private Button sendEmail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forgivinformation);

		userEmail = (EditText) findViewById(R.id.forgivinformation_edittext_userEmail);
		initUsContact();
	}

	private void initUsContact() {

		sendEmail = (Button) findViewById(R.id.forgivinformation_button_send);
		sendEmail.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				String useremailText = userEmail.getText().toString();
				String error = "";

				if (useremailText.isEmpty()) {
					error = "You did not enter a Email";
				}

				if (error.isEmpty()) {
					if (ConnectedToInternet.isOnline(getBaseContext())) {
						UserController controller = new UserController();

						try {
							controller.sendForgiveInformation(useremailText);
						} catch (IOException e) {
							e.printStackTrace();
						} catch (XmlPullParserException e) {
							e.printStackTrace();
						} catch (HabehException e) {
                            Toast.makeText(getBaseContext(),
                                    e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
						Toast.makeText(
								getBaseContext(),
								"Send Email Successfuly , Please Check Your MailBox",
								Toast.LENGTH_LONG).show();
						userEmail.setText("");

					} else {
						Toast.makeText(
								getBaseContext(),
								"For Send Description Please Connect To Internet",
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
        Intent myIntent = new Intent(UserForgiveInformation.this, UserLogin.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivityForResult(myIntent, 0);
        super.onBackPressed();
    }
}
