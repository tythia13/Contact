package com.bingbing.op.contact.app.phone.fragment;

import com.bingbing.op.contact.common.fragment.BaseFragment;

public class FragmentContactHistory extends BaseFragment
{
    private static FragmentContactHistory mInstance;

    public static FragmentContactHistory getInstance()
    {
        if (mInstance == null)
        {
            mInstance = new FragmentContactHistory();
        }
        return mInstance;
    }

}
