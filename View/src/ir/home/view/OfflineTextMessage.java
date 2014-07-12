
package ir.home.view;

import ir.home.habbeh.R;
import ir.home.model.TbMessage;
import ir.home.view.adapter.MessageAdapter;
import ir.home.view.database.DBAdapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class OfflineTextMessage extends Fragment {

    public static final String ARG_OBJECT= "categoryId";
    private ListView messageListView;
    private MessageAdapter adapter;
    private DBAdapter db;
    private List<TbMessage> offLineMessages;

    @Override
    public View onCreateView(LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.offlinemssage, container, false);
        Bundle args = getArguments();

        int categoryId = 1;
        if (args != null) {
            categoryId = args.getInt(ARG_OBJECT);
        }

        db = new DBAdapter(this.getActivity());
        db.open();
        offLineMessages = db.getAllSaveMessage(categoryId);
        db.close();
        messageListView = (ListView) rootView.findViewById(R.id.offlinemessage_listview_messages);
        adapter = new MessageAdapter(this.getActivity(), offLineMessages);
        messageListView.setAdapter(adapter);

        return rootView;
    }
}
