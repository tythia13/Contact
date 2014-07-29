package com.bingbing.op.contact.common.util;

public class ImageInfo
{
    private String mLocalPath;
    private String mImageUrl;
    private int mDefaultImageSourceId;

    /**
     * @return the mLocalPath
     */
    public String getLocalPath()
    {
        return mLocalPath;
    }

    /**
     * @param mLocalPath the mLocalPath to set
     */
    public void setLocalPath(String mLocalPath)
    {
        this.mLocalPath = mLocalPath;
    }

    /**
     * @return the mImageUrl
     */
    public String getImageUrl()
    {
        return mImageUrl;
    }

    /**
     * @param mImageUrl the mImageUrl to set
     */
    public void setImageUrl(String mImageUrl)
    {
        this.mImageUrl = mImageUrl;
    }

    /**
     * @return the mDefaultImageSourceId
     */
    public int getDefaultImageSourceId()
    {
        return mDefaultImageSourceId;
    }

    /**
     * @param mDefaultImageSourceId the mDefaultImageSourceId to set
     */
    public void setDefaultImageSourceId(int mDefaultImageSourceId)
    {
        this.mDefaultImageSourceId = mDefaultImageSourceId;
    }
}