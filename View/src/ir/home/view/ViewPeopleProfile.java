package ir.home.view;

import ir.home.controller.CommentController;
import ir.home.controller.MessageController;
import ir.home.controller.UserController;
import ir.home.controller.UserFriendController;
import ir.home.habbeh.R;
import ir.home.model.TbMessage;
import ir.home.model.TbUser;
import ir.home.utility.HabehException;
import ir.home.view.adapter.MessageAdapter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewPeopleProfile extends Activity {

	private TextView userName;
	private TextView name;
	private TextView status;
	private TbUser currentUser;
	private Button sendMessage;
	private Button friendRequest;
	private Button reportOffending;
	private List<TbMessage> userMessage;
	private ListView usermessageListView;
	private MessageAdapter adapter;
	private int userId;
	private int requester;
	private int friendId;
	List<TbMessage> getofflineMessage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpeopleprofile);

		final SharedPreferences sp = this.getSharedPreferences(
				"UserInformation", MODE_PRIVATE);
		requester = Integer.parseInt(sp.getString("UserId", "0"));

		userName = (TextView) findViewById(R.id.viewpeopleprofile_textview_userName);
		name = (TextView) findViewById(R.id.viewpeopleprofile_textview_name);
		status = (TextView) findViewById(R.id.viewpeopleprofile_textview_status);
		sendMessage = (Button) findViewById(R.id.viewpeopleprofile_button_sendmessage);
		friendRequest = (Button) findViewById(R.id.viewpeopleprofile_button_friendRequest);
		reportOffending = (Button) findViewById(R.id.viewpeopleprofile_button_reportoffending);
		userId = Integer.parseInt(getIntent().getStringExtra("userId"));
		usermessageListView = (ListView) findViewById(R.id.viewpeopleprofile_list_messagelistView);
		adapter = new MessageAdapter(this, new ArrayList<TbMessage>());
		usermessageListView.setAdapter(adapter);

		initGetUserInformation();

		initReadUserMessage();

		initFriendSendRequest();

		initCheckhasFriend();

		initCreatOffendingComment();

	}

	private void initGetUserInformation() {

		UserController controller = new UserController();
		try {
			currentUser = controller.getProfile(userId);

			if (currentUser != null) {
				userName.setText(currentUser.getUserName());
				name.setText(currentUser.getFirstName() + " "
						+ currentUser.getLastName());
				status.setText(currentUser.getStatus());
				friendId = currentUser.getId();

			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (HabehException e) {
			Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG)
					.show();
		}
	}

	private void initReadUserMessage() {

		MessageController controller = new MessageController();
		try {
			userMessage = controller.ReadUserMessage(userId);
			adapter.setData(userMessage);
			adapter.notifyDataSetChanged();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (HabehException e) {
			Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG)
					.show();
		}
	}

	private void initFriendSendRequest() {
		String state = initCheckhasFriend();

		if (requester == friendId) {
			friendRequest.setVisibility(View.GONE);
			reportOffending.setVisibility(View.GONE);
			sendMessage.setVisibility(View.GONE);
		}

		if (state.equals("true")) {
			friendRequest.setText("√ Friend");
			friendRequest.setEnabled(false);
		}
		if (state.equals("pending")) {
			friendRequest.setText("Wating Accept");
			friendRequest.setEnabled(false);
		}

		friendRequest.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {

				UserFriendController send = new UserFriendController();
				try {
					send.FriendSendRequest(requester, friendId);

				} catch (IOException e) {
					e.printStackTrace();
				} catch (XmlPullParserException e) {
					e.printStackTrace();
				} catch (HabehException e) {
					Toast.makeText(getBaseContext(), e.getMessage(),
							Toast.LENGTH_LONG).show();
				}

			}
		});

	}

	private void initCreatOffendingComment() {

		reportOffending.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				CommentController controller = new CommentController();
				try {
					controller.Create(userId, requester, 0, "Offending Report",
							2);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (XmlPullParserException e) {
					e.printStackTrace();
				} catch (HabehException e) {
					Toast.makeText(getBaseContext(), e.getMessage(),
							Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	private String initCheckhasFriend() {
		String resultcheck = null;
		UserFriendController check = new UserFriendController();
		try {

			check.CheckhasFriend(requester, friendId);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (HabehException e) {
			resultcheck = e.getMessage();

		}
		return resultcheck;
	}

	@Override
	public void onBackPressed() {
		Intent myIntent = new Intent(ViewPeopleProfile.this,
				OfflineTextMessage.class);
		myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
				| Intent.FLAG_ACTIVITY_CLEAR_TASK);
		startActivityForResult(myIntent, 0);
		super.onBackPressed();
	}
}
