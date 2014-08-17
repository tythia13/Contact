package com.bingbing.op.contact.app.phone.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
        mAdapter = new ContactListAdapter(mActivity, mItems);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3)
            {
                ContactItem item = mItems.get(position - mListView.getHeaderViewsCount());
                Uri noteUri = ContentUris.withAppendedId(mActivity.getIntent().getData(), item.getId());
                mActivity.startActivity(new Intent(Intent.ACTION_VIEW, noteUri));
            }
        });
        return v;
    }

    private Cursor mCursor;

    @Override
    public void onResume()
    {
        super.onResume();
        mCursor = mActivity.managedQuery(ContactsProvider.CONTENT_URI, ContactColumn.PROJECTION, null, null, null);
        if (mCursor != null)
        {
            List<ContactItem> list = new ArrayList<ContactItem>();
            if (mCursor.moveToFirst())
            {
                ContactItem item;
                while (mCursor.moveToNext())
                {
                    item = new ContactItem();
                    int index = mCursor.getColumnIndex(ContactColumn._ID);
                    if (index != -1) item.setId(mCursor.getInt(index));
                    index = mCursor.getColumnIndex(ContactColumn.NAME);
                    if (index != -1) item.setName(mCursor.getString(index));
                    index = mCursor.getColumnIndex(ContactColumn.MOBILENUM);
                    if (index != -1) item.setPhoneNumber(mCursor.getString(index));
                    list.add(item);
                }

            }
            // TODO cursor.close();
            mItems.clear();
            mItems.addAll(list);
            mAdapter.updateList(mItems);
        }
    }

    @Override
    public void onPause()
    {
        super.onPause();
        if (mCursor != null && !mCursor.isClosed())
        {
            //mCursor.close();
        }
    }
}
