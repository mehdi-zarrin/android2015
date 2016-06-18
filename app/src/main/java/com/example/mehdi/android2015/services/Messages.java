package com.example.mehdi.android2015.services;

import com.example.mehdi.android2015.core.ServiceResponse;
import com.example.mehdi.android2015.services.entities.Message;

import java.util.List;

public final class Messages {
    private Messages(){}


    public static class DeleteMessageRequest {
        int messageId;

        public DeleteMessageRequest(int messageId) {
            this.messageId = messageId;
        }
    }


    public static class DeleteMessageResponse extends ServiceResponse{
        public int MessageId;
    }


    public static class SearchMessagesRequest {
        public String FromContactId;
        public boolean includeSentMessages;
        public boolean includeReceiveMessages;

        public SearchMessagesRequest(String fromContactId, boolean includeSentMessages, boolean includeReceiveMessages) {
            FromContactId = fromContactId;
            this.includeSentMessages = includeSentMessages;
            this.includeReceiveMessages = includeReceiveMessages;
        }

        public SearchMessagesRequest(boolean includeSentMessages, boolean includeReceiveMessages) {
            this.includeSentMessages = includeSentMessages;
            this.includeReceiveMessages = includeReceiveMessages;
        }
    }

    public static class SearchMessagesResponse extends ServiceResponse {
        public List<Message> messages;
    }


}
