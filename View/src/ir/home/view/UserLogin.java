package ir.home.view;

import ir.home.controller.UserController;
import ir.home.habbeh.R;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserLogin extends Activity {
public static EditText  UserName;
public static EditText UserPassword;
public static Button Login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlogin);

        	UserName=(EditText)findViewById(R.id.UserName);
        	UserPassword=(EditText)findViewById(R.id.UserPassword );
        	Login=(Button)findViewById(R.id.Login );
        	
        			Login.setOnClickListener(new OnClickListener(){
        				
        				public void onClick(View arg0) {
					
        					
					UserController controller=new UserController();
					try {
						controller.login(UserName.getText().toString(), UserPassword.getText().toString());
					} catch (IOException e) {
						e.printStackTrace();
					} catch (XmlPullParserException e) {
						e.printStackTrace();
					}
					
        				}
        			}
        	);
    }

}
