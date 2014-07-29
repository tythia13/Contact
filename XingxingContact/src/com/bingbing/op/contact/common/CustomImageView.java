package com.bingbing.op.contact.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bingbing.op.contact.R;

public class CustomImageView extends RelativeLayout
{
    private ImageView mHeadImage;
    private ImageView mHeadImageCover;
    private boolean mIsNeedCover = true;
    private TypedArray mTypedArray;

    public CustomImageView(Context context)
    {
        super(context);
        setView(context);
    }

    public CustomImageView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.CommonHeadImageView);
        setView(context);
    }

    private void setView(Context context)
    {
        LayoutInflater.from(context).inflate(R.layout.layout_head_image_widget, this);
        mHeadImage = (ImageView)findViewById(R.id.common_header_images);
        mHeadImageCover = (ImageView)findViewById(R.id.common_head_cover);
        if (mIsNeedCover)
        {
            mHeadImageCover.setVisibility(View.VISIBLE);
        }
        else
        {
            mHeadImageCover.setVisibility(View.GONE);
        }
        if (mTypedArray != null)
        {
            Drawable cover = mTypedArray.getDrawable(R.styleable.CommonHeadImageView_headSrc);
            if (cover != null)
            {
                mHeadImage.setImageDrawable(cover);
            }
            Drawable head = mTypedArray.getDrawable(R.styleable.CommonHeadImageView_coverSrc);
            if (head != null)
            {
                mHeadImageCover.setImageDrawable(head);
            }
        }
    }

    public ImageView getHeadImage()
    {
        if (mHeadImage == null)
        {
            mHeadImage = (ImageView)findViewById(R.id.common_header_images);
        }
        return mHeadImage;
    }

    public void setHeadImage(Bitmap bmp)
    {
        if (mHeadImage == null)
        {
            mHeadImage = (ImageButton)findViewById(R.id.common_header_images);
        }

        if (bmp != null)
        {
            mHeadImage.setImageBitmap(bmp);
        }
        else
        {
            mHeadImage.setImageResource(R.drawable.img_qq);
        }
    }

    public void setHeadImageDrawable(Drawable drawable)
    {
        if (mHeadImage == null)
        {
            mHeadImage = (ImageButton)findViewById(R.id.common_header_images);
        }

        if (drawable != null)
        {
            mHeadImage.setImageDrawable(drawable);
        }
        else
        {
            mHeadImage.setImageResource(R.drawable.img_qq);
        }
    }

    public void setHeadImageResource(int rId)
    {
        if (mHeadImage == null)
        {
            mHeadImage = (ImageButton)findViewById(R.id.common_header_images);
        }

        mHeadImage.setImageResource(rId);
    }

    public void setHeadCoverImage(Drawable d)
    {
        if (mHeadImageCover == null)
        {
            mHeadImageCover = (ImageView)findViewById(R.id.common_head_cover);
        }
        mHeadImageCover.setImageDrawable(d);

    }

    public ImageView getHeadCoverImage()
    {
        if (mHeadImageCover == null)
        {
            mHeadImageCover = (ImageView)findViewById(R.id.common_head_cover);
        }
        return mHeadImageCover;
    }

}
