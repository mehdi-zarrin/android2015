package com.example.mehdi.android2015.views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mehdi.android2015.R;
import com.example.mehdi.android2015.activities.BaseActivity;
import com.example.mehdi.android2015.services.entities.Message;

import java.util.ArrayList;
import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessageViewHolder> implements View.OnClickListener {
    private final LayoutInflater inflater;
    private final BaseActivity activity;
    private final onMessageClickedListener listener;
    private final ArrayList<Message> messages;

    public MessagesAdapter(BaseActivity activity, onMessageClickedListener listener) {
        this.activity = activity;
        this.listener = listener;
        messages = new ArrayList<>();
        inflater = activity.getLayoutInflater();
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_message, parent, false);
        view.setOnClickListener(this);
        return new MessageViewHolder(view);
    }
    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        holder.populate(activity, messages.get(position));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public void onClick(View view) {
        if(view.getTag() instanceof Message) {
            Message message = (Message) view.getTag();
            listener.onMessageClicked(message);
        }
    }

    public interface onMessageClickedListener {
        void onMessageClicked(Message message);
    }
}
