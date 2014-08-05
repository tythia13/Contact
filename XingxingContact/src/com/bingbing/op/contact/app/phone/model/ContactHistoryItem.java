package com.bingbing.op.contact.app.phone.model;

public class ContactHistoryItem
{
    private int mId;
    private String mName;
    private String mPhoneNumber;

    public int getId()
    {
        return mId;
    }

    public void setId(int mId)
    {
        this.mId = mId;
    }

    public String getName()
    {
        return mName;
    }

    public void setName(String mName)
    {
        this.mName = mName;
    }

    public String getPhoneNumber()
    {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String mPhoneNumber)
    {
        this.mPhoneNumber = mPhoneNumber;
    }
}
