package com.example.mehdi.android2015.services;

import com.example.mehdi.android2015.core.ApplicationBase;
import com.example.mehdi.android2015.services.entities.ContactRequest;
import com.example.mehdi.android2015.services.entities.UserDetails;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class InMemoryContactService extends BaseInMemoryService {
    public InMemoryContactService(ApplicationBase application) {
        super(application);
    }


    @Subscribe
    public void getContactsRequests(Contact.GetContactRequestsRequest request) {
        Contact.GetContactRequestsResponse response = new Contact.GetContactRequestsResponse();
        response.requests = new ArrayList<>();

        for(int i=0; i < 3; i++) {

            response.requests.add(new ContactRequest(i, request.FromUs, createFakeUser(i, false), new GregorianCalendar()));
        }

        postWithDelay(response, 2000);
    }

    @Subscribe
    public void getContacts(Contact.GetContactsRequest request) {

        Contact.GetContactsResponse response = new Contact.GetContactsResponse();
        response.contacts = new ArrayList<>();

        for(int i = 1; i < 10; i++) {
            response.contacts.add(createFakeUser(i, true));
        }

        postWithDelay(response, 2000);
    }



    @Subscribe
    public void sendContactRequest(Contact.SendContactRequestRequest request) {
        Contact.SendContactsRequestResponse response = new Contact.SendContactsRequestResponse();
        if(request.userId == 2) {
            response.setOperationError("something bad happened!");
        } else {
            postWithDelay(response);
        }

    }

    @Subscribe
    public void respondToContactRequest(Contact.RespondContactRequestRequest request) {
        postWithDelay(new Contact.RespondContactRequestResponse());
    }


    private UserDetails createFakeUser(int id, boolean isContact) {
        String idString = Integer.toString(id);
        return new UserDetails(
            id,
            isContact,
            "Contact " + idString,
            "Contact" + idString,
            "http://www.gravatar.com/avatar/" + idString + "?d=identicon&s=64"
        );
    }

}
