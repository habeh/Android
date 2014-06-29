
package ir.home.view;

import ir.home.controller.MessageController;
import ir.home.habbeh.R;
import ir.home.model.TbCategory;
import ir.home.utility.HabehException;

import java.io.IOException;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SendMessage extends Activity {

    private Spinner categoryTitle;
    private EditText description;
    private Button send;
    private List<TbCategory> list;
    private int categoryId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sendmessage);

        // Load Data From Shared
        final SharedPreferences sp = this.getSharedPreferences(
                "UserInformation", MODE_PRIVATE);

        initSendMessage(sp);

        initBindCategorySpinner();

    }

    private void initSendMessage(final SharedPreferences sp) {
        description = (EditText) findViewById(R.id.sendmessage_edittext_Description);
        send = (Button) findViewById(R.id.sendmessage_button_Send);
        send.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {

                MessageController controller = new MessageController();
                try {
                    controller.InsertMessage(categoryId,
                            Integer.parseInt(sp.getString("UserId", "0")),
                            description.getText().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (NumberFormatException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (HabehException e) {
                    Toast.makeText(getBaseContext(),
                            e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initBindCategorySpinner() {
        categoryTitle = (Spinner) findViewById(R.id.sendmessage_spinner_categoryTitle);
        // load category item
        MessageController controller = new MessageController();

        try {
            list = controller.retrieveCategoryList();

            ArrayAdapter<TbCategory> dataAdapter = new ArrayAdapter<TbCategory>(
                    this, android.R.layout.simple_spinner_item, list);
            dataAdapter
                    .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            categoryTitle.setAdapter(dataAdapter);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (HabehException e) {
            Toast.makeText(getBaseContext(),
                    e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }

        categoryTitle.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parant, View v, int pos, long id) {
                
                TbCategory temptitleWithid = (TbCategory) parant.getItemAtPosition(pos);
                categoryId = temptitleWithid.getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });

    }
}
