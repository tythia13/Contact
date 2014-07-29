package com.bingbing.op.contact.app.phone.activity;

import com.bingbing.op.contact.R;
import com.bingbing.op.contact.R.drawable;
import com.bingbing.op.contact.R.id;
import com.bingbing.op.contact.R.layout;
import com.bingbing.op.contact.R.string;
import com.bingbing.op.contact.common.db.ContactColumn;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ContactDetailActivity extends Activity
{
	private TextView mTextViewName;
	private TextView mTextViewMobile;
	private TextView mTextViewHome;
	private TextView mTextViewAddress;
	private TextView mTextViewEmail;
	private TextView mTextViewBlog;
	
    private Cursor mCursor;
    private Uri mUri;
    
    private static final int REVERT_ID = Menu.FIRST;
    private static final int DELETE_ID = Menu.FIRST + 1;
    private static final int EDITOR_ID = Menu.FIRST + 2;
    private static final int CALL_ID = Menu.FIRST + 3;
    private static final int SENDSMS_ID = Menu.FIRST + 4;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		mUri = getIntent().getData();
		
		this.setContentView(R.layout.activity_contact_detail);
		
		mTextViewName = (TextView) findViewById(R.id.TextView_Name);
		mTextViewMobile = (TextView) findViewById(R.id.TextView_Mobile);
		mTextViewHome = (TextView) findViewById(R.id.TextView_Home);
		mTextViewAddress = (TextView) findViewById(R.id.TextView_Address);
		mTextViewEmail = (TextView) findViewById(R.id.TextView_Email);
		mTextViewBlog = (TextView) findViewById(R.id.TextView_Blog);
		
	    // ��ò�����ԭʼ��ϵ����Ϣ
        mCursor = managedQuery(mUri, ContactColumn.PROJECTION, null, null, null);
        mCursor.moveToFirst();
	}
	
    protected void onResume()
	{
		super.onResume();
		if (mCursor != null)
		{
			// ��ȡ����ʾ��ϵ����Ϣ
			mCursor.moveToFirst();
			
			mTextViewName.setText(mCursor.getString(ContactColumn.NAME_COLUMN));
			mTextViewMobile.setText(mCursor.getString(ContactColumn.MOBILENUM_COLUMN));
			mTextViewHome.setText(mCursor.getString(ContactColumn.HOMENUM_COLUMN));
			mTextViewAddress.setText(mCursor.getString(ContactColumn.ADDRESS_COLUMN));
			mTextViewEmail.setText(mCursor.getString(ContactColumn.EMAIL_COLUMN));
			mTextViewBlog.setText(mCursor.getString(ContactColumn.BLOG_COLUMN));
		}
		else
		{
			setTitle("������Ϣ");
		}
	}
    
    public boolean onCreateOptionsMenu(Menu menu)
	{
		super.onCreateOptionsMenu(menu);
		//��Ӳ˵�
		menu.add(0, REVERT_ID, 0, R.string.revert).setShortcut('0', 'r').setIcon(R.drawable.listuser);
		menu.add(0, DELETE_ID, 0, R.string.delete_user).setShortcut('0', 'd').setIcon(R.drawable.remove);
		menu.add(0, EDITOR_ID, 0, R.string.editor_user).setShortcut('0', 'd').setIcon(R.drawable.edituser);
		menu.add(0, CALL_ID, 0, R.string.call_user).setShortcut('0', 'd').setIcon(R.drawable.calluser)
				.setTitle(this.getResources().getString(R.string.call_user)+mTextViewName.getText());
		menu.add(0, SENDSMS_ID, 0, R.string.sendsms_user).setShortcut('0', 'd').setIcon(R.drawable.sendsms)
		.setTitle(this.getResources().getString(R.string.sendsms_user)+mTextViewName.getText());
		return true;
	}
    
    public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			//ɾ��
			case DELETE_ID:
				deleteContact();
				finish();
				break;
			//�����б�
			case REVERT_ID:
				setResult(RESULT_CANCELED);
				finish();
				break;
			case EDITOR_ID:
			//�༭��ϵ��
				startActivity(new Intent(Intent.ACTION_EDIT, mUri)); 
				break;
			case CALL_ID:
			//������ϵ��
		        Intent call = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+mTextViewMobile.getText()));
		        startActivity(call);
				break;
			case SENDSMS_ID:
			//�����Ÿ���ϵ��
		        Intent sms = new Intent(Intent.ACTION_SENDTO,Uri.parse("smsto:"+mTextViewMobile.getText()));
		        startActivity(sms);
				break;
		}
		return super.onOptionsItemSelected(item);
	}


	// ɾ����ϵ����Ϣ
	private void deleteContact()
	{
		if (mCursor != null)
		{
			mCursor.close();
			mCursor = null;
			getContentResolver().delete(mUri, null, null);
			setResult(RESULT_CANCELED);
		}
	}
}

