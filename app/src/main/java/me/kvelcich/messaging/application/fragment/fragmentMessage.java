package me.kvelcich.messaging.application.fragment;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import me.kvelcich.messaging.application.adapter.MessagesAdapter;
import me.kvelcich.messaging.application.classes.Message;
import me.kvelcich.messaging.application.database.MessageHandler;
import me.kvelcich.messaging.R;

public class fragmentMessage extends Fragment {
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_message, container, false);

        refresh();

        ImageView sendButton = (ImageView) rootView.findViewById(R.id.send);
        sendButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        //Changing the image color
                        ImageView view = (ImageView) v;
                        //overlay is black with transparency of 0x77 (119)
                        view.getDrawable().setColorFilter(0x33000000,PorterDuff.Mode.SRC_ATOP);
                        view.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        final EditText target = (EditText) getActivity().findViewById(R.id.target);
                        final EditText content = (EditText) rootView.findViewById(R.id.content);

                        if (target.getText().toString().matches("")) {
                            Snackbar.make(rootView, "Please enter a recipient", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                        } else  if (content.getText().toString().matches("")) {
                            Snackbar.make(rootView, "You cannot send a blank message", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                        } else {
                            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
                            String address = sharedPreferences.getString("Address", "");
                            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                            String url = "http://" + address + ".ngrok.io/messages";
                            Toast.makeText(getActivity(), "http://" + address + ".ngrok.io/messages", Toast.LENGTH_SHORT);
                            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    insert(new Message(target.getText().toString(), content.getText().toString(), true));
                                    content.setText("");
                                } }, new Response.ErrorListener() {
                                    //Create an error listener to handle errors appropriately.
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        NetworkResponse response = error.networkResponse;
                                        if(response != null && response.data != null){
                                            Toast.makeText(getActivity(),"errorMessage: "+response.statusCode, Toast.LENGTH_SHORT).show();
                                        }else{
                                            String errorMessage=error.getClass().getSimpleName();
                                            if(!TextUtils.isEmpty(errorMessage)){
                                                Toast.makeText(getActivity(),"errorMessage: "+errorMessage, Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                }) {
                                    protected Map<String, String> getParams() {
                                        Map<String, String> MyData = new HashMap<String, String>();
                                        MyData.put("target", target.getText().toString());
                                        MyData.put("content", content.getText().toString());
                                        return MyData;
                                    }
                                };
                            int socketTimeout = 30000;
                            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                            MyStringRequest.setRetryPolicy(policy);
                            requestQueue.add(MyStringRequest);
                        }
                    case MotionEvent.ACTION_CANCEL: {
                        //Resetting image color
                        ImageView view = (ImageView) v;
                        //clear the overlay
                        view.getDrawable().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });
        return rootView;
    }

    public void insert(Message message) {
        MessageHandler messageHandler = new MessageHandler(getActivity());
        messageHandler.insertMessage(message);
        refresh();
    }

    public void refresh() {
        MessageHandler messageHandler = new MessageHandler(getActivity());
        ArrayList<Message> messages = messageHandler.getMessages("kvelcich@gmail.com");
        MessagesAdapter messagesAdapter = new MessagesAdapter(getActivity(), messages);
        ListView listView = (ListView) rootView.findViewById(R.id.listview);
        listView.setAdapter(messagesAdapter);
        listView.setSelection(messagesAdapter.getCount() - 1);
    }

    public static fragmentMessage newInstance() {
        return new fragmentMessage();
    }
}
