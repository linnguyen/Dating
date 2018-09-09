package com.example.lin.boylove.utilities;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lin on 04/09/2018.
 */

public class GrantPermissionUtils {
    public static boolean isPermissionGrantedSuccessfully(@NonNull int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //Permission Granted Successfully. Write working code here.
            return true;
        } else {
            //You did not accept the request can not use the functionality.
            return false;
        }
    }

    public static boolean checkCameraPermission(Activity activity, int requestCode) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        } else {
            int permissionWRITEEXTERNAL = ContextCompat.checkSelfPermission(activity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int permissionCAMERA = ContextCompat.checkSelfPermission(activity,
                    Manifest.permission.CAMERA);
            List<String> listPermissionsNeeded = new ArrayList<>();

            if (permissionWRITEEXTERNAL != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }

            if (permissionCAMERA != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.CAMERA);
            }

            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(activity, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), requestCode);
                return false;
            }
            return true;
        }
    }

    public static boolean checkPickPhotoPermission(Activity activity, int requestCode) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        } else {
            int permissionWRITEEXTERNAL = ContextCompat.checkSelfPermission(activity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int permissionREADEXTERNAL = ContextCompat.checkSelfPermission(activity,
                    Manifest.permission.READ_EXTERNAL_STORAGE);
            List<String> listPermissionsNeeded = new ArrayList<>();
            if (permissionWRITEEXTERNAL != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (permissionREADEXTERNAL != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(activity, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), requestCode);
                return false;
            }
            return true;
        }
    }

    public static boolean checkCameraPermission(Fragment fragment, int requestCode) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        } else {
            int permissionWRITEEXTERNAL = ContextCompat.checkSelfPermission(fragment.getContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int permissionCAMERA = ContextCompat.checkSelfPermission(fragment.getContext(),
                    Manifest.permission.CAMERA);
            List<String> listPermissionsNeeded = new ArrayList<>();
            if (permissionWRITEEXTERNAL != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (permissionCAMERA != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.CAMERA);
            }
            if (!listPermissionsNeeded.isEmpty()) {
                fragment.requestPermissions(listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), requestCode);
                return false;
            }
            return true;
        }
    }

    public static boolean checkPickPhotoPermission(Fragment fragment, int requestCode) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        } else {
            int permissionREADEXTERNAL = ContextCompat.checkSelfPermission(fragment.getContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE);
            int permissionWRITEEXTERNAL = ContextCompat.checkSelfPermission(fragment.getContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);
            List<String> listPermissionsNeeded = new ArrayList<>();
            if (permissionREADEXTERNAL != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
            if (permissionWRITEEXTERNAL != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (!listPermissionsNeeded.isEmpty()) {
                fragment.requestPermissions(listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), requestCode);
                return false;
            }
            return true;
        }
    }
}
