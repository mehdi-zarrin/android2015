package com.example.mehdi.android2015.views;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mehdi.android2015.R;
import com.example.mehdi.android2015.activities.BaseActivity;
import com.example.mehdi.android2015.services.entities.UserDetails;
import com.squareup.picasso.Picasso;

public class UserDetailsAdapter extends ArrayAdapter<UserDetails> {
    private LayoutInflater inflater;

    public UserDetailsAdapter(BaseActivity activity) {
        super(activity, 0);
        inflater = activity.getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder view;
        UserDetails details = getItem(position);
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_user_details, parent, false);
            view = new ViewHolder(convertView);
            convertView.setTag(view);
        } else {
            view = (ViewHolder) convertView.getTag();
        }

        view.displayName.setText(details.getDisplayName());
        Picasso.with(getContext()).load(details.getAvatarUrl()).into(view.avatar);
        return convertView;

    }

    public class ViewHolder {
        public TextView displayName;
        public ImageView avatar;

        public ViewHolder(View view) {
            displayName = (TextView) view.findViewById(R.id.list_item_user_details_displayname);
            avatar = (ImageView) view.findViewById(R.id.list_item_user_details_avatar);

        }
    }


}
