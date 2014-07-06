package ir.home.view;

import ir.home.controller.UserFriendController;
import ir.home.habbeh.R;
import ir.home.model.TbUserFriend;
import ir.home.utility.HabehException;
import ir.home.view.adapter.FriendAdapter;

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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class UserFriendList extends Activity {

	private EditText userSearch;
	private Button search;
	private ListView userListView;
	private FriendAdapter adapter;
	private int userId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userfriendlist);

		final SharedPreferences sp = this.getSharedPreferences(
				"UserInformation", MODE_PRIVATE);
		userId = Integer.parseInt(sp.getString("UserId", "0"));
		userSearch = (EditText) findViewById(R.id.userfriendlist_edittext_userName);
		userListView = (ListView) findViewById(R.id.userfriendlist_list_userlistView);
		adapter = new FriendAdapter(this, new ArrayList<TbUserFriend>());
		userListView.setAdapter(adapter);

		initFriendUserSearch();

	}

	private void initFriendUserSearch() {
		search = (Button) findViewById(R.id.userfriendlist_button_userSearch);
		search.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				UserFriendController controller = new UserFriendController();
				try {
					List<TbUserFriend> result = controller.FriendList(userId);

					adapter.setData(result);
					adapter.notifyDataSetChanged();

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

	public void onBackPressed() {
		Intent myIntent = new Intent(UserFriendList.this, MainActivity.class);
		myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
				| Intent.FLAG_ACTIVITY_CLEAR_TASK);
		startActivityForResult(myIntent, 0);
		super.onBackPressed();
	}
}
