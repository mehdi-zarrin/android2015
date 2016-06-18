package com.example.mehdi.android2015.views;

import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mehdi.android2015.R;
import com.example.mehdi.android2015.activities.BaseActivity;
import com.example.mehdi.android2015.services.entities.ContactRequest;
import com.squareup.picasso.Picasso;

public class ContactsRequestAdapter extends ArrayAdapter<ContactRequest> {
    private LayoutInflater inflater;
    public ContactsRequestAdapter(BaseActivity activity) {
        super(activity, 0);
        inflater = activity.getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder view;
        ContactRequest request = getItem(position);
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_contact_request, parent, false);
            view = new ViewHolder(convertView);
            convertView.setTag(view);
        } else {
            view = (ViewHolder) convertView.getTag();
        }

        view.displayName.setText(request.getUser().getDisplayName());
        Picasso.with(getContext()).load(request.getUser().getAvatarUrl()).into(view.avatar);
        String createdAt = DateUtils
                .formatDateTime(
                        getContext(),
                        request.getCreateAt().getTimeInMillis(),
                        DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_TIME
                );

        if(request.getFromUs()) {
            view.createdAt.setText("Send At " + createdAt);
        } else {
            view.createdAt.setText("Received " + createdAt);
        }

        return convertView;
    }

    public class ViewHolder {
        public ImageView avatar;
        public TextView displayName, createdAt;

        public ViewHolder(View view) {
            displayName = (TextView) view.findViewById(R.id.list_item_contact_request_displayName);
            createdAt = (TextView) view.findViewById(R.id.list_item_contact_request_createdAt);
            avatar = (ImageView) view.findViewById(R.id.list_item_contact_request_avatar);
        }

    }
}
