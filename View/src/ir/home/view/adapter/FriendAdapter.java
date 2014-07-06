package ir.home.view.adapter;

import ir.home.habbeh.R;
import ir.home.model.TbUserFriend;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FriendAdapter extends BaseAdapter {

	private Activity activity;
	private List<TbUserFriend> data;
	private static LayoutInflater inflater = null;

	public FriendAdapter(Activity a, List<TbUserFriend> students) {

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
			vi = inflater.inflate(R.layout.user_item, null);

		TextView frienduserName = (TextView) vi
				.findViewById(R.id.userItemUserNameTextView);
		TextView Status = (TextView) vi.findViewById(R.id.UserStatus);
		String State = null;

		if (data.size() <= 0) {
			frienduserName.setText("No Data");
			Status.setText("No Data");
		} else {
			TbUserFriend temp = (TbUserFriend) data.get(position);
			if (temp.getAccept().equals("false")) {
				State = "( Wating For Accept Request )";
				frienduserName.setEnabled(false);
				frienduserName.setText(temp.getFriendUserName() + State);
			} else {
				frienduserName.setText(temp.getFriendUserName());
			}

			Status.setText(temp.getStatus());

		}
		return vi;
	}

	public void setData(List<TbUserFriend> result) {
		this.data = result;
	}
}