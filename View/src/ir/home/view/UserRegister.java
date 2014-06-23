package ir.home.view;

import ir.home.controller.UserController;
import ir.home.habbeh.R;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;
import android.app.Activity;
import android.content.Context;
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

				if (IsConnectedToInternet() == true) {
					UserController controller = new UserController();
					try {
						controller.register(userName.getText().toString(),
								userEmail.getText().toString(), userPassword
										.getText().toString());
					} catch (IOException e) {
						e.printStackTrace();
					} catch (XmlPullParserException e) {
						e.printStackTrace();
					}
				} else {
					Toast.makeText(getBaseContext(),
							"For Register Please Connect To Internet",
							Toast.LENGTH_LONG).show();
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

}
