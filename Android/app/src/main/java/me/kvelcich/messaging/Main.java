package me.kvelcich.messaging;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import me.kvelcich.messaging.application.database.DatabaseHelper;
import me.kvelcich.messaging.application.database.Impl.ChatImpl;
import me.kvelcich.messaging.application.fragment.FragmentChat;
import me.kvelcich.messaging.application.fragment.FragmentMessage;

public class Main extends Activity {

    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chat);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().remove(fragment).commit();

                fragment = new FragmentMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("chatId", -1);
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().add(R.id.container, fragment).commit();

                setContentView(R.layout.activity_main);
                EditText target = (EditText) findViewById(R.id.target);
                target.requestFocus();

                fab.hide();
            }
        });

        fragment = new FragmentChat();
        getFragmentManager().beginTransaction().add(R.id.container, fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}