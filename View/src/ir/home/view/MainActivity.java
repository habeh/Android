package ir.home.view;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import ir.home.controller.MessageController;
import ir.home.habbeh.R;
import ir.home.model.TbMessage;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
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
	private TbMessage Result;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Login = (Button) findViewById(R.id.Login);
		Login.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(),
						UserLogin.class);
				startActivityForResult(myIntent, 0);
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
		Profile.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(),
						UserProfile.class);
				startActivityForResult(myIntent, 0);
			}
		});

		search = (Button) findViewById(R.id.search);
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
		SendMessage.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(),
						SendMessage.class);
				startActivityForResult(myIntent, 0);
			}
		});
		
		//get Count new message For Retrieve By User
		Time now = new Time();
		now.setToNow();

		MessageController controller=new MessageController();
		try {
			Result=controller.NewMessageCount(now.format("%m/%d/%Y %H:%M:%S").toString());
	} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}
		OnlineMessage.setText("New Message For Receipt ( "+ Integer.toString(Result.getnewMessageCount()).toString()+" )" );

	}
}