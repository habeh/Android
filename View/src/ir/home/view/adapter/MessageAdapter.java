package ir.home.view.adapter;

import ir.home.controller.MessageController;
import ir.home.habbeh.R;
import ir.home.model.TbMessage;
import ir.home.utility.HabehException;
import ir.home.view.UserComment;

import java.io.IOException;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class MessageAdapter extends BaseAdapter {

	private Activity activity;
	private List<TbMessage> data;
	private static LayoutInflater inflater = null;
	private Button setlikeMessage;
	private Button commentMessage;
	private TextView showlikeMessage;
	private TextView userName;
	private String userNameText;
	private String likeMessageText;
	private TextView message;
	private TextView categoryTitle;

	public MessageAdapter(Activity a, List<TbMessage> students) {

		activity = a;
		data = students;

		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public int getCount() {

		if (data.size() <= 0)
			return 1;
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		if (data.size() > 0) {
			return data.get(position).getId();
		}
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View vi = convertView;

		if (convertView == null)
			vi = inflater.inflate(R.layout.message_item, null);

		message = (TextView) vi.findViewById(R.id.Message);
		userName = (TextView) vi.findViewById(R.id.Username);
		categoryTitle = (TextView) vi.findViewById(R.id.Category);
		showlikeMessage = (TextView) vi.findViewById(R.id.ShowLike);
		setlikeMessage = (Button) vi.findViewById(R.id.Like);
		commentMessage = (Button) vi
				.findViewById(R.id.message_item_button_comment);

		if (data.size() > 0) {
			TbMessage temp = (TbMessage) data.get(position);

			message.setText(temp.getDescription());
			categoryTitle.setText("Category :" + temp.getCategoryTitle());
			userName.setText("Send By :" + temp.getUserName());

			initLikeMessage(temp);

			initUserComment(temp);

			initGetLikeCount(temp);

		}

		return vi;
	}

	public void setData(List<TbMessage> data) {
		this.data = data;
	}

	private void initLikeMessage(final TbMessage temp) {
		final int userId = GetSharedPreferences(activity);
		setlikeMessage.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				MessageController controller = new MessageController();
				try {
					controller.LikeMessage(userId, temp.getId());
				} catch (IOException e) {
					e.printStackTrace();
				} catch (XmlPullParserException e) {
					e.printStackTrace();
				} catch (HabehException e) {
					e.printStackTrace();
				}

			}
		});
	}

	private void initUserComment(final TbMessage temp) {
		commentMessage.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				Intent myIntent = new Intent(arg0.getContext(),
						UserComment.class);
				myIntent.putExtra("messageId", Integer.toString(temp.getId()));
				myIntent.putExtra("userId", Integer.toString(temp.getUserId()));
				myIntent.putExtra("userName", userNameText);
				myIntent.putExtra("Description", temp.getDescription());
				myIntent.putExtra("categoryTitle", temp.getCategoryTitle());
				myIntent.putExtra("likeMessage", likeMessageText);
				arg0.getContext().startActivity(myIntent);

			}
		});
	}

	private void initGetLikeCount(TbMessage temp) {

		MessageController controllerlike = new MessageController();
		int count = 0;
		try {
			count = controllerlike.CountLike(temp.getId());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (HabehException e) {
			e.printStackTrace();
		}
		showlikeMessage.setText("Liked : " + Integer.toString(count));
		likeMessageText = Integer.toString(count);
	}

	public int GetSharedPreferences(Context context) {
		SharedPreferences sp = context.getSharedPreferences("UserInformation",
				Context.MODE_PRIVATE);
		int userId = Integer.parseInt(sp.getString("UserId", "0"));
		return userId;

	}

}
