
package com.example.lin.boylove.custom.messages;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.AttributeSet;


/**
 * Component for displaying list of messages
 */
public class MessagesList extends RecyclerView {
    private MessagesListStyle messagesListStyle;

    public MessagesList(Context context) {
        super(context);
    }

    public MessagesList(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        parseStyle(context, attrs);
    }

    public MessagesList(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        parseStyle(context, attrs);
    }

    public void setAdapter(MessagesListAdapter adapter) {
        setAdapter(adapter, true);
    }

    public void setAdapter(MessagesListAdapter adapter, boolean reverseLayout) {
        SimpleItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setSupportsChangeAnimations(false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, reverseLayout);

        setItemAnimator(itemAnimator);
        setLayoutManager(layoutManager);
        adapter.setLayoutManager(layoutManager);
        adapter.setStyle(messagesListStyle);

        addOnScrollListener(new RecyclerScrollMoreListener(layoutManager, adapter));
        super.setAdapter(adapter);
    }

    @SuppressWarnings("ResourceType")
    private void parseStyle(Context context, AttributeSet attrs) {
        messagesListStyle = MessagesListStyle.parse(context, attrs);
    }
}
