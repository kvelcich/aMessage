package me.kvelcich.messaging.application.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import me.kvelcich.messaging.application.classes.Message;
import me.kvelcich.messaging.R;

public class MessagesAdapter extends BaseAdapter {

    private List<Message> messages;
    private LayoutInflater layoutInflater;

    public MessagesAdapter(Context context, List<Message> messages) {
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.messages = messages;
    }

    @Override
    public int getItemViewType(int position) {
        if (messages.get(position).isSent())
            return 1;
        return 2;
    }

    @Override
    public int getCount() {
        return messages.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        int type = getItemViewType(position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            switch (type) {
                case 1:
                    convertView = layoutInflater.inflate(R.layout.list_messagesent, parent, false);
                    viewHolder.Content = (TextView) convertView.findViewById(R.id.msg);

                    viewHolder.Content.setText(messages.get(position).getContent());
                    if (position > 0) {
                        if (messages.get(position).isSent() == messages.get(position - 1).isSent()) {
                            viewHolder.Arrow = (ImageView) convertView.findViewById(R.id.arrow);
                            viewHolder.Arrow.setVisibility(View.INVISIBLE);
                            viewHolder.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.layout);
                            viewHolder.relativeLayout.setPadding(viewHolder.relativeLayout.getPaddingLeft(), (viewHolder.relativeLayout.getPaddingLeft() + viewHolder.relativeLayout.getPaddingRight()) / 3, viewHolder.relativeLayout.getPaddingRight(), 0);
                        }
                    }
                    break;
                case 2:
                    convertView = layoutInflater.inflate(R.layout.list_messagereceived, parent, false);
                    viewHolder.Content = (TextView) convertView.findViewById(R.id.msg);

                    viewHolder.Content.setText(messages.get(position).getContent());
                    if (position > 0) {
                        if (messages.get(position).isSent() == messages.get(position - 1).isSent()) {
                            viewHolder.Arrow = (ImageView) convertView.findViewById(R.id.arrow);
                            viewHolder.Arrow.setVisibility(View.INVISIBLE);
                            viewHolder.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.layout);
                            viewHolder.relativeLayout.setPadding(viewHolder.relativeLayout.getPaddingLeft(), (viewHolder.relativeLayout.getPaddingLeft() + viewHolder.relativeLayout.getPaddingRight()) / 4, viewHolder.relativeLayout.getPaddingRight(), 0);
                        }
                    }
                default:
                    break;
            }
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    private class ViewHolder {
        private TextView Content;
        private ImageView Arrow;
        private RelativeLayout relativeLayout;
    }
}