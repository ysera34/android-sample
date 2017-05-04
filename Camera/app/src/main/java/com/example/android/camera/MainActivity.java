package com.example.android.camera;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton;
    private ImageView mImageView;

    private ImageHandler mImageHandler;

    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 1100;
    private static final int GALLERY_IMAGE_REQUEST_CODE = 2100;
    private static final int PERMISSION_EXTERNAL_STORAGE_REQUEST_CODE = 9101;
    private String[] mStoragePermissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);
        mImageView = (ImageView) findViewById(R.id.image_view);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                checkStoragePermission();
                break;
        }
    }

    private void checkStoragePermission() {
        String state = Environment.getExternalStorageState();
        if (!Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            Toast.makeText(getApplicationContext(), "SD 카드가 없으므로 취소 되었습니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), mStoragePermissions[0]) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(getApplicationContext(), mStoragePermissions[1]) != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, mStoragePermissions[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, mStoragePermissions[1])) {
                    // Should we show an explanation?

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("저장소 권한 요청합니다.")
                            .setMessage("프로필 이미지 변경을 위한 갤러리 접근 권한을 요청합니다.");
                    builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                requestPermissions(mStoragePermissions, PERMISSION_EXTERNAL_STORAGE_REQUEST_CODE);
                            }
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();

                } else {
                    // No explanation needed, we can request the permission.
                    requestPermissions(mStoragePermissions, PERMISSION_EXTERNAL_STORAGE_REQUEST_CODE);
                }

            } else {
                // already have permission
                callCamera();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSION_EXTERNAL_STORAGE_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
//                    Log.i(TAG + "granted", "Storage Permission has been granted by user : " +
//                            "PERMISSION_EXTERNAL_STORAGE_REQUEST_CODE : " + PERMISSION_EXTERNAL_STORAGE_REQUEST_CODE);
                    callCamera();
                } else {
//                    Log.i(TAG + "denied", "Storage Permission has been denied by user : " +
//                            "PERMISSION_EXTERNAL_STORAGE_REQUEST_CODE : " + PERMISSION_EXTERNAL_STORAGE_REQUEST_CODE);
                    Toast.makeText(getApplicationContext(), "권한이 없으므로 취소 되었습니다.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void callCamera() {
        mImageHandler = new ImageHandler(getApplicationContext());

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            Intent cameraIntent = new Intent();
//            cameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
//
//            File photoFile = null;
//            try {
//                photoFile = mImageHandler.setUpImageFile();
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//
//            String authorities = getApplicationContext().getPackageName() + ".fileprovider";
//            Uri imageUri = FileProvider.getUriForFile(this, authorities, photoFile);
//            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//
//
//            startActivityForResult(cameraIntent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
//        } else {
            startActivityForResult(mImageHandler.dispatchTakePictureIntent(), CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
//        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case CAMERA_CAPTURE_IMAGE_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    mImageHandler.handleCameraImage(mImageView);
                }
                break;
        }
    }
}
