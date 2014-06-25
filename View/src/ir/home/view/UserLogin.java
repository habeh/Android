
package ir.home.view;

import ir.home.controller.UserController;
import ir.home.habbeh.R;
import ir.home.model.TbUser;
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
import android.widget.TextView;
import android.widget.Toast;

public class UserLogin extends Activity {

    private EditText userName;
    private EditText userPassword;
    private Button login;
    private TextView forgivePassword;
    private TextView registerTextView;
    private TbUser result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlogin);

        userName = (EditText) findViewById(R.id.userlogin_edittext_UserName);
        userPassword = (EditText) findViewById(R.id.userlogin_edittext_UserPassword);

        initLogin();

        initForgivePassword();

        initRegisterTextView();
    }

    private void initRegisterTextView() {
        registerTextView = (TextView) findViewById(R.id.userlogin_textview_registerTextView);
        registerTextView.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {

                Intent myIntent = new Intent(arg0.getContext(),
                        UserRegister.class);
                startActivityForResult(myIntent, 0);
                finish();
            }
        });
    }

    private void initForgivePassword() {
        forgivePassword = (TextView) findViewById(R.id.userlogin_textview_Forgiv);
        forgivePassword.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {

                Intent myIntent = new Intent(arg0.getContext(),
                        UserForgiveInformation.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }

    private void initLogin() {
        login = (Button) findViewById(R.id.userlogin_button_Login);
        login.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {

                String userNameText = userName.getText().toString();
                String userPasswordText = userPassword.getText().toString();
                String error = "";

                if (userNameText.isEmpty()) {
                    error = "You did not enter a UserName";
                }

                if (userPasswordText.isEmpty()) {
                    error += "\nYou did not enter a Password";
                }

                if (error.isEmpty()) {
                    if (ConnectedToInternet.isOnline(getBaseContext())) {
                        UserController controller = new UserController();
                        try {
                            result = controller.login(userNameText, userPasswordText);

                            if (result == null) {
                                Toast.makeText(getBaseContext(), "Login Failed",
                                        Toast.LENGTH_LONG).show();

                            } else {

                                SavePrefs("UserName", result.getUserName().toString());
                                SavePrefs("UserId", Integer.toString(result.getId()));
                                Toast.makeText(getBaseContext(),
                                        "You Login Successfully In Habbeh",
                                        Toast.LENGTH_LONG).show();

                                // Go To Next Page
                                Intent myIntent = new Intent(getBaseContext(),
                                        OfflineTextMessage.class);
                                startActivityForResult(myIntent, 0);

                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (XmlPullParserException e) {
                            e.printStackTrace();
                        }
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
}
