package com.example.mehdi.android2015.services;

import android.support.annotation.Nullable;

import com.example.mehdi.android2015.core.ApplicationBase;
import com.example.mehdi.android2015.services.entities.UserDetails;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import com.example.mehdi.android2015.services.entities.Message;

public class InMemoryMessageService extends BaseInMemoryService {
    public InMemoryMessageService(ApplicationBase application) {
        super(application);
    }


    @Subscribe
    public void onDeleteMessageRequest(Messages.DeleteMessageRequest request) {
        Messages.DeleteMessageResponse response = new Messages.DeleteMessageResponse();
        response.MessageId = request.messageId;
        postWithDelay(response);
    }


    @Subscribe
    public void onSearchMessagesRequest(Messages.SearchMessagesRequest request) {
        Messages.SearchMessagesResponse response = new Messages.SearchMessagesResponse();
        response.messages = new ArrayList<>();

        List<UserDetails> users = new ArrayList<>();
        for(int i = 1; i < 10; i++) {
            String stringId = Integer.toString(i);
            users.add(new UserDetails(
                    i,
                    false,
                    "User  " + stringId,
                    "user_" + stringId,
                    "http://www.gravatar.com/avatar/" + stringId + "?d=identicon"
            ));
        }

        Random random = new Random();
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE, -100);
        for(int i = 0; i < 100; i++) {
            boolean isFromUs;
            if(request.includeReceiveMessages && request.includeReceiveMessages) {
                isFromUs = random.nextBoolean();
            } else {
                isFromUs = !request.includeReceiveMessages;
            }

            date.set(Calendar.MINUTE, random.nextInt(60 * 24));
            String numberString = Integer.toString(i);
            response.messages.add(new Message(
                    i,
                    (Calendar) date.clone(),
                    "Short Message " + numberString,
                    "Long Message " + numberString,
                    "",
                    users.get(random.nextInt(users.size())),
                    isFromUs,
                    i > 4
            ));
        }

        postWithDelay(response, 2000);


    }

}
