package com.bingbing.op.contact;

import android.app.Application;
import android.content.Context;

public class ContactApplication extends Application
{
    private static ContactApplication mInstance;

    public static ContactApplication getInstance()
    {
        if (mInstance == null)
        {
            mInstance = new ContactApplication();
        }
        return mInstance;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    public Context getContext()
    {
        return getApplicationContext();
    }
}
