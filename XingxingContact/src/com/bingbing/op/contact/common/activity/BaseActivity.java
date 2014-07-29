package com.bingbing.op.contact.common.activity;

import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

public class BaseActivity extends FragmentActivity
{
    protected void showToast(String str)
    {
        if (str != null && !str.equals(""))
        {
            Toast.makeText(this, str, 200).show();
        }
    }
}
