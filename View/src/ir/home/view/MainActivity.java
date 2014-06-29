
package ir.home.view;

import ir.home.controller.MessageController;
import ir.home.habbeh.R;
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
import android.widget.Toast;

public class MainActivity extends Activity {

    private Button login;
    private Button profile;
    private Button register;
    private Button onlineMessage;
    private Button offlineMessage;
    private Button findpeople;
    private Button usContact;
    private Button usAbout;
    private Button habbehAbout;
    private Button searchUsers;
    private Button sendMessage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final SharedPreferences sp = this.getSharedPreferences(
                "UserInformation", MODE_PRIVATE);
        // sp.edit().clear().commit();
        if (sp.getString("UserId", "0") == "0") {
            Intent myIntent = new Intent(MainActivity.this, UserLogin.class);
            startActivityForResult(myIntent, 0);

        }

        initLogin();

        initRegister();

        initProfile(sp);

        initSearchUsers(sp);

        initOnlineMessage();

        initOfflineMessage();

        initFindpeople(sp);

        initUsContact();

        initUsAbout();

        initHabbehAbout();

        initSendMessage(sp);

        if (ConnectedToInternet.isOnline(getBaseContext())) {
            MessageController controller = new MessageController();
            int count = 0;
            try {
                count = controller.CountNewMessage("2014-01-01");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (HabehException e) {
                Toast.makeText(getBaseContext(),
                        e.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
            onlineMessage.setText("You Have  " + count + "  New Message");
        } else {
            Toast.makeText(getBaseContext(),
                    "For Receipt New Message Please Connect To Internet",
                    Toast.LENGTH_LONG).show();
        }

    }

    private void initSendMessage(final SharedPreferences sp) {
        sendMessage = (Button) findViewById(R.id.main_button_SendMessage);
        if (sp.getString("UserId", "0") == "0") {
            sendMessage.setVisibility(View.GONE);

        }
        sendMessage.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(),
                        SendMessage.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }

    private void initHabbehAbout() {
        habbehAbout = (Button) findViewById(R.id.main_button_HabbehAbout);
        habbehAbout.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(),
                        HabbehAbout.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }

    private void initUsAbout() {
        usAbout = (Button) findViewById(R.id.main_button_UsAbout);
        usAbout.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), UsAbout.class);
                startActivityForResult(myIntent, 0);

            }
        });
    }

    private void initUsContact() {
        usContact = (Button) findViewById(R.id.main_button_UsContact);
        usContact.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), UsContact.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }

    private void initFindpeople(final SharedPreferences sp) {
        findpeople = (Button) findViewById(R.id.main_button_Findpeople);
        if (sp.getString("UserId", "0") == "0") {
            findpeople.setVisibility(View.GONE);

        }
        findpeople.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(),
                        Findpeople.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }

    private void initOfflineMessage() {
        offlineMessage = (Button) findViewById(R.id.main_button_OfflineMessage);
        offlineMessage.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(),
                        OfflineTextMessage.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }

    private void initOnlineMessage() {
        onlineMessage = (Button) findViewById(R.id.main_button_OnlineMessage);
        onlineMessage.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(),
                        OnlineTextMessage.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }

    private void initSearchUsers(final SharedPreferences sp) {
        searchUsers = (Button) findViewById(R.id.main_button_searchUsers);
        if (sp.getString("UserId", "0") == "0") {
            searchUsers.setVisibility(View.GONE);

        }
        searchUsers.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(),
                        UserSearch.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }

    private void initProfile(final SharedPreferences sp) {
        profile = (Button) findViewById(R.id.main_button_Profile);
        if (sp.getString("UserId", "0") == "0") {
            profile.setVisibility(View.GONE);

        }
        profile.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {

                if (sp.getString("UserId", "0") == "0") {
                    Toast.makeText(getBaseContext(), "Please First Login",
                            Toast.LENGTH_LONG).show();

                } else {
                    Intent myIntent = new Intent(view.getContext(),
                            UserProfile.class);
                    startActivityForResult(myIntent, 0);
                }

            }
        });
    }

    private void initRegister() {
        register = (Button) findViewById(R.id.main_button_Register);
        register.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(),
                        UserRegister.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }

    private void initLogin() {
        login = (Button) findViewById(R.id.main_button_Login);
        login.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), UserLogin.class);
                startActivityForResult(myIntent, 0);
                finish();
            }
        });
    }
}
