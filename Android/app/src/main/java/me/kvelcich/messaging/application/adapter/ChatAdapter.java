package me.kvelcich.messaging.application.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import me.kvelcich.messaging.R;
import me.kvelcich.messaging.application.classes.Chat;

public class ChatAdapter extends BaseAdapter {

    private List<Chat> chats;
    private LayoutInflater layoutInflater;

    public ChatAdapter(Context context, List<Chat> chats) {
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.chats = chats;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return chats.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();

            convertView = layoutInflater.inflate(R.layout.list_chat, parent, false);

            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.name.setText(chats.get(position).getUserIds());
            viewHolder.content = (TextView) convertView.findViewById(R.id.content);
            viewHolder.content.setText("Example message goes here...");
            viewHolder.time = (TextView) convertView.findViewById(R.id.time);
            viewHolder.time.setText("01/01/01");

            convertView.setTag(viewHolder);
        } else viewHolder = (ViewHolder) convertView.getTag();

        return convertView;
    }

    private class ViewHolder {
        private TextView name;
        private TextView content;
        private TextView time;
    }
}
