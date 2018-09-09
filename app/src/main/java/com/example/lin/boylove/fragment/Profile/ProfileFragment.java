package com.example.lin.boylove.fragment.Profile;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lin.boylove.R;
import com.example.lin.boylove.entity.Response.User;
import com.example.lin.boylove.fragment.DxBaseFragment;
import com.example.lin.boylove.helper.ImageHelper.ISelectImageView;
import com.example.lin.boylove.helper.ImageHelper.SelectImageAsFragment;
import com.example.lin.boylove.helper.UploadImage.ImageUploadPresenter;
import com.example.lin.boylove.helper.UploadImage.ImageUploadPresenterIml;
import com.example.lin.boylove.helper.UploadImage.ImageUploadView;
import com.example.lin.boylove.utilities.Constant;
import com.example.lin.boylove.utilities.GlideUtils;
import com.example.lin.boylove.utilities.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by lin on 20/08/2017.
 */

public class ProfileFragment extends DxBaseFragment implements
        ProfileView, ISelectImageView.ISelectImageListener, ImageUploadView {
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.imv_user_profile)
    CircleImageView civUserProfile;

    private ProfilePresenter presenter;
    private ImageUploadPresenter uploadPresenter;
    private SelectImageAsFragment selectImageView;

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
        uploadPresenter = new ImageUploadPresenterIml(mContext, this);
        selectImageView = new SelectImageAsFragment(this, this);
    }

    @OnClick(R.id.imv_user_profile)
    public void onImageClicked() {
        selectImageView.showSelectImageMethod();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (selectImageView != null) {
            selectImageView.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (selectImageView != null) {
            selectImageView.onActivityResult(requestCode, resultCode, data);
        }
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
    public void onUploadImageSuccess(String imagePath) {
        GlideUtils.loadImageAvatar(mContext, imagePath, civUserProfile);
        Log.i("IMAGE_PATH", Constant.Config.URL_IMAGE + imagePath);
    }

    @Override
    public void showMessage(String message) {
        Utils.showToast(mContext, message);
    }

    @Override
    public void onGetUserProfileSuccess(User user) {
        tvName.setText(user.getUserName());
        GlideUtils.loadImageAvatar(mContext, user.getAvatar(), civUserProfile);
    }

    @Override
    public void onImagePathReturn(List<String> paths) {
        uploadPresenter.uploadImage(paths.get(0));
    }

    @Override
    public void onSelectImageError() {

    }
}
