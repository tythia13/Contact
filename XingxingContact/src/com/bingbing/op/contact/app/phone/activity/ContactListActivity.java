package com.bingbing.op.contact.app.phone.activity;

import android.content.ComponentName;
import android.content.ContentUris;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bingbing.op.contact.R;
import com.bingbing.op.contact.app.phone.adapter.FragmentAdapter;
import com.bingbing.op.contact.common.activity.BaseActivity;

public class ContactListActivity extends BaseActivity
{
    private FragmentAdapter mAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setDefaultKeyMode(DEFAULT_KEYS_SHORTCUT);
        setContentView(R.layout.activity_contact_list);
        mViewPager = (ViewPager)findViewById(R.id.activity_contact_list_viewpager);
        mAdapter = new FragmentAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(new OnPageChangeListener()
        {

            @Override
            public void onPageSelected(int arg0)
            {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2)
            {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0)
            {
                // TODO Auto-generated method stub

            }
        });
    }

    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        Uri uri = ContentUris.withAppendedId(getIntent().getData(), id);

        String action = getIntent().getAction();
        if (Intent.ACTION_EDIT.equals(action))
        {
            // �༭��ϵ��
            startActivity(new Intent(Intent.ACTION_EDIT, uri));
        }
        else
        {
            // �鿴��ϵ��
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        }
    }

    // TODO delete all following lines
    private static final String TAG = "MyContacts";

    private static final int AddContact_ID = Menu.FIRST;
    private static final int EditContact_ID = Menu.FIRST + 1;
    private static final int DELEContact_ID = Menu.FIRST + 2;
    private static final int EXITContact_ID = Menu.FIRST + 3;

    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        // �����ϵ��
        menu.add(0, AddContact_ID, 0, R.string.add_user).setShortcut('3', 'a').setIcon(R.drawable.add);

        Intent intent = new Intent(null, getIntent().getData());
        intent.addCategory(Intent.CATEGORY_ALTERNATIVE);
        menu.addIntentOptions(Menu.CATEGORY_ALTERNATIVE, 0, 0, new ComponentName(this, ContactListActivity.class), null, intent, 0, null);

        // �˳�����
        menu.add(0, EXITContact_ID, 0, R.string.exit).setShortcut('4', 'd').setIcon(R.drawable.exit);
        return true;

    }

    // ����˵�����
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case AddContact_ID:
                // �����ϵ��
                startActivity(new Intent(Intent.ACTION_INSERT, getIntent().getData()));
                return true;
            case EXITContact_ID:
                // �˳�����
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onPrepareOptionsMenu(Menu menu)
    {
        super.onPrepareOptionsMenu(menu);
        final boolean haveItems = false;

        if (haveItems)
        {

            Uri uri = null;// ContentUris.withAppendedId(getIntent().getData(),
                           // getSelectedItemId());

            Intent[] specifics = new Intent[2];
            specifics[0] = new Intent(Intent.ACTION_EDIT, uri);
            specifics[1] = new Intent(Intent.ACTION_VIEW, uri);
            MenuItem[] items = new MenuItem[2];

            // ������������Ĳ˵�
            Intent intent = new Intent(null, uri);
            intent.addCategory(Intent.CATEGORY_ALTERNATIVE);
            // menu.addIntentOptions(Menu.CATEGORY_ALTERNATIVE, 0, 0, null, specifics, intent, 0,
            // items);

            if (items[0] != null)
            {
                // �༭��ϵ��
                // items[0].setShortcut('1',
                // 'e').setIcon(R.drawable.edituser).setTitle(R.string.editor_user);
            }
            if (items[1] != null)
            {
                // �鿴��ϵ��
                // items[1].setShortcut('2',
                // 'f').setTitle(R.string.view_user).setIcon(R.drawable.viewuser);
            }
        }
        else
        {
            menu.removeGroup(Menu.CATEGORY_ALTERNATIVE);
        }
        return true;
    }

    // ���������Ĳ˵�
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo)
    {
        AdapterView.AdapterContextMenuInfo info;
        try
        {
            info = (AdapterView.AdapterContextMenuInfo)menuInfo;
        }
        catch (ClassCastException e)
        {
            return;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo info;
        try
        {
            info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        }
        catch (ClassCastException e)
        {
            return false;
        }

        switch (item.getItemId())
        {
            case DELEContact_ID:
            {
                // ɾ��һ����¼
                Uri noteUri = ContentUris.withAppendedId(getIntent().getData(), info.id);
                getContentResolver().delete(noteUri, null, null);
                return true;
            }
        }
        return false;
    }
}
