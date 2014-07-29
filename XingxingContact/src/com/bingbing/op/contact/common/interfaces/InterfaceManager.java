package com.bingbing.op.contact.common.interfaces;


public class InterfaceManager
{
    /**
     * Interface to implement when you want to get notified of refreshing state events. Call
     * {@link CommonPullRefreshListView#setOnRefreshStateChangedListener(OnRefreshStateChangedListener)}
     * to activate an {@link OnRefreshStateChangedListener}.
     */
    public static interface OnScrollingStateListener
    {

        /**
         * Method to be called when a refresh is requested
         */
        public void OnScrolling(boolean isScrolling);
    }
}
