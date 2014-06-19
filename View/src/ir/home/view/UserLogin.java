package ir.home.view;

import ir.home.controller.UserController;
import ir.home.habbeh.R;
import ir.home.model.TbUser;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserLogin extends Activity {

	private EditText UserName;
	private EditText UserPassword;
	private Button Login;
	private TbUser Result;
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userlogin);
		
		UserName = (EditText) findViewById(R.id.UserName);
		UserPassword = (EditText) findViewById(R.id.UserPassword);
		Login = (Button) findViewById(R.id.Login);
		
		Login.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				   
		            	UserController controller = new UserController();
						  try {
							  Result=controller.login(UserName.getText().toString(),
									  UserPassword.getText().toString());
							
						  }catch (IOException e) {
							e.printStackTrace();
						  } catch (XmlPullParserException e) {
							e.printStackTrace();
						  }
						  
							
						  if ( Result == null){
								 Toast.makeText(getBaseContext(), 
											"ظ†ط§ظ… ع©ط§ط±ط¨ط±غŒ غŒط§ ط±ظ…ط² ط¹ط¨ظˆط± ط§ط´طھط¨ط§ظ‡ ط§ط³طھ",
											Toast.LENGTH_LONG).show();
								  
							 } else {

								 
								 //Get And Save Data
								  SavePrefs("UserNameC",Result.getUserName().toString()); 
									 Toast.makeText(getBaseContext(), 
												Result.getEmail().toString(),
												Toast.LENGTH_LONG).show();
									 
									 
									 //Go To Next Page
									 Intent myIntent = new Intent(getBaseContext(), UserProfile.class);
							            startActivityForResult(myIntent, 0);
									 
					            }
							 
				/*note : Object Result Dar sorat vared kardan Data  Eshetbah 
						  bad az login movafaght null nemishavad */
				
    }
			
  });

}
	
	private void SavePrefs(String key, String value) {
		 SharedPreferences sp = this.getSharedPreferences("UserName", MODE_PRIVATE);
		    SharedPreferences.Editor edit =  sp.edit();
		      edit.putString(key ,value);
		        edit.commit();
		 
	}
	
	
	 
}


