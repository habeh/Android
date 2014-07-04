package ir.home.view;

import ir.home.habbeh.R;
import ir.home.model.TbMessage;
import ir.home.view.adapter.MessageAdapter;
import ir.home.view.database.DBAdapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class OfflineTextMessage extends Activity {

	private ListView messageListView;
	private MessageAdapter adapter;
	DBAdapter db;
	List<TbMessage> getofflineMessage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.offlinemssage);
		
		db = new DBAdapter(this);
		db.open();
		getofflineMessage = db.getAllSaveMessage(DBAdapter.DATABASE_TBMESSAGE);
		db.close();

		messageListView = (ListView) findViewById(R.id.messagelistView);
		adapter = new MessageAdapter(this, new ArrayList<TbMessage>());
		messageListView.setAdapter(adapter);
		adapter.setData(getofflineMessage);
		adapter.notifyDataSetChanged();

	}
}
