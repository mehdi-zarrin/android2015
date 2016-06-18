package com.example.mehdi.android2015.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mehdi.android2015.R;
import com.example.mehdi.android2015.services.Messages;
import com.example.mehdi.android2015.services.entities.Message;
import com.example.mehdi.android2015.views.MainNavDrawer;
import com.example.mehdi.android2015.views.MessagesAdapter;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class SentMessages extends BaseAuthenticatedActivity implements MessagesAdapter.onMessageClickedListener {
    public static final int REQUEST_VIEW_MESSAGE = 1;

    private MessagesAdapter adapter;
    private ArrayList<Message> messages;
    private View progressFrame;
    private RecyclerView recyclerView;
    @Override
    protected void onAppCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_sent_messages);
        setNavDrawer(new MainNavDrawer(this));

        getSupportActionBar().setTitle("Sent Messages");

        adapter = new MessagesAdapter(this, this);
        messages = adapter.getMessages();
        recyclerView = (RecyclerView) findViewById(R.id.activity_sent_messages_messages);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        progressFrame = findViewById(R.id.activity_sent_messages_progressFrame);

        bus.post(new Messages.SearchMessagesRequest(true, false));

    }

    @Subscribe
    public void onMessagesLoaded(Messages.SearchMessagesResponse response) {
        progressFrame.setVisibility(View.GONE);
        Log.e("DDDD" , "response loaded and size of messages is : " + response.messages.size());
        messages.addAll(response.messages);
        adapter.notifyItemRangeInserted(0, messages.size());
    }
    @Override
    public void onMessageClicked(Message message) {

        Toast.makeText(this, message.getOtherUser().getDisplayName() + " Has been clicked" , Toast.LENGTH_LONG).show();
    }
}
