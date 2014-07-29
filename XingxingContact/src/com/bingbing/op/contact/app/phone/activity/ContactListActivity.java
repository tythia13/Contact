package com.bingbing.op.contact.app.phone.activity;

import com.bingbing.op.contact.R;
import com.bingbing.op.contact.R.drawable;
import com.bingbing.op.contact.R.string;
import com.bingbing.op.contact.common.db.ContactColumn;
import com.bingbing.op.contact.common.db.ContactsProvider;

import android.app.ListActivity;
import android.content.ComponentName;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ContactListActivity extends ListActivity
{
	private static final String TAG = "MyContacts";
	
	private static final int AddContact_ID = Menu.FIRST;
	private static final int EditContact_ID = Menu.FIRST+1;
	private static final int DELEContact_ID = Menu.FIRST+2;
	private static final int EXITContact_ID = Menu.FIRST+3;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setDefaultKeyMode(DEFAULT_KEYS_SHORTCUT);
		
        Intent intent = getIntent();
        if (intent.getData() == null) {
            intent.setData(ContactsProvider.CONTENT_URI);
        }

        getListView().setOnCreateContextMenuListener(this);
        getListView().setBackgroundResource(R.drawable.bg);

        Cursor cursor = managedQuery(getIntent().getData(), ContactColumn.PROJECTION, null, null,null);

        //ע��ÿ���б��ʾ��ʽ ������ + �ƶ��绰
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
			android.R.layout.simple_list_item_2,
			cursor,
			new String[] {ContactColumn.NAME, ContactColumn.MOBILENUM },
			new int[] { android.R.id.text1, android.R.id.text2 });

        setListAdapter(adapter);
	}

    public boolean onCreateOptionsMenu(Menu menu) 
    {
        super.onCreateOptionsMenu(menu);
        //�����ϵ��
        menu.add(0, AddContact_ID, 0, R.string.add_user)
        	.setShortcut('3', 'a')
        	.setIcon(R.drawable.add);
        
        Intent intent = new Intent(null, getIntent().getData());
        intent.addCategory(Intent.CATEGORY_ALTERNATIVE);
        menu.addIntentOptions(Menu.CATEGORY_ALTERNATIVE, 0, 0,
                new ComponentName(this, ContactListActivity.class), null, intent, 0, null);

        //�˳�����
        menu.add(0, EXITContact_ID, 0, R.string.exit)
    		.setShortcut('4', 'd')
    		.setIcon(R.drawable.exit);
        return true;
        
    }
    
    //����˵�����
    public boolean onOptionsItemSelected(MenuItem item) 
    {
        switch (item.getItemId()) 
        {
        case AddContact_ID:
            //�����ϵ��
            startActivity(new Intent(Intent.ACTION_INSERT, getIntent().getData()));
            return true;
        case EXITContact_ID:
        	//�˳�����
        	this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public boolean onPrepareOptionsMenu(Menu menu)
	{
		super.onPrepareOptionsMenu(menu);
		final boolean haveItems = getListAdapter().getCount() > 0;

		if (haveItems)
		{
			
			Uri uri = ContentUris.withAppendedId(getIntent().getData(), getSelectedItemId());

			Intent[] specifics = new Intent[2];
			specifics[0] = new Intent(Intent.ACTION_EDIT, uri);
			specifics[1] = new Intent(Intent.ACTION_VIEW, uri);
			MenuItem[] items = new MenuItem[2];
				
			//������������Ĳ˵�
			Intent intent = new Intent(null, uri);
			intent.addCategory(Intent.CATEGORY_ALTERNATIVE);
			// menu.addIntentOptions(Menu.CATEGORY_ALTERNATIVE, 0, 0, null, specifics, intent, 0, items);

			if (items[0] != null)
			{
				//�༭��ϵ��
				// items[0].setShortcut('1', 'e').setIcon(R.drawable.edituser).setTitle(R.string.editor_user);   
			}
			if (items[1] != null)
			{
				//�鿴��ϵ��
				// items[1].setShortcut('2', 'f').setTitle(R.string.view_user).setIcon(R.drawable.viewuser);   
			}
		}
		else
		{
			menu.removeGroup(Menu.CATEGORY_ALTERNATIVE);
		}
		return true;
	}
    //��̬�˵�����
    //�����Ĭ�ϲ���Ҳ���������ﴦ��
    protected void onListItemClick(ListView l, View v, int position, long id)   
    {   
        Uri uri = ContentUris.withAppendedId(getIntent().getData(), id);   
  
        String action = getIntent().getAction();   
        if ( Intent.ACTION_EDIT.equals(action) )
		{
        	//�༭��ϵ��
        	startActivity(new Intent(Intent.ACTION_EDIT, uri));  
		}  
        else
        {     
        	//�鿴��ϵ��
        	startActivity(new Intent(Intent.ACTION_VIEW, uri));       
        }  
    }   
    
    //���������Ĳ˵�
	public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo)
	{
		AdapterView.AdapterContextMenuInfo info;
		try
		{
			info = (AdapterView.AdapterContextMenuInfo) menuInfo;
		}
		catch (ClassCastException e)
		{
			return;
		}
		//�õ������������
		Cursor cursor = (Cursor) getListAdapter().getItem(info.position);
		if (cursor == null)
		{
			return;
		}

		menu.setHeaderTitle(cursor.getString(1));
		//���ɾ��˵�
		menu.add(0, DELEContact_ID, 0, R.string.delete_user);
	}
    
    @Override
    public boolean onContextItemSelected(MenuItem item)
	{
		AdapterView.AdapterContextMenuInfo info;
		try
		{
			info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
		}
		catch (ClassCastException e)
		{
			return false;
		}

		switch (item.getItemId())
		{
			case DELEContact_ID:
			{
				//ɾ��һ����¼
				Uri noteUri = ContentUris.withAppendedId(getIntent().getData(), info.id);
				getContentResolver().delete(noteUri, null, null);
				return true;
			}
		}
		return false;
	}
}
