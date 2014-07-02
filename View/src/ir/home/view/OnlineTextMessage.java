
package ir.home.view;

import ir.home.controller.MessageController;
import ir.home.controller.UserController;
import ir.home.habbeh.R;
import ir.home.model.TbMessage;
import ir.home.model.TbUser;
import ir.home.utility.HabehException;
import ir.home.view.database.DBAdapter;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class OnlineTextMessage extends Activity {

    private TbUser Result;
    private List<TbMessage> list;
    DBAdapter db;
    String tb_message = DBAdapter.DATABASE_TBMESSAGE;
    String userNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onlinemessage);

        db = new DBAdapter(this);
        db.open();

       initGetOnlineMessage();

        Toast.makeText(getBaseContext(),
                "New Message For Download : " + list.size(), Toast.LENGTH_LONG)
                .show();

        Button Download = (Button) findViewById(R.id.onlinemessage_button_download);
        Download.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {

                int i = 0;

                for (; i < list.size(); i++) {
                    TbMessage temp = list.get(i);
                    initGetUserInformation(temp);

                    try {
                        db.insertTbMessage(
                                temp.getId(),
                                temp.getUserId(),
                                userNameText,
                                temp.getCategoryTitle(),
                                temp.getDescription(),                                
                                temp.getShare(),
                                temp.getSendDateAsString(),
                                DBAdapter.DATABASE_TBMESSAGE);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
                db.close();
                Toast.makeText(getBaseContext(),
                        "Download Complete", Toast.LENGTH_LONG)
                        .show();

            }
        });

    }

    private void initGetOnlineMessage() {

        MessageController controller = new MessageController();
        try {
            list = controller.ShowOnlineMessage("2014/01/01");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (HabehException e) {

        }

    }

    private void initGetUserInformation(final TbMessage temp) {
        UserController controller = new UserController();
        try {
            Result = controller.getProfile(temp.getUserId());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (HabehException e) {
            e.printStackTrace();
        }

        userNameText = Result.getUserName();
    }

}
