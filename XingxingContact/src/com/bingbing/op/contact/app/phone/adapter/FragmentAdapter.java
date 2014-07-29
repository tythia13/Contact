package com.bingbing.op.contact.app.phone.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bingbing.op.contact.app.phone.fragment.FragmentContactGroup;
import com.bingbing.op.contact.app.phone.fragment.FragmentContactHistory;
import com.bingbing.op.contact.app.phone.fragment.FragmentContactList;

public class FragmentAdapter extends FragmentPagerAdapter
{
    private static final int COUNT = 3;

    public FragmentAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        Fragment fragment = FragmentContactHistory.getInstance();
        switch (position)
        {
            case 0:
                fragment = FragmentContactHistory.getInstance();
                break;
            case 1:
                fragment = FragmentContactList.getInstance();
                break;
            case 2:
                fragment = FragmentContactGroup.getInstance();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount()
    {
        return COUNT;
    }

}
