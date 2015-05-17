package com.example.phobos.contacts;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.ContactsContract.CommonDataKinds;

public class Contact implements Parcelable{
    private String id;
    private String name;
    private String phone;
    private String avatar;

    public Contact(String id, String name, String phone, String avatar) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public static Contact fromCursor(Cursor cursor) {
        int idColumnIndex = cursor.getColumnIndex(CommonDataKinds.Contactables.LOOKUP_KEY);
        int nameColumnIndex = cursor.getColumnIndex(CommonDataKinds.Contactables.DISPLAY_NAME);
        int phoneColumnIndex = cursor.getColumnIndex(CommonDataKinds.Phone.NUMBER);
        int avatarColumnIndex = cursor.getColumnIndex(CommonDataKinds.Photo.PHOTO_THUMBNAIL_URI);

        String id = cursor.getString(idColumnIndex);
        String name = cursor.getString(nameColumnIndex);
        String phone = cursor.getString(phoneColumnIndex);
        String avatar = cursor.getString(avatarColumnIndex);

        return new Contact(id, name, phone, avatar);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(phone);
        dest.writeString(avatar);
    }

    public static final Parcelable.Creator<Contact> CREATOR
            = new Parcelable.Creator<Contact>() {
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    private Contact(Parcel in) {
        id = in.readString();
        name = in.readString();
        phone = in.readString();
        avatar = in.readString();
    }
}
