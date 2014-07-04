package ir.home.view;

import ir.home.controller.ContactController;
import ir.home.habbeh.R;
import ir.home.utility.HabehException;
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
import android.widget.Toast;

public class UsContact extends Activity {

	private EditText userName;
	private EditText userDescription;
	private Button Send;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.uscontact);

		userName = (EditText) findViewById(R.id.uscontact_edittext_userName);
		userDescription = (EditText) findViewById(R.id.uscontact_edittext_userDescription);
		initUserDescription();

	}
	

	private void initUserDescription() {
		final SharedPreferences sp = this.getSharedPreferences(
				"UserInformation", MODE_PRIVATE);
		userName.setText(sp.getString("UserName", "0"));
		userName.setEnabled(false);
		Send = (Button) findViewById(R.id.uscontact_button_Send);
		Send.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				
				String userDescriptionText = userDescription.getText().toString();
				int UserId = Integer.parseInt(sp.getString("UserId", "0"));
				String error = "";
				
				if (userDescriptionText.isEmpty()) {
					error = "You did not enter a Description";
				}

				if (error.isEmpty()) {
					if (ConnectedToInternet.isOnline(getBaseContext())) {
						ContactController controller = new ContactController();
						try {
							controller.Create(UserId, userDescriptionText);
						} catch (IOException e) {
							e.printStackTrace();
						} catch (XmlPullParserException e) {
							e.printStackTrace();
						} catch (HabehException e) {
                            Toast.makeText(getBaseContext(),
                                    e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }

						Toast.makeText(getBaseContext(), "Send Successfully",
								Toast.LENGTH_LONG).show();
						userDescription.setText("");

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
        Intent myIntent = new Intent(UsContact.this, MainActivity.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivityForResult(myIntent, 0);
        super.onBackPressed();
    }

}
