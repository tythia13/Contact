package com.bingbing.op.contact.common.util;

import android.view.View;
import android.widget.ImageView;

import com.bingbing.op.contact.common.CustomImageView;

public class ImageViewLoadingCache
{
    private View mBaseView;
    private ImageView[] mImageView;
    private int[] mImageId;
    // unique id
    private String mId;

    /**
     * @param baseView root view
     * @param imageIds imageIds
     * @param id unique id.
     */
    public ImageViewLoadingCache(View baseView, int[] imageIds, String id)
    {
        this.mBaseView = baseView;
        mImageId = imageIds;
        if (mImageId != null)
        {
            mImageView = new ImageView[mImageId.length];
        }
    }

    /**
     * Set unique id.
     * 
     * @param id
     */
    public void setUniqueId(String id)
    {
        mId = id;
    }

    /**
     * Check whether the cache is the same according to unique id.
     * 
     * @param id
     * @return true
     */
    public boolean isEquals(String id)
    {
        if (StrUtils.isEmpty(mId))
        {
            if (StrUtils.isEmpty(id))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            if (mId.equals(id))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    /**
     * Retrieves image view count in every list item.
     */
    public int getImageViewCount()
    {
        return mImageId == null ? 0 : mImageId.length;
    }

    /**
     * Index of image views.
     * 
     * @param index
     * @return
     */
    public ImageView getImageView(int index)
    {
        if (mImageId == null || mImageId.length == 0)
        {
            return null;
        }
        else
        {
            if (mImageView[index] == null)
            {
                View view = mBaseView.findViewById(mImageId[index]);
                if (view instanceof CustomImageView)
                {
                    mImageView[index] = ((CustomImageView)view).getHeadImage();
                }
                else
                {
                    mImageView[index] = (ImageView)mBaseView.findViewById(mImageId[index]);
                }
            }
            return mImageView[index];
        }
    }

}
