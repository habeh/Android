package ir.home.view;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import ir.home.controller.UserController;
import ir.home.habbeh.R;
import ir.home.model.TbUser;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserSearch extends Activity {

	private EditText Usersearch;
	private Button Search;
	private TbUser Result;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.usersearch);
		
		Usersearch=(EditText)findViewById(R.id.Usersearch);
		Search=(Button)findViewById(R.id.search);
		Search.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				
				UserController controller=new UserController();
				try {
			Result=	controller.Search(Usersearch.getText().toString());

				 }catch (IOException e) {
						e.printStackTrace();
					  } catch (XmlPullParserException e) {
						e.printStackTrace();
					  }
				//Toast.makeText(getBaseContext(), Result.getEmail().toString(), Toast.LENGTH_SHORT).show();
	}
		});

}
	
}
