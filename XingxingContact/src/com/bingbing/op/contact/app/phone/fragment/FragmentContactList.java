package com.bingbing.op.contact.app.phone.fragment;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.bingbing.op.contact.R;
import com.bingbing.op.contact.app.phone.adapter.ContactListAdapter;
import com.bingbing.op.contact.app.phone.model.ContactItem;
import com.bingbing.op.contact.common.db.ContactColumn;
import com.bingbing.op.contact.common.db.ContactsProvider;

public class FragmentContactList extends BaseFragmentContact
{
    private ContactListAdapter mAdapter;
    private List<ContactItem> mItems;
    private ListView mListView;

    public static FragmentContactList newInstance()
    {
        FragmentContactList mInstance = new FragmentContactList();
        return mInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_contact_list, null, false);
        mListView = (ListView)v.findViewById(R.id.framgment_contact_list_listview);
        mItems = new ArrayList<ContactItem>();
        Cursor cursor = mActivity.managedQuery(ContactsProvider.CONTENT_URI, ContactColumn.PROJECTION, null, null, null);

        if (cursor != null)
        {
            if (cursor.moveToFirst())
            {
                ContactItem item;
                while (cursor.moveToNext())
                {
                    item = new ContactItem();
                    int index = cursor.getColumnIndex(ContactColumn._ID);
                    if (index != -1) item.setId(cursor.getInt(index));
                    index = cursor.getColumnIndex(ContactColumn.NAME);
                    if (index != -1) item.setName(cursor.getString(index));
                    index = cursor.getColumnIndex(ContactColumn.MOBILENUM);
                    if (index != -1) item.setPhoneNumber(cursor.getString(index));
                    mItems.add(item);
                }

            }
            //TODO cursor.close();
        }
        mAdapter = new ContactListAdapter(mActivity, mItems);
        mListView.setAdapter(mAdapter);
        return v;
    }
}
