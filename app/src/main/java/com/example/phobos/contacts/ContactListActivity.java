package com.example.phobos.contacts;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * An activity representing a list of Contacts. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ContactDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link ContactListFragment} and the item details
 * (if present) is a {@link ContactDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link ContactListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class ContactListActivity extends FragmentActivity
        implements ContactListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        if (findViewById(R.id.contact_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((ContactListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.contact_list))
                    .setActivateOnItemClick(true);
        }

        // TODO: If exposing deep links into your app, handle intents here.
    }

    /**
     * Callback method from {@link ContactListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(Contact contact) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putParcelable(ContactDetailFragment.ARG_ITEM, contact);
            ContactDetailFragment fragment = new ContactDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contact_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, ContactDetailActivity.class);
            detailIntent.putExtra(ContactDetailFragment.ARG_ITEM, contact);
            startActivity(detailIntent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        String [] projection = new String[]
                {
                        ContactsContract.Profile.DISPLAY_NAME_PRIMARY,
                        ContactsContract.Profile.PHOTO_THUMBNAIL_URI
                };

        Cursor profileCursor = getContentResolver().query(
                        ContactsContract.Profile.CONTENT_URI,
                        projection,
                        null,
                        null,
                        null
        );

        ImageView avatar = (ImageView) findViewById(R.id.avatar);
        TextView name = (TextView) findViewById(R.id.profile_name);

        if (profileCursor.moveToFirst()) {
            String avatarUri = profileCursor.getString(profileCursor.getColumnIndex(projection[1]));
            if (!TextUtils.isEmpty(avatarUri)) {
                avatar.setImageURI(Uri.parse(avatarUri));
            }
            else {
                avatar.setImageResource(R.mipmap.ic_launcher);
            }

            name.setText(profileCursor.getString(profileCursor.getColumnIndex(projection[0])));
        }

        profileCursor.close();
    }
}
