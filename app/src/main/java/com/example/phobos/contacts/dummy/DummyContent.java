package com.example.phobos.contacts.dummy;

import com.example.phobos.contacts.Contact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<Contact> ITEMS = new ArrayList<>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, Contact> ITEM_MAP = new HashMap<>();


    static {
        addItem(new Contact("1", "Peter", "123", null));
        addItem(new Contact("2", "Anna", "321", null));
        addItem(new Contact("3", "Ivan", "753", null));
    }

    private static void addItem(Contact item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getId(), item);
    }
}