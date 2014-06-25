
package ir.home.view;

import ir.home.controller.UserController;
import ir.home.habbeh.R;
import ir.home.model.TbUser;
import ir.home.utility.HabehException;
import ir.home.view.utility.ConnectedToInternet;

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

public class UserRegister extends Activity {

    private EditText userName;
    private EditText userEmail;
    private EditText userPassword;
    private Button register;
    private TbUser result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userregister);

        userName = (EditText) findViewById(R.id.userregister_edittext_userName);
        userEmail = (EditText) findViewById(R.id.userregister_edittext_userEmail);
        userPassword = (EditText) findViewById(R.id.userregister_edittext_userPassword);
        initRegister();

    }

    private void initRegister() {
        register = (Button) findViewById(R.id.userregister_button_register);

        register.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                String userNameText = userName.getText().toString();
                String userEmailText = userEmail.getText().toString();
                String userPasswordTExt = userPassword.getText().toString();
                String error = "";
                if (userNameText.isEmpty()) {
                    error = "You did not enter a UserName";
                }

                if (userEmailText.isEmpty()) {
                    error = "You did not enter a Email";
                }

                if (userPasswordTExt.isEmpty()) {
                    error = "You did not enter a Password";
                }

                if (error.isEmpty()) {
                    if (ConnectedToInternet.isOnline(getBaseContext())){
                        UserController controller = new UserController();
                        try {
                            controller.register(userNameText, userEmailText, userPasswordTExt);

                            // after register for load UserInformation And Save
                            // In
                            // Local
                            result = controller.login(userName.getText()
                                    .toString(), userPassword.getText()
                                    .toString());

                            SavePrefs("UserName", result.getUserName().toString());
                            SavePrefs("UserId", Integer.toString(result.getId()));
                            Toast.makeText(getBaseContext(),
                                    "Register Successfully", Toast.LENGTH_LONG)
                                    .show();

                            Intent myIntent = new Intent(arg0.getContext(),
                                    UserProfile.class);
                            startActivityForResult(myIntent, 0);
                            finish();

                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (XmlPullParserException e) {
                            e.printStackTrace();
                        } catch (HabehException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                    } else {
                        Toast.makeText(getBaseContext(),
                                "For Register Please Connect To Internet",
                                Toast.LENGTH_LONG).show();
                    }

                }
                else
                {
                    Toast.makeText(getBaseContext(),
                            error,
                            Toast.LENGTH_LONG).show();

                }
            }
        });
    }


    private void SavePrefs(String key, String value) {
        SharedPreferences sp = this.getSharedPreferences("UserInformation",
                MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key, value);
        edit.commit();

    }

    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(UserRegister.this, MainActivity.class);
        startActivityForResult(myIntent, 0);
        super.onBackPressed();
    }
}
