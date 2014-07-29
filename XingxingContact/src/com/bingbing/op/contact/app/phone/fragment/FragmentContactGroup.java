package com.bingbing.op.contact.app.phone.fragment;

import com.bingbing.op.contact.common.fragment.BaseFragment;

public class FragmentContactGroup extends BaseFragment
{

    private static FragmentContactGroup mInstance;

    public static FragmentContactGroup getInstance()
    {
        if (mInstance == null)
        {
            mInstance = new FragmentContactGroup();
        }
        return mInstance;
    }

}
