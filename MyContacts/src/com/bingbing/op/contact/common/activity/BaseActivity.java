package com.bingbing.op.contact.common.activity;

import android.app.Activity;
import android.widget.Toast;

public class BaseActivity extends Activity
{
    protected void showToast(String str)
    {
        if (str != null && !str.equals(""))
        {
            Toast.makeText(this, str, 200).show();
        }
    }
}
