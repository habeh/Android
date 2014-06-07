package ir.home.view;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import ir.home.controller.UserController;
import ir.home.habbeh.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserRegister extends Activity {
	public static EditText UserName;
	public static EditText UserEmail;
	public static EditText UserPassword;
	public static Button Register;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userregister);

		UserName = (EditText) findViewById(R.id.UserName);
		UserEmail = (EditText) findViewById(R.id.UserEmail);
		UserPassword = (EditText) findViewById(R.id.UserPassword);
		Register = (Button) findViewById(R.id.Register);

		Register.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				UserController controller = new UserController();
				try {
					Toast.makeText(getApplicationContext(), "before controller.register", 0).show();
					
					controller.register(UserName.getText().toString(),
							UserEmail.getText().toString(), UserPassword
									.getText().toString());
					
					Toast.makeText(getApplicationContext(), UserName.getText(), 0).show();
					
				} catch (IOException e) {
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), e.getMessage(), 0).show();
					Log.d("ws",e.getMessage());
				} catch (XmlPullParserException e) {
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), e.getMessage(), 0).show();
					Log.d("ws",e.getMessage());
				}
			}
		});

	}

}
