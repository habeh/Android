package ir.home.view;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import ir.home.controller.UserController;
import ir.home.habbeh.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserForgiveInformation extends Activity {

	private EditText Email;
	private Button Send;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forgivinformation);
		
		Email=(EditText)findViewById(R.id.UserEmail);
		Send=(Button)findViewById(R.id.Send);
		Send.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0){
				UserController controller = new UserController();
				
				try {
					controller.sendForgiveInformation(Email.getText().toString());
				} catch (IOException e) {
					e.printStackTrace();
				} catch (XmlPullParserException e) {
					e.printStackTrace();
				}
				Toast.makeText(getBaseContext(), "Send Email Successfuly , Please Check Your MailBox", Toast.LENGTH_LONG).show();
			}
		}
		);
	}
}

