package ir.home.view;

import ir.home.controller.MessageController;
import ir.home.habbeh.R;
import ir.home.model.TbCategory;
import ir.home.utility.HabehException;
import ir.home.view.adapter.MessageCategoryPagerAdapter;

import java.io.IOException;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class OfflineTextMessageMainActivity extends FragmentActivity {

	private MessageCategoryPagerAdapter messageCategoryPagerAdapter;
	private ViewPager viewPager;

	public void onCreate(Bundle savedInstanceState) {
		ActionBar actionBar = getActionBar();

		super.onCreate(savedInstanceState);

		setContentView(R.layout.offlinetexttessagetainactivity);

		try {
			MessageController controller = new MessageController();

			List<TbCategory> categories = controller.retrieveCategoryUsedList();

			messageCategoryPagerAdapter = new MessageCategoryPagerAdapter(
					getSupportFragmentManager(), categories);
			viewPager = (ViewPager) findViewById(R.id.offlinetexttessagetainactivity_pager);
			viewPager.setAdapter(messageCategoryPagerAdapter);
			viewPager
					.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
						@Override
						public void onPageSelected(int position) {
							getActionBar().setSelectedNavigationItem(position);
						}
					});

			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

			// Add 3 tabs, specifying the tab's text and TabListener
			for (int i = 0; i < categories.size(); i++) {
				actionBar.addTab(actionBar.newTab()
						.setText(categories.get(i).getTitle())
						.setTabListener(tabListener));
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (HabehException e) {
			e.printStackTrace();
		}
	}

	ActionBar.TabListener tabListener = new ActionBar.TabListener() {

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {

		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			viewPager.setCurrentItem(tab.getPosition());
		}

		@Override
		public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {

		}

	};

	@Override
	public void onBackPressed() {
		Intent myIntent = new Intent(OfflineTextMessageMainActivity.this,
				MainActivity.class);
		myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
				| Intent.FLAG_ACTIVITY_CLEAR_TASK);
		startActivityForResult(myIntent, 0);
		finish();
		super.onBackPressed();
	}
}
