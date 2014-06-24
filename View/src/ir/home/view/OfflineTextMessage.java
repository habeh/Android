package ir.home.view;

import ir.home.controller.MessageController;
import ir.home.habbeh.R;
import ir.home.model.TbMessage;
import ir.home.view.adapter.MessageAdapter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class OfflineTextMessage extends Activity {

	private EditText Date;
	private Button Show;
	private ListView messageListView;
	private MessageAdapter adapter;
	private String convertedDate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.offlinemssage);

		Date = (EditText) findViewById(R.id.Date);
		Show = (Button) findViewById(R.id.Show);
		messageListView = (ListView) findViewById(R.id.messagelistView);
		adapter = new MessageAdapter(this, new ArrayList<TbMessage>());
		messageListView.setAdapter(adapter);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		try {
			Date d = sdf.parse("2014/06/20 14:50:42");
			convertedDate = sdf.format(d);
			Date.setText(convertedDate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		Show.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				MessageController controller = new MessageController();
				try {
					List<TbMessage> result = controller.ShowOnlineMessage(Date
							.getEditableText().toString());

					adapter.setData(result);
					adapter.notifyDataSetChanged();

				} catch (IOException e) {
					e.printStackTrace();
				} catch (XmlPullParserException e) {
					e.printStackTrace();
				}
			}
		});

	}

	@Override
	public void onBackPressed() {
		Intent myIntent = new Intent(OfflineTextMessage.this,
				MainActivity.class);
		startActivityForResult(myIntent, 0);
		super.onBackPressed();
	}

}
