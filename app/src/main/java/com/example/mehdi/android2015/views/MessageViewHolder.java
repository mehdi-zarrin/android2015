package com.example.mehdi.android2015.views;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mehdi.android2015.R;
import com.example.mehdi.android2015.services.entities.Message;
import com.squareup.picasso.Picasso;

public class MessageViewHolder extends RecyclerView.ViewHolder {

    private ImageView avatar;
    private TextView displayname, createdAt, sentReceived;
    private CardView cardView;

    public MessageViewHolder(View itemView) {
        super(itemView);
        cardView = (CardView) itemView;
        avatar = (ImageView) itemView.findViewById(R.id.list_item_message_avatar);
        displayname = (TextView) itemView.findViewById(R.id.list_item_message_displayname);
        createdAt = (TextView) itemView.findViewById(R.id.list_item_message_createdat);
        sentReceived = (TextView) itemView.findViewById(R.id.list_item_message_sentReceived);

    }

    public void populate(Context context, Message message) {
        itemView.setTag(message);
        Picasso.with(context).load(message.getOtherUser().getAvatarUrl()).into(avatar);
        String createdDateTime = DateUtils.formatDateTime(context, message.getCreatedAt().getTimeInMillis(), DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_TIME);
        sentReceived.setText( message.isFromUs() ? "sent" : "received");
        displayname.setText(message.getOtherUser().getDisplayName());
        createdAt.setText(createdDateTime);

        int colorResourceId;
        if(message.isSelected()) {
            colorResourceId = R.color.list_item_message_background_selected;
            cardView.setCardElevation(5);
        } else if(message.isRead()) {
            colorResourceId = R.color.list_item_message_background;
            cardView.setCardElevation(2);
        } else {
            colorResourceId = R.color.list_item_message_background_unread;
            cardView.setCardElevation(3);
        }

        cardView.setBackgroundColor(context.getResources().getColor(colorResourceId));
    }
}
