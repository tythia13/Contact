package com.bingbing.op.contact.common.util;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.bingbing.op.contact.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.widget.ImageView;

public class AsyncImageLoader
{
    private static AsyncImageLoader mAlbumImageLoader;
    private HashMap<String, SoftReference<Drawable>> mImageCache;
    private static final int IMAGE_CALL_BACK_HANDLER_CODE = 0;
    private static final int IMAGE_LOAD_RUNNING_THREAD_MAX_NUMBER = 5;
    private List<Thread> mRunningThread;
    private List<Thread> mUnrunThread;
    private HashMap<String, String> mImageLoading;

    private AsyncImageLoader()
    {
        mImageCache = new HashMap<String, SoftReference<Drawable>>();
        mRunningThread = new ArrayList<Thread>();
        mUnrunThread = new ArrayList<Thread>();
        mImageLoading = new HashMap<String, String>();
    }

    public static AsyncImageLoader getInstance()
    {
        if (mAlbumImageLoader == null)
        {
            mAlbumImageLoader = new AsyncImageLoader();
        }
        return mAlbumImageLoader;
    }

    /**
     * Loading image to image view. <b>Note:</b> The function must be called in main thread.
     */
    public void loadingImage(final ImageView imageView, String imageId, Context context)
    {
        loadDrawable(imageView, imageId, context);
    }

    public void loadDrawable(final ImageView imageView, final String imageId, final Context context)
    {
        imageView.setTag(imageId);
        final Handler handler = new Handler()
        {
            public void handleMessage(Message message)
            {
                switch (message.what)
                {
                    case IMAGE_CALL_BACK_HANDLER_CODE:
                        Drawable cachedImage = null;
                        Object obj = message.obj;
                        if (obj != null && obj instanceof Drawable)
                        {
                            cachedImage = (Drawable)obj;
                        }
                        if (cachedImage == null)
                        {
                            cachedImage = getImage(imageId);
                        }
                        if (cachedImage != null && imageView.getTag() != null && imageView.getTag().equals(imageId))
                        {
                            imageView.setImageDrawable(cachedImage);
                        }
                        else if (cachedImage != null)
                        {
                            // empty
                        }
                        else
                        {
                            imageView.setImageResource(R.drawable.img_qq);
                        }
                        break;
                    default:
                        break;
                }
            }
        };
        if (mImageCache.containsKey(imageId))
        {
            SoftReference<Drawable> softReference = mImageCache.get(imageId);
            Drawable drawable = null;
            if (softReference != null)
            {
                drawable = softReference.get();
            }
            if (drawable != null)
            {
                imageView.setImageDrawable(drawable);
                return;
            }
            else
            {
                mImageCache.remove(imageId);
            }
        }
        imageView.setImageResource(R.drawable.img_qq);
        try
        {
            if (!mImageLoading.containsKey(imageId))
            {
                Thread thread = new Thread()
                {
                    @Override
                    public void run()
                    {
                        if (!mImageCache.containsKey(imageId))
                        {
                            Drawable drawable = loadImageFromUrl(imageId, context);
                            mImageCache.put(imageId, new SoftReference<Drawable>(drawable));
                            Message message = handler.obtainMessage(IMAGE_CALL_BACK_HANDLER_CODE, drawable);
                            handler.sendMessage(message);
                            mRunningThread.remove(this);
                            mImageLoading.remove(imageId);
                            synchronized (mUnrunThread)
                            {
                                if (!mUnrunThread.isEmpty() && mRunningThread.size() < IMAGE_LOAD_RUNNING_THREAD_MAX_NUMBER)
                                {
                                    Thread unrunThread = mUnrunThread.remove(0);
                                    mRunningThread.add(unrunThread);
                                    unrunThread.start();
                                }
                            }
                        }
                    }
                };
                mImageLoading.put(imageId, imageId);
                if (mRunningThread.size() > IMAGE_LOAD_RUNNING_THREAD_MAX_NUMBER)
                {
                    mUnrunThread.add(thread);
                }
                else
                {
                    mRunningThread.add(thread);
                    thread.start();
                }
            }
        }
        catch (Exception e)
        {

        }
    }

    public Drawable getImage(final String imageId)
    {
        Drawable drawable = null;
        if (mImageCache.containsKey(imageId))
        {
            SoftReference<Drawable> softReference = mImageCache.get(imageId);
            if (softReference != null)
            {
                drawable = softReference.get();
            }
            if (drawable != null) { return drawable; }
        }
        return null;
    }

    public static Drawable loadImageFromUrl(String imageId, Context context)
    {
        if (StrUtils.isEmpty(imageId)) { return null; }
        Drawable drawable;
        Bitmap bm = MediaStore.Images.Thumbnails.getThumbnail(context.getContentResolver(), Long.parseLong((String)imageId),
                Images.Thumbnails.MINI_KIND, null);
        if (bm != null)
        {
            drawable = new BitmapDrawable(bm);
            return drawable;
        }
        else
        {
            return null;
        }
    }

    public interface ImageCallback
    {
        public void imageLoaded(boolean state, String imageId);
    }
}
