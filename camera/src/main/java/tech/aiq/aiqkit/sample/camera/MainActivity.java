package tech.aiq.aiqkit.sample.camera;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Toast;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import tech.aiq.kit.AIQKit;

public class MainActivity extends Activity {

    private static final int REQUEST_PERMISSIONS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnClick(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            if (!hasPermission(Manifest.permission.CAMERA)) {
                ActivityCompat.requestPermissions(this, requestedPermissions(), REQUEST_PERMISSIONS);
            } else {
                startActivityForResult(intent, 0);
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
            //scale up the bitmap large enough for testing,
            imageBitmap = Bitmap.createScaledBitmap(imageBitmap, imageBitmap.getWidth() * 3, imageBitmap.getHeight() * 3, true);
            Observable<AIQKit.MatchResult> result = AIQKit.matchImage(imageBitmap);

            result.observeOn(AndroidSchedulers.mainThread()).subscribe(
                    new Action1<AIQKit.MatchResult>() {
                        @Override
                        public void call(AIQKit.MatchResult result) {
                            // take the returned payload as url and show it in browser window
                            if (result != null) {
                                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(result.getPayload()));
                                startActivity(myIntent);
                            } else {
                                showToast("No match found");
                            }
                        }
                    },
                    new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            showToast("No match found");
                        }
                    }
            );
        }
    }


    private void showToast(String text) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    //--------------------------------------------------------------
    //Request runtime permission
    //--------------------------------------------------------------
    private boolean hasPermission(String perm) {
        return ActivityCompat.checkSelfPermission(this, perm) == PackageManager.PERMISSION_GRANTED;
    }

    private String[] requestedPermissions() {
        return new String[]{Manifest.permission.CAMERA};
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSIONS) {
            if (!hasPermission(Manifest.permission.CAMERA)) {
                finish();
            } else {
                startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), 0);
            }
        }
    }
}
