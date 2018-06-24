package com.example.lin.boylove.fragment.Chat;

import android.net.Uri;
import android.os.Bundle;

import com.example.lin.boylove.fragment.DxBaseFragment;
import com.example.lin.boylove.utilities.Utils;


public class ChatFragment extends DxBaseFragment {

    private OnFragmentInteractionListener mListener;
    public static ChatFragment instance;

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
    }

    @Override
    protected int getLayoutResource() {
        return 0;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initAttributes() {

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void setMessageResponse(final String message) {
        ChatFragment.this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Utils.showToast(mContext, message);
            }
        });
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onPause() {
        super.onPause();
        instance = null;
    }
}
