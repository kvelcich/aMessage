package me.kvelcich.messaging.application.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import me.kvelcich.messaging.R;
import me.kvelcich.messaging.application.adapter.ChatAdapter;
import me.kvelcich.messaging.application.adapter.MessagesAdapter;
import me.kvelcich.messaging.application.classes.Chat;
import me.kvelcich.messaging.application.database.DatabaseHelper;
import me.kvelcich.messaging.application.database.Impl.ChatImpl;
import me.kvelcich.messaging.application.database.Impl.MessageImpl;

public class FragmentChat extends Fragment {

    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_chat, container, false);

        DatabaseHelper db = new DatabaseHelper(getActivity());
        List<Chat> chats = ChatImpl.retrieveChats(db.getReadableDatabase());

        ListView listView = (ListView) rootView.findViewById(R.id.listview);
        ChatAdapter chatAdapter = new ChatAdapter(getActivity(), chats);
        listView.setAdapter(chatAdapter);

        return rootView;
    }
}
