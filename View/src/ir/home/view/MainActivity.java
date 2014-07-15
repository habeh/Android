package ir.home.view;

import ir.home.controller.MessageController;
import ir.home.controller.UserController;
import ir.home.habbeh.R;
import ir.home.model.TbMessage;
import ir.home.model.TbUser;
import ir.home.utility.HabehException;
import ir.home.view.database.DBAdapter;
import ir.home.view.utility.ConnectedToInternet;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button login;
	private Button profile;
	private Button register;
	private Button onlineMessage;
	private Button offlineMessage;
	private Button findpeople;
	private Button usContact;
	private Button usAbout;
	private Button habbehAbout;
	private Button searchUsers;
	private Button sendMessage;
	private List<TbMessage> list;
	private TbUser Result;
	DBAdapter db;
	String userNameText;
	String lastUpdate;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		db = new DBAdapter(this);
		db.open();
		final SharedPreferences sp = this.getSharedPreferences(
				"UserInformation", MODE_PRIVATE);
		// sp.edit().clear().commit();
		if (sp.getString("UserId", "0") == "0") {
			Intent myIntent = new Intent(MainActivity.this, UserLogin.class);
			startActivityForResult(myIntent, 0);
		}

		lastUpdate = db.getLastUpdate();

		initGetOnlineMessage();

		initLogin();

		initRegister();

		initProfile(sp);

		initSearchUsers(sp);

		initOnlineMessage();

		initOfflineMessage();

		initFindpeople(sp);

		initUsContact();

		initUsAbout();

		initHabbehAbout();

		initSendMessage(sp);

		new AsyncCountNewMessage().execute();

	}

	private void initSendMessage(final SharedPreferences sp) {
		sendMessage = (Button) findViewById(R.id.main_button_SendMessage);
		sendMessage.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(),
						SendMessage.class);
				myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						| Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivityForResult(myIntent, 0);

			}
		});
	}

	private void initHabbehAbout() {
		habbehAbout = (Button) findViewById(R.id.main_button_HabbehAbout);
		habbehAbout.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(),
						HabbehAbout.class);
				myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						| Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivityForResult(myIntent, 0);
			}
		});
	}

	private void initUsAbout() {
		usAbout = (Button) findViewById(R.id.main_button_UsAbout);
		usAbout.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(),
						UserFriendList.class);
				myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						| Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivityForResult(myIntent, 0);

			}
		});
	}

	private void initUsContact() {
		usContact = (Button) findViewById(R.id.main_button_UsContact);
		usContact.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(), UsContact.class);
				myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						| Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivityForResult(myIntent, 0);
			}
		});
	}

	private void initFindpeople(final SharedPreferences sp) {
		findpeople = (Button) findViewById(R.id.main_button_Findpeople);

		findpeople.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(),
						Findpeople.class);
				myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						| Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivityForResult(myIntent, 0);
			}
		});
	}

	private void initOfflineMessage() {
		offlineMessage = (Button) findViewById(R.id.main_button_OfflineMessage);
		offlineMessage.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(),
						OfflineTextMessageMainActivity.class);
				myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						| Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivityForResult(myIntent, 0);
			}
		});
	}

	private void initOnlineMessage() {
		onlineMessage = (Button) findViewById(R.id.main_button_OnlineMessage);
		onlineMessage.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				new AsyncGetOnlineMessage().execute();

			}
		});
	}

	private void initSearchUsers(final SharedPreferences sp) {
		searchUsers = (Button) findViewById(R.id.main_button_searchUsers);
		searchUsers.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(),
						UserSearch.class);
				myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						| Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivityForResult(myIntent, 0);
			}
		});
	}

	private void initProfile(final SharedPreferences sp) {
		profile = (Button) findViewById(R.id.main_button_Profile);
		profile.setOnClickListener(new OnClickListener() {

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
	}

	private void initRegister() {
		register = (Button) findViewById(R.id.main_button_Register);
		register.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(),
						UserRegister.class);
				myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						| Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivityForResult(myIntent, 0);
			}
		});
	}

	private void initLogin() {
		login = (Button) findViewById(R.id.main_button_Login);
		login.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(), UserLogin.class);
				myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						| Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivityForResult(myIntent, 0);

			}
		});
	}

	private void initGetOnlineMessage() {

		MessageController controller = new MessageController();
		try {
			list = controller.ShowOnlineMessage(lastUpdate);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (HabehException e) {

		}

	}

	private void initGetUserInformation(final TbMessage temp) {
		UserController controller = new UserController();
		try {
			Result = controller.getProfile(temp.getUserId());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (HabehException e) {
			e.printStackTrace();
		}

		userNameText = Result.getUserName();
	}

	private void initCountNewMessage() {
		int count = 0;
		if (ConnectedToInternet.isOnline(getBaseContext())) {
			MessageController controller = new MessageController();
			db.close();
			try {
				count = controller.CountNewMessage(lastUpdate);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			} catch (HabehException e) {
				Toast.makeText(getBaseContext(), e.getMessage(),
						Toast.LENGTH_LONG).show();
			}
			onlineMessage.setText("You Have  " + count + "  New Message");

		} else {
			Toast.makeText(getBaseContext(),
					getResources().getString(R.string.ConnectToInternet),
					Toast.LENGTH_LONG).show();
		}

	}


	private class AsyncCountNewMessage extends AsyncTask<String, Void, Integer> {
		ProgressDialog pdLoading = new ProgressDialog(MainActivity.this);

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pdLoading.setMessage("Loading...");
			pdLoading.show();
		}

		@Override
		protected Integer doInBackground(String... params) {
			int count = 0;
			MessageController controller = new MessageController();
			db.close();
			try {
				count = controller.CountNewMessage(lastUpdate);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			} catch (HabehException e) {
				Toast.makeText(getBaseContext(), e.getMessage(),
						Toast.LENGTH_LONG).show();
			}

			return count;
		}

		protected void onPostExecute(Integer result) {
			super.onPostExecute(result);
			onlineMessage.setText("You Have  " + result.toString()
					+ "  New Message");
			pdLoading.dismiss();
		}

	}

	private class AsyncGetOnlineMessage extends
			AsyncTask<String, Void, Integer> {
		ProgressDialog pdLoading = new ProgressDialog(MainActivity.this);

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pdLoading.setMessage(getResources().getString(
					R.string.GetNewMessage));
			pdLoading.show();
		}

		@Override
		protected Integer doInBackground(String... params) {
			db.open();
			int i = 0;
			for (; i < list.size(); i++) {
				TbMessage temp = list.get(i);
				initGetUserInformation(temp);
				try {
					db.insertTbMessage(temp.getId(), temp.getUserId(),
							userNameText, temp.getCategoryTitle(),
							temp.getCategoryId(), temp.getDescription(),
							temp.getShare(), temp.getSendDate());
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			lastUpdate = db.getLastUpdate();
			db.close();
			return null;
		}

		protected void onPostExecute(Integer result) {
			super.onPostExecute(result);
			initCountNewMessage();
			Toast.makeText(getBaseContext(), "Download Complete",
					Toast.LENGTH_LONG).show();
			pdLoading.dismiss();
		}

	}

}
