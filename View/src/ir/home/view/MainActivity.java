package ir.home.view;

import ir.home.habbeh.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
   
	private Button Login;
	private Button Register;
	private Button OnlineMessage;
	private Button OfflineMessage;
	private Button Findpeople;
	private Button UsContact;
	private Button UsAbout;
	private Button HabbehAbout;

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);  
        
    Login=(Button)findViewById(R.id.Login);
      Login.setOnClickListener(new OnClickListener() {
        	
        public void onClick(View view){
			Intent myIntent = new Intent(view.getContext(), UserLogin.class);
            startActivityForResult(myIntent, 0);	
        }
      }
    );
        
     
      Register=(Button)findViewById(R.id.Register);
      Register.setOnClickListener(new OnClickListener() {
        	
        public void onClick(View view){
			Intent myIntent = new Intent(view.getContext(), UserRegister.class);
            startActivityForResult(myIntent, 0);	
        }
      }
    );
      
      
      OnlineMessage=(Button)findViewById(R.id.OnlineMessage);
      OnlineMessage.setOnClickListener(new OnClickListener() {
        	
        public void onClick(View view){
			Intent myIntent = new Intent(view.getContext(), OnlineTextMessage.class);
            startActivityForResult(myIntent, 0);	
        }
      }
    );
      
      
      OfflineMessage=(Button)findViewById(R.id.OfflineMessage);
      OfflineMessage.setOnClickListener(new OnClickListener() {
        	
        public void onClick(View view){
			Intent myIntent = new Intent(view.getContext(), OfflineTextMessage.class);
            startActivityForResult(myIntent, 0);	
        }
      }
    );
      
      
      Findpeople=(Button)findViewById(R.id.Findpeople);
      Findpeople.setOnClickListener(new OnClickListener() {
        	
        public void onClick(View view){
			Intent myIntent = new Intent(view.getContext(), Findpeople.class);
            startActivityForResult(myIntent, 0);	
        }
      }
    );
      
      
      UsContact=(Button)findViewById(R.id.UsContact);
      UsContact.setOnClickListener(new OnClickListener() {
        	
        public void onClick(View view){
			Intent myIntent = new Intent(view.getContext(), UsContact.class);
            startActivityForResult(myIntent, 0);	
        }
      }
    );
      
      
      UsAbout=(Button)findViewById(R.id.UsAbout);
      UsAbout.setOnClickListener(new OnClickListener() {
        	
        public void onClick(View view){
			Intent myIntent = new Intent(view.getContext(), UsAbout.class);
            startActivityForResult(myIntent, 0);	
        }
      }
    );
      
      
      HabbehAbout=(Button)findViewById(R.id.HabbehAbout);
      HabbehAbout.setOnClickListener(new OnClickListener() {
        	
        public void onClick(View view){
			Intent myIntent = new Intent(view.getContext(), HabbehAbout.class);
            startActivityForResult(myIntent, 0);	
        }
      }
    );
        
    }
}