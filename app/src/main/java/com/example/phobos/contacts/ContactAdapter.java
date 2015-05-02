package com.example.phobos.contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {

    public ContactAdapter(Context context, List<Contact> contacts) {
        super(context, R.layout.contact_item, contacts);
    }

    public static class ViewHolder {
        public TextView name;
        public TextView phone;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Contact contact = getItem(position);

        View view = convertView;
        ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater =
                    (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.contact_item, parent, false);
            holder = new ViewHolder();
            holder.name = (TextView) view.findViewById(R.id.contact_name);
            holder.phone = (TextView) view.findViewById(R.id.contact_phone);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        if (contact != null) {
            holder.name.setText(contact.getName());
            holder.phone.setText(contact.getPhone());
        }
        return view;
    }
}
