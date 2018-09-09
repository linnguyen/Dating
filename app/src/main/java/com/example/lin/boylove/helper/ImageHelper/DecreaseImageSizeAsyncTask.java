package com.example.lin.boylove.helper.ImageHelper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.AsyncTask;
import android.os.Environment;

import com.example.lin.boylove.utilities.Constant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by lin on 04/09/2018.
 */

public class DecreaseImageSizeAsyncTask extends AsyncTask<Void, Long, ArrayList<String>> {
    List<String> imagePaths;
    private OnDecreaseFinishListener listener;

    public DecreaseImageSizeAsyncTask(List<String> imagePaths, OnDecreaseFinishListener listener) {
        this.imagePaths = imagePaths;
        this.listener = listener;
    }

    public DecreaseImageSizeAsyncTask(String imagePath, OnDecreaseFinishListener listener) {
        this.imagePaths = new ArrayList<>();
        imagePaths.add(imagePath);
        this.listener = listener;
    }

    @Override
    protected ArrayList<String> doInBackground(Void... params) {
        ArrayList<String> listDecreaseImagePath = new ArrayList<>();
        for (int i = 0; i < imagePaths.size(); i++) {
            String imagePath = decreaseImageSize(imagePaths.get(i), i);
            listDecreaseImagePath.add(imagePath);
        }
        return listDecreaseImagePath;
    }

    @Override
    protected void onPostExecute(ArrayList<String> list) {
        super.onPostExecute(list);
        listener.OnDecreaseFinished(imagePaths, list);
    }

    public String decreaseImageSize(String imagePath, int pos) {

        File mFile = new File(imagePath);
        Bitmap scaled = null;
        Bitmap decreaseBitmap = null;
        try {
            scaled = decodeSampledBitmapFromResourceMemOpt(new FileInputStream(mFile)/*, o.outWidth / scale, o.outHeight / scale*/);
            if (scaled != null) {
                Matrix mat = new Matrix();
                mat.postRotate(getOrientationFromExif(imagePath));
                decreaseBitmap = Bitmap.createBitmap(scaled, 0, 0, scaled.getWidth(), scaled.getHeight(), mat, true);
            } else {
                return Constant.EMPTY;
            }
        } catch (FileNotFoundException | OutOfMemoryError e) {
            e.printStackTrace();
            return Constant.EMPTY;
        }
        imagePath = getPathFromResultData(decreaseBitmap, pos);
        return imagePath;
    }

    public Bitmap decodeSampledBitmapFromResourceMemOpt(
            InputStream inputStream/*, int reqWidth, int reqHeight*/) {

        byte[] byteArr = new byte[0];
        byte[] buffer = new byte[1024];
        int len;
        int count = 0;

        try {
            while ((len = inputStream.read(buffer)) > -1) {
                if (len != 0) {
                    if (count + len > byteArr.length) {
                        byte[] newbuf = new byte[(count + len) * 2];
                        System.arraycopy(byteArr, 0, newbuf, 0, count);
                        byteArr = newbuf;
                    }

                    System.arraycopy(buffer, 0, byteArr, count, len);
                    count += len;
                }
            }

            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(byteArr, 0, count, options);

            options.inSampleSize = calculateInSampleSize(options/*, reqWidth,
                    reqHeight*/);
            options.inJustDecodeBounds = false;
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            return BitmapFactory.decodeByteArray(byteArr, 0, count, options);

        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public static int calculateInSampleSize(BitmapFactory.Options options/*, int reqWidth, int reqHeight*/) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        // The new size we want to scale to
        final int REQUIRED_SIZE = 768;

        if (height >= width) {
            if (height > REQUIRED_SIZE) {
                inSampleSize = Math.round((float) (height / REQUIRED_SIZE));
            }
        } else {
            if (width > REQUIRED_SIZE) {
                inSampleSize = Math.round((float) (width / REQUIRED_SIZE));
            }
        }
        return inSampleSize;
    }


    public static String getPathFromResultData(Bitmap bitmap, int pos) {

        Calendar calendar = Calendar.getInstance();
        File directoryFolder = new File(Environment.getExternalStorageDirectory(), Constant.IMAGE_FOLDER_PATH);
        directoryFolder.mkdirs();
        try {
            OutputStream fOut;
            File file = new File(directoryFolder.getAbsolutePath(), "temp_" + calendar.getTimeInMillis() + "_" + pos + ".jpg");
            file.createNewFile();
            fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
            return file.getAbsolutePath();
        } catch (Exception e) {
            return Constant.EMPTY;
        }
    }

    private static int getOrientationFromExif(String imagePath) {
        int orientation = 0;
        try {
            ExifInterface exif = new ExifInterface(imagePath);
            int exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);

            switch (exifOrientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    orientation = 270;

                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    orientation = 180;

                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    orientation = 90;

                    break;

                case ExifInterface.ORIENTATION_NORMAL:
                    orientation = 0;

                    break;
                default:
                    break;
            }
        } catch (IOException e) {
        }

        return orientation;
    }

    public interface OnDecreaseFinishListener {
        void OnDecreaseFinished(List<String> originalImagePaths, List<String> decreaseImagePaths);
    }
}
