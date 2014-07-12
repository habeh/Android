
package ir.home.view.adapter;


import java.util.List;

import ir.home.model.*;
import ir.home.view.OfflineTextMessage;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MessageCategoryPagerAdapter extends FragmentStatePagerAdapter {
    
    private List<TbCategory> categories;
    
    public MessageCategoryPagerAdapter (FragmentManager fm,List<TbCategory> categories) {
        super(fm);
        this.categories=categories;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new OfflineTextMessage();
        Bundle args = new Bundle();
        
        args.putInt(OfflineTextMessage.ARG_OBJECT, categories.get(i).getId());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "OBJECT " + (position + 1);
    }
}
