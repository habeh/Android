package ir.home.view;

import ir.home.habbeh.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class UsAbout extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.usabout);

	}
	public void onBackPressed() {
        Intent myIntent = new Intent(UsAbout.this, MainActivity.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivityForResult(myIntent, 0);
        super.onBackPressed();
    }
}
