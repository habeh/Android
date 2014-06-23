package ir.home.view.adapter;

import ir.home.controller.MessageController;
import ir.home.controller.UserController;
import ir.home.habbeh.R;
import ir.home.model.TbMessage;
import ir.home.model.TbUser;

import java.io.IOException;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class MessageAdapter extends BaseAdapter {
	private TbUser Result;
	private Activity activity;
	private List<TbMessage> data;
	private static LayoutInflater inflater = null;

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
		return data.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View vi = convertView;

		if (convertView == null)
			vi = inflater.inflate(R.layout.message_item, null);

		TextView Message = (TextView) vi.findViewById(R.id.Message);
		TextView UserName = (TextView) vi.findViewById(R.id.Username);
		TextView Category = (TextView) vi.findViewById(R.id.Category);
		TextView ShowLike = (TextView) vi.findViewById(R.id.ShowLike);
		Button Like = (Button) vi.findViewById(R.id.Like);

		if (data.size() <= 0) {
			Message.setText("No Data");
			UserName.setText("No Data");
			Category.setText("No Data");
			ShowLike.setText("No Data");
		} else {
			final TbMessage temp = (TbMessage) data.get(position);
			Message.setText(temp.getDescription());
			Category.setText("Category :" + temp.getCategoryTitle());

			// get UseName By Pass UserId To WebService

			UserController controller = new UserController();
			try {
				Result = controller.getProfile(temp.getUserId());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}
			UserName.setText("Send By :" + Result.getUserName().toString());

			// Setlike for this message
			Like.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {

					MessageController controller = new MessageController();
					try {
						controller.LikeMessage(temp.getUserId(), temp.getId());
					} catch (IOException e) {
						e.printStackTrace();
					} catch (XmlPullParserException e) {
						e.printStackTrace();
					}
				}
			});

			// ShowLike Count For This Message
			MessageController controllerlike = new MessageController();
			int count = 0;
			try {
				count = controllerlike.CountLike(temp.getId());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}
			ShowLike.setText("Liked : " + Integer.toString(count));

		}
		return vi;
	}

	public void setData(List<TbMessage> data) {
		this.data = data;
	}
}