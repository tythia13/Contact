package com.bingbing.op.contact.app.phone.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.bingbing.op.contact.R;

public class TabView extends FrameLayout implements OnClickListener
{
    private LinearLayout mLinearLayoutHistory, mLinearLayoutContacts, mLinearLayoutGroup;
    private onItemSelectedListener mListener;

    public TabView(Context context)
    {
        super(context);
        init(context);
    }

    public TabView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context);
    }

    public TabView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context)
    {
        LayoutInflater.from(context).inflate(R.layout.layout_tabview_common_head, this);
        mLinearLayoutContacts = (LinearLayout)findViewById(R.id.layout_tabview_common_head_linearlayout_contact);
        mLinearLayoutHistory = (LinearLayout)findViewById(R.id.layout_tabview_common_head_linearlayout_dial);
        mLinearLayoutGroup = (LinearLayout)findViewById(R.id.layout_tabview_common_head_linearlayout_group);
        mLinearLayoutContacts.setOnClickListener(this);
        mLinearLayoutHistory.setOnClickListener(this);
        mLinearLayoutGroup.setOnClickListener(this);
        mLinearLayoutHistory.setEnabled(false);
    }

    public void setOnItemSelectedListener(onItemSelectedListener listener)
    {
        mListener = listener;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.layout_tabview_common_head_linearlayout_dial:
                if (mListener != null)
                {
                    mListener.onItemSelect(0);
                }
                break;
            case R.id.layout_tabview_common_head_linearlayout_contact:
                if (mListener != null)
                {
                    mListener.onItemSelect(1);
                }
                break;
            case R.id.layout_tabview_common_head_linearlayout_group:
                if (mListener != null)
                {
                    mListener.onItemSelect(2);
                }
                break;
        }
    }

    public void setCurrentItem(int position)
    {
        mLinearLayoutHistory.setEnabled(true);
        mLinearLayoutContacts.setEnabled(true);
        mLinearLayoutGroup.setEnabled(true);
        switch (position)
        {
            case 0:
                mLinearLayoutHistory.setEnabled(false);
                break;
            case 1:
                mLinearLayoutContacts.setEnabled(false);
                break;
            case 2:
                mLinearLayoutGroup.setEnabled(false);
                break;
        }
    }

    public interface onItemSelectedListener
    {
        void onItemSelect(int position);
    }

}
