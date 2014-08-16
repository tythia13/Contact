package com.bingbing.op.contact.app.phone.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;

import com.bingbing.op.contact.R;
import com.bingbing.op.contact.app.phone.adapter.FragmentAdapter;
import com.bingbing.op.contact.app.phone.fragment.FragmentContactGroup;
import com.bingbing.op.contact.app.phone.fragment.FragmentContactHistory;
import com.bingbing.op.contact.app.phone.fragment.FragmentContactList;
import com.bingbing.op.contact.app.phone.view.TabView;
import com.bingbing.op.contact.app.phone.view.TabView.onItemSelectedListener;
import com.bingbing.op.contact.common.activity.BaseActivity;
import com.bingbing.op.contact.common.db.ContactsProvider;
import com.bingbing.op.contact.common.fragment.BaseFragment;

public class ContactListActivity extends BaseActivity
{
    private FragmentAdapter mAdapter;
    private ViewPager mViewPager;
    private TabView mTabView;
    private List<BaseFragment> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setDefaultKeyMode(DEFAULT_KEYS_SHORTCUT);
        setContentView(R.layout.activity_contact_list);
        Intent intent = getIntent();
        if (intent.getData() == null)
        {
            intent.setData(ContactsProvider.CONTENT_URI);
        }
        mViewPager = (ViewPager)findViewById(R.id.activity_contact_list_viewpager);
        mTabView = (TabView)findViewById(R.id.activity_contact_list_tabview);
        mList = new ArrayList<BaseFragment>();
        FragmentContactGroup f1 = new FragmentContactGroup();
        FragmentContactHistory f2 = new FragmentContactHistory();
        FragmentContactList f3 = new FragmentContactList();
        mList.add(f2);
        mList.add(f3);
        mList.add(f1);
        mAdapter = new FragmentAdapter(getSupportFragmentManager(), mList);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(new OnPageChangeListener()
        {

            @Override
            public void onPageSelected(int position)
            {
                mTabView.setCurrentItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2)
            {// empty
            }

            @Override
            public void onPageScrollStateChanged(int arg0)
            { // empty
            }
        });
        mTabView.setOnItemSelectedListener(new onItemSelectedListener()
        {

            @Override
            public void onItemSelect(int position)
            {
                mViewPager.setCurrentItem(position);
            }
        });
    }

    private static final int AddContact_ID = Menu.FIRST;
    private static final int EXITContact_ID = Menu.FIRST + 1;

    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        menu.add(0, AddContact_ID, 0, R.string.add_user).setShortcut('0', 'a').setIcon(R.drawable.add);
        Intent intent = new Intent(null, getIntent().getData());
        intent.addCategory(Intent.CATEGORY_ALTERNATIVE);
        menu.addIntentOptions(Menu.CATEGORY_ALTERNATIVE, 0, 0, new ComponentName(this, ContactListActivity.class), null, intent, 0, null);
        menu.add(0, EXITContact_ID, 0, R.string.exit).setShortcut('1', 'd').setIcon(R.drawable.exit);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case AddContact_ID:
                startActivity(new Intent(Intent.ACTION_INSERT, getIntent().getData()));
                return true;
            case EXITContact_ID:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
