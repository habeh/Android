package ir.home.view.adapter;

import ir.home.controller.UserFriendController;
import ir.home.habbeh.R;
import ir.home.model.TbUserFriend;
import ir.home.utility.HabehException;

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

public class FriendAdapter extends BaseAdapter {

	private Button accept;
	private Button reject;
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
			vi = inflater.inflate(R.layout.friendlist_item, null);

		TextView frienduserName = (TextView) vi
				.findViewById(R.id.userfriendlist_textview_username);
		TextView Status = (TextView) vi.findViewById(R.id.UserStatus);
		 accept = (Button) vi.findViewById(R.id.userfriendlist_button_accept);
		reject = (Button) vi.findViewById(R.id.userfriendlist_button_reject);
		accept.setVisibility(View.GONE);
		reject.setVisibility(View.GONE);
		

		if (data.size() <= 0) {
			frienduserName.setText("No Data");
			Status.setText("No Data");
		} else {
			TbUserFriend temp = (TbUserFriend) data.get(position);
			if (temp.getAccept().equals("false")) {
				accept.setVisibility(View.VISIBLE);
				reject.setVisibility(View.VISIBLE);
				frienduserName.setEnabled(false);
				frienduserName.setText(temp.getFriendUserName());
			} else {
				frienduserName.setText(temp.getFriendUserName() + " √ ");
			}

			Status.setText(temp.getStatus());
			
			initAccept(temp);
			
			initReject(temp);

		}
		return vi;
	}

	public void setData(List<TbUserFriend> result) {
		this.data = result;
	}
	
	
    private void initAccept(final TbUserFriend temp) {

        accept.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {

            	UserFriendController acceptController = new UserFriendController();
                 try {
                      acceptController.FriendAcceptRequest(temp.getId());
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
    
    private void initReject(final TbUserFriend temp) {

        reject.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {

            	UserFriendController acceptController = new UserFriendController();
                 try {
                      acceptController.FriendRejectRequest(temp.getId());
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
}