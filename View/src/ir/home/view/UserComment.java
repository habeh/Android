package ir.home.view;

import ir.home.controller.CommentController;
import ir.home.habbeh.R;
import ir.home.model.TbComment;
import ir.home.utility.HabehException;
import ir.home.view.adapter.CommentAdapter;

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
import android.widget.TextView;
import android.widget.Toast;

public class UserComment extends Activity {
	private ListView commentListView;
	private TextView message;
	private TextView userName;
	private TextView likeMessage;
	private CommentAdapter adapter;
	private Button CreatComment;
	private int messageId;
	private EditText comment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.usercomment);

		commentListView = (ListView) findViewById(R.id.usercomment_List_commentListView);
		adapter = new CommentAdapter(this, new ArrayList<TbComment>());
		commentListView.setAdapter(adapter);

		initGetData();

		initRetrieveComment();

		initCreatComment();

	}

	private void initCreatComment() {
		final SharedPreferences sp = this.getSharedPreferences(
				"UserInformation", MODE_PRIVATE);
		final int userId = Integer.parseInt(sp.getString("UserId", "0"));
		CreatComment = (Button) findViewById(R.id.usercomment_button_sendComment);
		CreatComment.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				CommentController controller = new CommentController();
				try {
					controller.Create(userId, messageId, comment.getText()
							.toString());
				} catch (IOException e) {
					e.printStackTrace();
				} catch (XmlPullParserException e) {
					e.printStackTrace();
				} catch (HabehException e) {
                    Toast.makeText(getBaseContext(),
                            e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

			}
		});
	}

	private void initRetrieveComment() {
		CommentController controller = new CommentController();
		try {
			int messageId = Integer.parseInt(getIntent().getStringExtra(
					"messageId"));
			List<TbComment> result = controller.RetrieveComment(messageId);

			adapter.setData(result);
			adapter.notifyDataSetChanged();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (HabehException e) {
            Toast.makeText(getBaseContext(),
                    e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
	}

	private void initGetData() {
		comment = (EditText) findViewById(R.id.usercomment_edittext_Description);
		message = (TextView) findViewById(R.id.usercomment_textview_Description);
		userName = (TextView) findViewById(R.id.usercomment_textview_userName);
		likeMessage = (TextView) findViewById(R.id.usercomment_textview_likeMessage);
		message.setText(getIntent().getStringExtra("Description"));
		userName.setText(getIntent().getStringExtra("userName"));
		likeMessage.setText(getIntent().getStringExtra("likeMessage"));
		messageId = Integer.parseInt(getIntent().getStringExtra("messageId"));
	}
	
	public void onBackPressed() {
        Intent myIntent = new Intent(UserComment.this, OfflineTextMessage.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivityForResult(myIntent, 0);
        super.onBackPressed();
    }

}
