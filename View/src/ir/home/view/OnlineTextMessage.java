package ir.home.view;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import ir.home.model.TbMessage;
import org.xmlpull.v1.XmlPullParserException;

import ir.home.controller.MessageController;
import ir.home.habbeh.R;
import android.app.Activity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class OnlineTextMessage extends Activity {

	private TbMessage Result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.onlinemessage);
		
		Time now = new Time();
		now.setToNow();

		MessageController controller=new MessageController();
		try {
			Result=controller.NewMessageCount(now.format("%m/%d/%Y %H:%M:%S").toString());
	} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}
		Toast.makeText(getBaseContext(), 
				Integer.toString(Result.getnewMessageCount()).toString(),
				Toast.LENGTH_LONG).show();
	//	textview1.setText(Integer.toString(Result.getnewMessageCount()));
	}

}
