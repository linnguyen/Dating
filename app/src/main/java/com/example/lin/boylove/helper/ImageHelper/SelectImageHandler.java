package com.example.lin.boylove.helper.ImageHelper;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;

import com.example.lin.boylove.utilities.GrantPermissionUtils;
import com.example.lin.boylove.utilities.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.iwf.photopicker.PhotoPicker;

/**
 * Created by lin on 04/09/2018.
 */

public abstract class SelectImageHandler implements ISelectImageView, DecreaseImageSizeAsyncTask.OnDecreaseFinishListener {
    private ISelectImageListener listener;
    private boolean isSelectMultipleImage;

    public SelectImageHandler(ISelectImageListener listener) {
        this.listener = listener;
        isSelectMultipleImage = false;
    }

    abstract Activity getActivity();

    protected static final int PICK_IMAGE_REQUEST = 11;
    protected static final int TAKE_IMAGE_REQUEST = 22;
    protected static final int PICK_MULTIPLE_IMAGE_REQUEST = PhotoPicker.REQUEST_CODE;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CAMERA_PERMISSION:
                if (GrantPermissionUtils.isPermissionGrantedSuccessfully(grantResults)) {
                    dispatchTakePictureIntent();
                }
                break;
            case PICK_IMAGE_PERMISSION:
                if (GrantPermissionUtils.isPermissionGrantedSuccessfully(grantResults)) {
                    if (isSelectMultipleImage) {
                        pickMultipleImageFromLibrary();
                    } else {
                        pickImageFromLibrary();
                    }
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PICK_IMAGE_REQUEST:
                    Uri uri = data.getData();
                    String tempPath = null;
                    String url = data.getData().toString();
                    if (url.startsWith("content://com.google.android.apps.photos.content")) {
                        try {
                            InputStream is = getActivity().getContentResolver().openInputStream(uri);
                            if (is != null) {
                                Bitmap pictureBitmap = BitmapFactory.decodeStream(is);
                                try {
                                    tempPath = getImagePath(pictureBitmap);
                                } catch (IOException | NullPointerException e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (FileNotFoundException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    } else {
                        tempPath = getRealPathFromURIPath(uri);
                    }

                    if (tempPath != null) {
                        new DecreaseImageSizeAsyncTask(tempPath, this).execute();
                    } else {
                        if (listener != null) {
                            listener.onSelectImageError();
                        }
                    }
                    break;

                case TAKE_IMAGE_REQUEST:
                    galleryAddPic();
                    break;

                case PICK_MULTIPLE_IMAGE_REQUEST:
                    if (data != null) {
                        ArrayList<String> photos =
                                data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                        new DecreaseImageSizeAsyncTask(photos, this).execute();
                    }
                    break;
            }
        }
    }

    private void galleryAddPic() {
        try {
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            File f = new File(mCurrentPhotoPath);
            Uri contentUri = Uri.fromFile(f);
            mediaScanIntent.setData(contentUri);
            getActivity().sendBroadcast(mediaScanIntent);
            // Post image
//        listener.onImagePathReturn(f.getPath());
            new DecreaseImageSizeAsyncTask(f.getPath(), this).execute();
        } catch (NullPointerException e) {
            e.printStackTrace();
            listener.onSelectImageError();
        }
    }

    private String getRealPathFromURIPath(Uri contentURI) {
        Cursor cursor = getActivity().getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }

    private String mCurrentPhotoPath;

    protected File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    protected String getImagePath(Bitmap bitmap) throws IOException {
        // Create an image file name
        File filesDir = getActivity().getFilesDir();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File imageFile = new File(filesDir, imageFileName + ".jpg");
        OutputStream os;
        os = new FileOutputStream(imageFile);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
        os.flush();
        os.close();
        return imageFile.getAbsolutePath();
    }

    @Override
    public void OnDecreaseFinished(List<String> originalImagePaths, List<String> decreaseImagePaths) {
        if (!Utils.isEmpty(decreaseImagePaths)) {
            if (listener != null) {
                listener.onImagePathReturn(decreaseImagePaths);
            }
        }
    }

    public void showSelectImageMethod() {
        isSelectMultipleImage = false;
//        String[] selections = getActivity().getResources().getStringArray(R.array.dialog_selections_image_method);
//        DialogUtil.showBottomSheetDialog(getActivity(), selections, true, new DialogUtil.OnBottomSheetDialogListener() {
//            @Override
//            public void onSelectBottomSheetOption(String option) {
//                if (option.equals(getActivity().getString(R.string.label_gallery))) {
//                    pickImageFromLibrary();
//                } else if (option.equals(getActivity().getString(R.string.label_camera))) {
//                    dispatchTakePictureIntent();
//                }
//            }
//        });
        pickImageFromLibrary();
    }

    public void showSelectMultipleImageMethod() {
        isSelectMultipleImage = true;
//        String[] selections = getActivity().getResources().getStringArray(R.array.dialog_selections_image_method);
//        DialogUtil.showBottomSheetDialog(getActivity(), selections, true, new DialogUtil.OnBottomSheetDialogListener() {
//            @Override
//            public void onSelectBottomSheetOption(String option) {
//                if (option.equals(getActivity().getString(R.string.label_gallery))) {
//                    pickMultipleImageFromLibrary();
//                } else if (option.equals(getActivity().getString(R.string.label_camera))) {
//                    dispatchTakePictureIntent();
//                }
//            }
//        });
    }
}
