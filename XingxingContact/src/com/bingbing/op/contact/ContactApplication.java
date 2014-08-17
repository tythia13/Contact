package com.bingbing.op.contact;

import android.app.Application;
import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

public class ContactApplication extends Application
{
    private static ContactApplication mInstance;
    private static Toast mToast;

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

    public static Context getContext()
    {
        return mInstance;
    }

    public void showToast(String text)
    {
        TextView textView = new TextView(getContext());
        textView.setText(text);
        textView.setBackgroundResource(R.drawable.bkg_alert);
        textView.setTextColor(getContext().getResources().getColor(R.color.color_ffffff));
        textView.setPadding(20, 10, 20, 10);
        textView.setTextSize(getContext().getResources().getDimension(R.dimen.DEMIN_FONT_SIZE_10));

        Toast toast = getToast();
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setView(textView);
        toast.setDuration(80);
        toast.show();
    }

    private static Toast getToast()
    {
        if (mToast == null)
        {
            mToast = new Toast(getContext());
        }
        return mToast;
    }

}
