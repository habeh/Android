package ir.home.view.adapter;

import ir.home.habbeh.R;
import ir.home.model.TbUser;


import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class UserAdapter extends BaseAdapter {

	private Activity activity;
	private List<TbUser> data;
	private static LayoutInflater inflater = null;


	public UserAdapter(Activity a, List<TbUser> students) {

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
		return  data.get(position);
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

		TextView text = (TextView) vi.findViewById(R.id.userItemUserNameTextView);
		TextView Status=(TextView)vi.findViewById(R.id.UserStatus );

		if (data.size() <= 0) {
			text.setText("No Data");
			Status.setText("No Data");
		} else {
			TbUser temp = (TbUser) data.get(position);

			text.setText(temp.getUserName());
			Status.setText(temp.getStatus());

		}
		return vi;
	}

	public void setData(List<TbUser> data) {
		this.data = data;
	}
}