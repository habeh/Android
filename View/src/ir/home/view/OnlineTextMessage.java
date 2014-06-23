package ir.home.view;

import ir.home.controller.MessageController;
import ir.home.habbeh.R;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;
import android.app.Activity;
import android.os.Bundle;
import android.text.format.Time;
import android.widget.Toast;

public class OnlineTextMessage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.onlinemessage);

		Time now = new Time();
		now.setToNow();
		MessageController controller = new MessageController();
		int count = 0;
		try {
			count = controller.CountNewMessage(now.format("%m/%d/%Y %H:%M:%S")
					.toString());

			count = controller.CountNewMessage("2014-01-01");

		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}
		Toast.makeText(getBaseContext(), Integer.toString(count).toString(),
				Toast.LENGTH_LONG).show();
		// textview1.setText(Integer.toString(Result.getnewMessageCount()));
	}

}
