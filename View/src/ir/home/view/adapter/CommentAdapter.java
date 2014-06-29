package ir.home.view.adapter;

import ir.home.controller.UserController;
import ir.home.habbeh.R;
import ir.home.model.TbComment;
import ir.home.model.TbUser;
import ir.home.utility.HabehException;

import java.io.IOException;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CommentAdapter extends BaseAdapter {

	private Activity activity;
	private List<TbComment> data;
	private static LayoutInflater inflater = null;
	private TbUser Result;
	private TextView userName;
	private TextView userComment;

	public CommentAdapter(Activity a, List<TbComment> students) {

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
		return data.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View vi = convertView;

		if (convertView == null)
			vi = inflater.inflate(R.layout.comment_item, null);

		userName = (TextView) vi
				.findViewById(R.id.comment_item_textview_userName);
		userComment = (TextView) vi
				.findViewById(R.id.comment_item_textview_comment);

		if (data.size() <= 0) {
			userName.setText("No Data");
			userComment.setText("No Data");
		} else {
			TbComment temp = (TbComment) data.get(position);
			userComment.setText(temp.getDescription());

			initGetUserInformation(temp);
			
		}
		return vi;
	}

	public void setData(List<TbComment> data) {
		this.data = data;
	}

	
	private void initGetUserInformation(final TbComment temp) {

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
		userName.setText("Send Comment By :" + Result.getUserName().toString());

	}
}