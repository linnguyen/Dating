package com.example.lin.boylove.fragment.Profile;

import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lin.boylove.R;
import com.example.lin.boylove.entity.Response.User;
import com.example.lin.boylove.fragment.DxBaseFragment;
import com.example.lin.boylove.utilities.GlideUtils;
import com.example.lin.boylove.utilities.Utils;

import butterknife.BindView;

/**
 * Created by lin on 20/08/2017.
 */

public class ProfileFragment extends DxBaseFragment implements ProfileView {
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.imv_user_profile)
    ImageButton imv_user_profile;

    private ProfilePresenter presenter;

    public ProfileFragment() {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void initViews() {
        setupLstOnline();
        getLstOnline();
    }

    @Override
    protected void initAttributes() {
        presenter = new ProfilePresenterIml(mContext, this);
    }

    private void setupLstOnline() {

    }

    private void getLstOnline() {
        presenter.getUserProfile();
    }

    @Override
    public void showProgressBar() {
    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void showMessage(String message) {
        Utils.showToast(mContext, message);
    }

    @Override
    public void onGetUserProfileSuccess(User user) {
//         it should be the full name here
        tvName.setText(user.getEmail());
        GlideUtils.loadImageAvatar(mContext, user.getAvatar(), imv_user_profile);
    }
}
