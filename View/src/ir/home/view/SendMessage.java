package ir.home.view;

import ir.home.controller.MessageController;
import ir.home.habbeh.R;
import ir.home.model.TbCategory;
import ir.home.utility.HabehException;

import java.io.IOException;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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
	private SharedPreferences sp;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sendmessage);

		sp = this.getSharedPreferences("UserInformation", MODE_PRIVATE);

		initSendMessage(sp);
		
		new AsyncBindCategory().execute();

	}

	private void initSendMessage(final SharedPreferences sp) {
		description = (EditText) findViewById(R.id.sendmessage_edittext_Description);
		send = (Button) findViewById(R.id.sendmessage_button_Send);
		send.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {

				new AsyncSendMessage().execute();
			}
		});
	}


	private class AsyncSendMessage extends AsyncTask<String, Void, Integer> {
		ProgressDialog pdLoading = new ProgressDialog(SendMessage.this);

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pdLoading.setMessage("Loading...");
			pdLoading.show();
		}

		@Override
		protected Integer doInBackground(String... params) {
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
				Toast.makeText(getBaseContext(), e.getMessage(),
						Toast.LENGTH_LONG).show();
			}
			return null;
		}

		protected void onPostExecute(Integer result) {
			super.onPostExecute(result);
			description.setText("");
			Toast.makeText(getBaseContext(), "جملک ارسال شد", Toast.LENGTH_LONG)
					.show();
			pdLoading.dismiss();
		}
	}

	// Task Load Category
	private class AsyncBindCategory extends
			AsyncTask<String, Void, List<TbCategory>> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected List<TbCategory> doInBackground(String... params) {
			MessageController controller = new MessageController();
			try {
				list = controller.retrieveCategoryList();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			} catch (HabehException e) {
				Toast.makeText(getBaseContext(), e.getMessage(),
						Toast.LENGTH_LONG).show();
			}
			return list;
		}

		protected void onPostExecute(List<TbCategory> result) {
			super.onPostExecute(result);
			categoryTitle = (Spinner) findViewById(R.id.sendmessage_spinner_categoryTitle);
			ArrayAdapter<TbCategory> dataAdapter = new ArrayAdapter<TbCategory>(
					SendMessage.this, android.R.layout.simple_spinner_item,
					result);
			dataAdapter
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			categoryTitle.setAdapter(dataAdapter);
			categoryTitle
					.setOnItemSelectedListener(new OnItemSelectedListener() {
						@Override
						public void onItemSelected(AdapterView<?> parant,
								View v, int pos, long id) {
							TbCategory temptitleWithid = (TbCategory) parant
									.getItemAtPosition(pos);
							categoryId = temptitleWithid.getId();
						}

						@Override
						public void onNothingSelected(AdapterView<?> parentView) {

						}

					});
		}

	}

	@Override
	public void onBackPressed() {
		Intent myIntent = new Intent(SendMessage.this, MainActivity.class);
		myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
				| Intent.FLAG_ACTIVITY_CLEAR_TASK);
		startActivityForResult(myIntent, 0);
		finish();
		super.onBackPressed();
	}
}
