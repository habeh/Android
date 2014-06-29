package ir.home.view;

import ir.home.controller.MessageController;
import ir.home.habbeh.R;
import ir.home.model.TbCategory;
import java.io.IOException;
import java.util.ArrayList;
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

public class SendMessage extends Activity {

	private Spinner CategoryTitle;
	private EditText Description;
	private Button Send;
	private ArrayList<TbCategory> List;
	private int CategoryId;

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
		Description = (EditText) findViewById(R.id.sendmessage_edittext_Description);
		Send = (Button) findViewById(R.id.sendmessage_button_Send);
		Send.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {

				MessageController controller = new MessageController();
				try {
					controller.InsertMessage(CategoryId,
							Integer.parseInt(sp.getString("UserId", "")),
							Description.getText().toString());
				} catch (IOException e) {
					e.printStackTrace();
				} catch (XmlPullParserException e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	private void initBindCategorySpinner() {
		CategoryTitle = (Spinner) findViewById(R.id.sendmessage_spinner_categoryTitle);
		// load category item
		MessageController controller = new MessageController();

		try {
			List = (ArrayList<TbCategory>) controller.RetrieveCategoryList();
			List<TitleWithId> list = new ArrayList<TitleWithId>();
			for (int i = 0; i < List.size(); i++) {
				TbCategory temptbCategory = (TbCategory) List.get(i);
				
				list.add(new TitleWithId(temptbCategory.getTitle(),
						temptbCategory.getId()));
			}

			ArrayAdapter<TitleWithId> dataAdapter = new ArrayAdapter<TitleWithId>(
					this, android.R.layout.simple_spinner_item, list);
			dataAdapter
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			CategoryTitle.setAdapter(dataAdapter);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}

		CategoryTitle.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parant, View v, int pos,
					long id) {
				final TitleWithId temptitleWithid = (TitleWithId) parant
						.getItemAtPosition(pos);
				CategoryId = temptitleWithid.id;

			}

			@Override
			public void onNothingSelected(AdapterView<?> parentView) {
				
			}

		});

	}

	
	public class TitleWithId {
		public String title;
		public int id;

		public TitleWithId(String CategoryTitle, int CategoryId) {
			title = CategoryTitle;
			id = CategoryId;
		}

		@Override
		public String toString() {
			return title;
		}
	}

}
