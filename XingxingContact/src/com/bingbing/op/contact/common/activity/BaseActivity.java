package com.bingbing.op.contact.common.activity;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

    /**
     * 
     * @param view the view which the keyboard will add to it.
     */
    public void showSoftKeyboard(View view)
    {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive())
        {
            imm.showSoftInput(view, 0);
        }
    }

    /**
     * Hide the soft keyboard.
     * 
     * @param view
     */
    public void hideSoftKeyboard(View view)
    {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive())
        {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
