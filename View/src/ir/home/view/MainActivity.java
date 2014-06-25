package ir.home.view;

import ir.home.controller.MessageController;
import ir.home.habbeh.R;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button Login;
	private Button Profile;
	private Button Register;
	private Button OnlineMessage;
	private Button OfflineMessage;
	private Button Findpeople;
	private Button UsContact;
	private Button UsAbout;
	private Button HabbehAbout;
	private Button search;
	private Button SendMessage;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final SharedPreferences sp = this.getSharedPreferences(
				"UserInformation", MODE_PRIVATE);
		// sp.edit().clear().commit();
		if (sp.getString("UserId", "0") == "0") {
			Intent myIntent = new Intent(MainActivity.this,
					UserLogin.class);
			startActivityForResult(myIntent, 0);

		} 

		Login = (Button) findViewById(R.id.Login);
		Login.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(), UserLogin.class);
				startActivityForResult(myIntent, 0);
				finish();
			}
		});

		Register = (Button) findViewById(R.id.Register);
		Register.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(),
						UserRegister.class);
				startActivityForResult(myIntent, 0);
			}
		});

		Profile = (Button) findViewById(R.id.Profile);
		if (sp.getString("UserId", "0") == "0") {
			Profile.setVisibility(View.GONE);

		}
		Profile.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {

				if (sp.getString("UserId", "0") == "0") {
					Toast.makeText(getBaseContext(), "Please First Login",
							Toast.LENGTH_LONG).show();

				} else {
					Intent myIntent = new Intent(view.getContext(),
							UserProfile.class);
					startActivityForResult(myIntent, 0);
				}

			}
		});

		search = (Button) findViewById(R.id.search);
		if (sp.getString("UserId", "0") == "0") {
			search.setVisibility(View.GONE);

		}
		search.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(),
						UserSearch.class);
				startActivityForResult(myIntent, 0);
			}
		});

		OnlineMessage = (Button) findViewById(R.id.OnlineMessage);
		OnlineMessage.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(),
						OnlineTextMessage.class);
				startActivityForResult(myIntent, 0);
			}
		});

		OfflineMessage = (Button) findViewById(R.id.OfflineMessage);
		OfflineMessage.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(),
						OfflineTextMessage.class);
				startActivityForResult(myIntent, 0);
			}
		});

		Findpeople = (Button) findViewById(R.id.Findpeople);
		if (sp.getString("UserId", "0") == "0") {
			Findpeople.setVisibility(View.GONE);

		}
		Findpeople.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(),
						Findpeople.class);
				startActivityForResult(myIntent, 0);
			}
		});

		UsContact = (Button) findViewById(R.id.UsContact);
		UsContact.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(), UsContact.class);
				startActivityForResult(myIntent, 0);
			}
		});

		UsAbout = (Button) findViewById(R.id.UsAbout);
		UsAbout.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				// sp.edit().clear().commit();
				Intent myIntent = new Intent(view.getContext(), UsAbout.class);
				startActivityForResult(myIntent, 0);

			}
		});

		HabbehAbout = (Button) findViewById(R.id.HabbehAbout);
		HabbehAbout.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(),
						HabbehAbout.class);
				startActivityForResult(myIntent, 0);
			}
		});

		SendMessage = (Button) findViewById(R.id.SendMessage);
		if (sp.getString("UserId", "0") == "0") {
			SendMessage.setVisibility(View.GONE);

		}
		SendMessage.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(),
						SendMessage.class);
				startActivityForResult(myIntent, 0);
			}
		});
		if (IsConnectedToInternet() == true) {
			MessageController controller = new MessageController();
			int count = 0;
			try {
				count = controller.CountNewMessage("2014-01-01");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}
			OnlineMessage.setText("You Have  " + count + "  New Message");
		} else {
			Toast.makeText(getBaseContext(),
					"For Receipt New Message Please Connect To Internet",
					Toast.LENGTH_LONG).show();
		}
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