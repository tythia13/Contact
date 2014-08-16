package com.bingbing.op.contact.app.phone.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bingbing.op.contact.R;
import com.bingbing.op.contact.app.phone.adapter.ContactHistoryAdapter;
import com.bingbing.op.contact.app.phone.adapter.FragmentAdapter;
import com.bingbing.op.contact.app.phone.fragment.FragmentContactGroup;
import com.bingbing.op.contact.app.phone.fragment.FragmentContactHistory;
import com.bingbing.op.contact.app.phone.fragment.FragmentContactList;
import com.bingbing.op.contact.app.phone.model.ContactHistoryItem;
import com.bingbing.op.contact.app.phone.view.TabView;
import com.bingbing.op.contact.app.phone.view.TabView.onItemSelectedListener;
import com.bingbing.op.contact.common.activity.BaseActivity;
import com.bingbing.op.contact.common.db.ContactsProvider;
import com.bingbing.op.contact.common.fragment.BaseFragment;
import com.bingbing.op.contact.common.util.StrUtils;

public class ContactListActivity extends BaseActivity implements OnClickListener
{
    private TextView mTextViewOne, mTextViewTwo, mTextViewThree, mTextViewFour, mTextViewFive, mTextViewSix, mTextViewSeven,
            mTextViewEight, mTextViewNine, mTextViewJing, mTextViewXing, mTextViewZero;
    private TextView mTextViewShowKeyboard;
    private EditText mEditTextNumber;
    private ImageView mImageViewClear, mImageViewHideKeyboard, mImageViewDial, mImageViewDelete;
    private LinearLayout mLinearLayoutTop, mLinearLayoutKeyboard, mLinearLayoutBottomOne, mLinearLayoutBottomTwo;
    private ListView mListView;
    private List<BaseFragment> mFragmentList;
    private FragmentAdapter mFragmentAdapter;
    private ViewPager mViewPager;
    private TabView mTabView;
    private ContactHistoryAdapter mContactorAdaper;
    private List<ContactHistoryItem> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setDefaultKeyMode(DEFAULT_KEYS_SHORTCUT);
        setContentView(R.layout.activity_contact_list);
        initView();
        initData();
    }

    private void initView()
    {
        mLinearLayoutTop = (LinearLayout)findViewById(R.id.activity_contact_list_linearlayout_top);
        mLinearLayoutKeyboard = (LinearLayout)findViewById(R.id.activity_contact_list_linearlayout_keyboard);
        mLinearLayoutBottomOne = (LinearLayout)findViewById(R.id.activity_contact_list_linearlayout_bottom_1);
        mLinearLayoutBottomTwo = (LinearLayout)findViewById(R.id.activity_contact_list_linearlayout_bottom_2);
        mListView = (ListView)findViewById(R.id.activity_contact_list_listview);
        mTextViewZero = (TextView)findViewById(R.id.activity_contact_list_textview_0);
        mTextViewOne = (TextView)findViewById(R.id.activity_contact_list_textview_1);
        mTextViewTwo = (TextView)findViewById(R.id.activity_contact_list_textview_2);
        mTextViewThree = (TextView)findViewById(R.id.activity_contact_list_textview_3);
        mTextViewFour = (TextView)findViewById(R.id.activity_contact_list_textview_4);
        mTextViewFive = (TextView)findViewById(R.id.activity_contact_list_textview_5);
        mTextViewSix = (TextView)findViewById(R.id.activity_contact_list_textview_6);
        mTextViewSeven = (TextView)findViewById(R.id.activity_contact_list_textview_7);
        mTextViewEight = (TextView)findViewById(R.id.activity_contact_list_textview_8);
        mTextViewNine = (TextView)findViewById(R.id.activity_contact_list_textview_9);
        mTextViewXing = (TextView)findViewById(R.id.activity_contact_list_textview_xing);
        mTextViewJing = (TextView)findViewById(R.id.activity_contact_list_textview_jing);
        mTextViewShowKeyboard = (TextView)findViewById(R.id.activity_contact_list_textview_showkeyboard);
        mEditTextNumber = (EditText)findViewById(R.id.activity_contact_list_edittext_numbers);
        mImageViewClear = (ImageView)findViewById(R.id.activity_contact_list_imageview_clear);
        mImageViewDelete = (ImageView)findViewById(R.id.activity_contact_list_imageview_delete);
        mImageViewDial = (ImageView)findViewById(R.id.activity_contact_list_imageview_dial);
        mImageViewHideKeyboard = (ImageView)findViewById(R.id.activity_contact_list_imageview_hidekeyboard);
        mTextViewZero.setOnClickListener(this);
        mTextViewOne.setOnClickListener(this);
        mTextViewTwo.setOnClickListener(this);
        mTextViewThree.setOnClickListener(this);
        mTextViewFour.setOnClickListener(this);
        mTextViewFive.setOnClickListener(this);
        mTextViewSix.setOnClickListener(this);
        mTextViewSeven.setOnClickListener(this);
        mTextViewEight.setOnClickListener(this);
        mTextViewNine.setOnClickListener(this);
        mTextViewXing.setOnClickListener(this);
        mTextViewJing.setOnClickListener(this);
        mTextViewShowKeyboard.setOnClickListener(this);
        mImageViewClear.setOnClickListener(this);
        mImageViewDelete.setOnClickListener(this);
        mImageViewDial.setOnClickListener(this);
        mImageViewHideKeyboard.setOnClickListener(this);
        mEditTextNumber.addTextChangedListener(mWatcher);
        mEditTextNumber.setOnTouchListener(new OnTouchListener()
        {

            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                hideSoftKeyboard(mEditTextNumber);
                return true;
            }
        });
        mImageViewDelete.setOnLongClickListener(new View.OnLongClickListener()
        {

            @Override
            public boolean onLongClick(View v)
            {
                mEditTextNumber.setText("");
                return false;
            }
        });
        mListView.setOnTouchListener(new OnTouchListener()
        {

            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                mLinearLayoutBottomOne.setVisibility(View.VISIBLE);
                mLinearLayoutBottomTwo.setVisibility(View.GONE);
                mLinearLayoutKeyboard.setVisibility(View.GONE);
                return false;
            }
        });
    }

    private void initData()
    {
        Intent intent = getIntent();
        if (intent.getData() == null)
        {
            intent.setData(ContactsProvider.CONTENT_URI);
        }
        mViewPager = (ViewPager)findViewById(R.id.activity_contact_list_viewpager);
        mTabView = (TabView)findViewById(R.id.activity_contact_list_tabview);
        mFragmentList = new ArrayList<BaseFragment>();
        FragmentContactGroup f1 = new FragmentContactGroup();
        FragmentContactHistory f2 = new FragmentContactHistory();
        FragmentContactList f3 = new FragmentContactList();
        mFragmentList.add(f2);
        mFragmentList.add(f3);
        mFragmentList.add(f1);
        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(mFragmentAdapter);
        mViewPager.setOnPageChangeListener(new OnPageChangeListener()
        {

            @Override
            public void onPageSelected(int position)
            {
                mTabView.setCurrentItem(position);
                if (position == 0)
                {
                    mLinearLayoutBottomOne.setVisibility(View.VISIBLE);
                }
                else
                {
                    mEditTextNumber.setText("");
                    mLinearLayoutTop.setVisibility(View.INVISIBLE);
                    mLinearLayoutKeyboard.setVisibility(View.GONE);
                    mListView.setVisibility(View.INVISIBLE);
                    mLinearLayoutBottomOne.setVisibility(View.GONE);
                    mLinearLayoutBottomTwo.setVisibility(View.GONE);
                }
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
        mList = new ArrayList<ContactHistoryItem>();
        ContactHistoryItem item;
        for (int i = 0; i < 10; i++)
        {
            item = new ContactHistoryItem();
            item.setId(i);
            item.setName("name" + i);
            item.setPhoneNumber((Math.random() * 1000000 + i) + "");
            mList.add(item);
        }
        mContactorAdaper = new ContactHistoryAdapter(this, mList);
        mListView.setAdapter(mContactorAdaper);
    }

    private TextWatcher mWatcher = new TextWatcher()
    {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        { // empty
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {// empty
        }

        @Override
        public void afterTextChanged(Editable s)
        {
            if (s.toString().equals(""))
            {
                mLinearLayoutTop.setVisibility(View.INVISIBLE);
                mListView.setVisibility(View.GONE);
            }
            else
            {
                mLinearLayoutTop.setVisibility(View.VISIBLE);
                mListView.setVisibility(View.VISIBLE);
                mEditTextNumber.setSelection(s.length());
            }
        }
    };

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.activity_contact_list_textview_0:
                appendOrDeleteText(true, "0");
                break;
            case R.id.activity_contact_list_textview_1:
                appendOrDeleteText(true, "1");
                break;
            case R.id.activity_contact_list_textview_2:
                appendOrDeleteText(true, "2");
                break;
            case R.id.activity_contact_list_textview_3:
                appendOrDeleteText(true, "3");
                break;
            case R.id.activity_contact_list_textview_4:
                appendOrDeleteText(true, "4");
                break;
            case R.id.activity_contact_list_textview_5:
                appendOrDeleteText(true, "5");
                break;
            case R.id.activity_contact_list_textview_6:
                appendOrDeleteText(true, "6");
                break;
            case R.id.activity_contact_list_textview_7:
                appendOrDeleteText(true, "7");
                break;
            case R.id.activity_contact_list_textview_8:
                appendOrDeleteText(true, "8");
                break;
            case R.id.activity_contact_list_textview_9:
                appendOrDeleteText(true, "9");
                break;
            case R.id.activity_contact_list_textview_xing:
                appendOrDeleteText(true, "*");
                break;
            case R.id.activity_contact_list_textview_jing:
                appendOrDeleteText(true, "#");
                break;
            case R.id.activity_contact_list_textview_showkeyboard:
                mLinearLayoutBottomOne.setVisibility(View.GONE);
                mLinearLayoutBottomTwo.setVisibility(View.VISIBLE);
                mLinearLayoutKeyboard.setVisibility(View.VISIBLE);
                mListView.setVisibility(View.VISIBLE);
                break;
            case R.id.activity_contact_list_imageview_clear:
                mEditTextNumber.setText("");
                break;
            case R.id.activity_contact_list_imageview_delete:
                appendOrDeleteText(false, null);
                break;
            case R.id.activity_contact_list_imageview_dial:
                dial();
                break;
            case R.id.activity_contact_list_imageview_hidekeyboard:
                mLinearLayoutBottomOne.setVisibility(View.VISIBLE);
                mLinearLayoutBottomTwo.setVisibility(View.GONE);
                mLinearLayoutKeyboard.setVisibility(View.INVISIBLE);
                break;
        }
    }

    private void dial()
    {
        String number = mEditTextNumber.getText().toString().trim();
        if (!StrUtils.isEmpty(number))
        {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
            startActivity(intent);
        }
    }

    private void appendOrDeleteText(boolean isAppend, String append)
    {
        String text = mEditTextNumber.getText().toString();
        if (isAppend)
        {
            if (append != null)
            {
                mEditTextNumber.setText(text + append);
            }
        }
        else
        {
            if (text.length() > 0)
            {
                mEditTextNumber.setText(text.substring(0, text.length() - 1));
            }
        }
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
