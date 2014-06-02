package ir.home.view;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import ir.home.controller.UserController;
import ir.home.habbeh.R;
import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);  
        
        UserController controller=new UserController();
        try {
			controller.register("test", "test@yahoo.com", "123");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}
        
    }
}