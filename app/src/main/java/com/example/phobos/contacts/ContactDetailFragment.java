package com.example.phobos.contacts;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A fragment representing a single Contact detail screen.
 * This fragment is either contained in a {@link ContactListActivity}
 * in two-pane mode (on tablets) or a {@link ContactDetailActivity}
 * on handsets.
 */
public class ContactDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM = "item";

    private Contact mContact;

    private TextView name;
    private TextView phone;
    private ImageView avatar;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ContactDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contact_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        name = (TextView) view.findViewById(R.id.contact_name);
        phone = (TextView) view.findViewById(R.id.contact_phone);
        avatar = (ImageView) view.findViewById(R.id.contact_avatar);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM)) {
            mContact = getArguments().getParcelable(ARG_ITEM);
        }

        if (mContact != null) {
            if (!TextUtils.isEmpty(mContact.getName())) {
                name.setText(mContact.getName());
            }

            if (!TextUtils.isEmpty(mContact.getPhone())) {
                phone.setText(mContact.getPhone());
            }

            if (!TextUtils.isEmpty(mContact.getAvatar())) {
                avatar.setImageURI(Uri.parse(mContact.getAvatar()));
            } else {
                avatar.setImageResource(R.mipmap.ic_launcher);
            }
        }
    }
}
