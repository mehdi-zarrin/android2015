package com.example.mehdi.android2015.services;

import com.example.mehdi.android2015.core.ServiceResponse;
import com.example.mehdi.android2015.services.entities.ContactRequest;
import com.example.mehdi.android2015.services.entities.UserDetails;

import java.util.List;

public final class Contact {
    private Contact(){}


    public static class GetContactRequestsRequest {
        public boolean FromUs;

        public GetContactRequestsRequest(boolean fromUs) {
            FromUs = fromUs;
        }
    }

    public static class GetContactRequestsResponse extends ServiceResponse {
        public List<ContactRequest> requests;
    }


    public static class GetContactsRequest {
        public boolean includePendingContacts;

        public GetContactsRequest(boolean includePendingContacts) {
            this.includePendingContacts = includePendingContacts;
        }
    }


    public static class GetContactsResponse extends  ServiceResponse{
        public List<UserDetails> contacts;
    }


    public static class SendContactRequestRequest {
        public int userId;

        public SendContactRequestRequest(int userId) {
            this.userId = userId;
        }
    }


    public static class SendContactsRequestResponse extends ServiceResponse {
    }


    public static class RespondContactRequestRequest {
        public int ContactRequestId;
        public boolean Accept;

        public RespondContactRequestRequest(int contactRequestId, boolean accept) {
            ContactRequestId = contactRequestId;
            Accept = accept;
        }
    }

    public static class RespondContactRequestResponse extends ServiceResponse {}


}
