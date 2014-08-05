package com.bingbing.op.contact.app.phone.fragment;

import android.app.Activity;
import android.os.Bundle;

import com.bingbing.op.contact.common.fragment.BaseFragment;

public class BaseFragmentContact extends BaseFragment
{

    protected Activity mActivity;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }
}
