package com.bingbing.op.contact.app.phone.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bingbing.op.contact.common.fragment.BaseFragment;

public class FragmentAdapter extends FragmentPagerAdapter
{
    private List<BaseFragment> mList;

    public FragmentAdapter(FragmentManager fm, List<BaseFragment> list)
    {
        super(fm);
        mList = list;
    }

    @Override
    public Fragment getItem(int position)
    {
        return mList.get(position);
    }

    @Override
    public int getCount()
    {
        return mList == null ? 0 : mList.size();
    }

}
