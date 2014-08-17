package com.bingbing.op.contact.common.receiver;

import com.bingbing.op.contact.ContactApplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BaseBroadcastReceiver extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {
        // empty
    }
    
    public void showToast(String str)
    {
        ContactApplication.getInstance().showToast(str);
    }

}
