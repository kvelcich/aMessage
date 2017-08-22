package me.kvelcich.messaging.application.fragment;

import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import me.kvelcich.messaging.application.adapter.MessagesAdapter;
import me.kvelcich.messaging.application.classes.Message;
import me.kvelcich.messaging.application.database.DatabaseHelper;
import me.kvelcich.messaging.R;
import me.kvelcich.messaging.application.database.Impl.MessageImpl;

public class FragmentMessage extends Fragment {

    private View rootView;
    private int chatId;

    public FragmentMessage() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_message, container, false);

        chatId = getArguments().getInt("chatId");
        refresh(chatId);

        return rootView;
    }

    public void refresh(int chatId) {
        DatabaseHelper db = new DatabaseHelper(getActivity());
        List<Message> messageList = MessageImpl.retrieveMessages(db.getReadableDatabase(), chatId);
        MessagesAdapter messagesAdapter = new MessagesAdapter(getActivity(), messageList);
        ListView listView = (ListView) rootView.findViewById(R.id.listview);
        listView.setAdapter(messagesAdapter);
        listView.setSelection(messagesAdapter.getCount() - 1);
    }
}
