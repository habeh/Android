package ir.home.view;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.xmlpull.v1.XmlPullParserException;

import ir.home.controller.UserController;
import ir.home.habbeh.R;
import ir.home.model.TbUser;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserProfile extends Activity {

	
 private TbUser Result;
 private TextView UserId;
 private TextView UserName;
 private TextView FirstName;
 private TextView LastName;
 private TextView Picture;
 private TextView UserEmail;
 private TextView PhoneNo;
 private TextView Location;
 private TextView UserRegisterDate;
 
 
 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userprofile);
		
		
		UserName=(TextView)findViewById(R.id.UserProfile);
		FirstName=(TextView)findViewById(R.id.FirstName);
		LastName=(TextView)findViewById(R.id.LastName);
		PhoneNo=(TextView)findViewById(R.id.PhoneNo);
		Location=(TextView)findViewById(R.id.Location);
		UserEmail=(TextView)findViewById(R.id.UserEmail);
		UserRegisterDate=(TextView)findViewById(R.id.UserRegisterDate);
		UserId=(TextView)findViewById(R.id.UserId);
	
		
		
		
		final  SharedPreferences sp = this.getSharedPreferences("UserName", MODE_PRIVATE);
        final String UserNameP = sp.getString("UserNameC", "");
		UserController controller=new UserController();
		try {
			  Result=controller.getProfile(UserNameP.toString());
		  }catch (IOException e) {
			e.printStackTrace();
		  } catch (XmlPullParserException e) {
			e.printStackTrace();
		  }
		
		
		UserName.setText(Result.getUserName().toString());
		FirstName.setText(Result.getFirstName().toString());
		LastName.setText(Result.getLastName().toString());
		UserEmail.setText(Result.getEmail().toString());
		PhoneNo.setText(Result.getPhoneNo().toString());
		Location.setText(Result.getLocation().toString());
		
		
		
	
		
		
	}
	

}

