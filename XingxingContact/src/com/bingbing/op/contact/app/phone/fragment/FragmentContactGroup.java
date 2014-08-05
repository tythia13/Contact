package com.bingbing.op.contact.app.phone.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bingbing.op.contact.R;

public class FragmentContactGroup extends BaseFragmentContact
{

    public static FragmentContactGroup newInstance()
    {
        FragmentContactGroup mInstance = new FragmentContactGroup();
        return mInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_contact_group, container, false);
        initView(v);
        return v;
    }

    private void initView(View v)
    {

    }
}
