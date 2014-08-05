package com.bingbing.op.contact.app.phone.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bingbing.op.contact.R;
import com.bingbing.op.contact.app.phone.model.ContactHistoryItem;
import com.bingbing.op.contact.common.interfaces.InterfaceManager.OnScrollingStateListener;
import com.bingbing.op.contact.common.util.AsyncImageLoader;
import com.bingbing.op.contact.common.util.ImageViewLoadingCache;

public class ContactHistoryAdapter extends BaseAdapter implements OnScrollingStateListener
{
    private Context mContext;
    private List<ContactHistoryItem> mList;
    private AsyncImageLoader mAsyncImageLoader;
    private boolean mIsScrolling;

    public ContactHistoryAdapter(Context context, List<ContactHistoryItem> List)
    {
        mIsScrolling = false;
        mContext = context;
        mList = List;
        mAsyncImageLoader = AsyncImageLoader.getInstance();
    }

    public void updateList(List<ContactHistoryItem> list)
    {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount()
    {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return mList == null ? null : mList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return mList == null ? 0 : position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ContactHistoryItem item = mList.get(position);
        ViewHolder holder;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_contact_history_view_item, null);
            holder = new ViewHolder(convertView, new int[] { R.id.layout_contact_list_item_imageview_head }, String.valueOf(position));
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.mTextViewName.setText(item.getName());
        holder.mTextViewPhone.setText(item.getPhoneNumber());
        return convertView;
    }

    @Override
    public void OnScrolling(boolean isScrolling)
    {
        mIsScrolling = isScrolling;
    }

    private class ViewHolder extends ImageViewLoadingCache
    {
        private TextView mTextViewName, mTextViewPhone;

        public ViewHolder(View baseView, int[] imageIds, String id)
        {
            super(baseView, imageIds, id);
            mTextViewName = (TextView)baseView.findViewById(R.id.layout_contact_list_item_textview_name);
            mTextViewPhone = (TextView)baseView.findViewById(R.id.layout_contact_list_item_textview_phone);
        }

    }
}
