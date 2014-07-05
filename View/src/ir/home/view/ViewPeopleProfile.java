package ir.home.view;

import ir.home.controller.MessageController;
import ir.home.controller.UserController;
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
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewPeopleProfile extends Activity {

	private TextView userName;
	private TextView name;
	private TextView status;
	private TbUser currentUser;
	private List<TbMessage> userMessage;
	private ListView usermessageListView;
	private MessageAdapter adapter;
	private int userId;
	List<TbMessage> getofflineMessage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpeopleprofile);

		userName = (TextView) findViewById(R.id.viewpeopleprofile_textview_userName);
		name = (TextView) findViewById(R.id.viewpeopleprofile_textview_name);
		status = (TextView) findViewById(R.id.viewpeopleprofile_textview_status);
		userId = Integer.parseInt(getIntent().getStringExtra("userId"));
		usermessageListView = (ListView) findViewById(R.id.viewpeopleprofile_list_messagelistView);
		adapter = new MessageAdapter(this, new ArrayList<TbMessage>());
		usermessageListView.setAdapter(adapter);
		
		initGetUserInformation();
		
		initReadUserMessage();

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

	@Override
	public void onBackPressed() {
		Intent myIntent = new Intent(ViewPeopleProfile.this, OfflineTextMessage.class);
		myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
				| Intent.FLAG_ACTIVITY_CLEAR_TASK);
		startActivityForResult(myIntent, 0);
		super.onBackPressed();
	}
}
