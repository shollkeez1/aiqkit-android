package tech.aiq.aiqkit.sample.bitmap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import tech.aiq.kit.AIQKit;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnClick(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
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
                            // check if the result is "no match found'
                            if (AIQKit.isImageNotFoundError(throwable)) {
                                showToast("No match found");
                            } else {
                                showToast("Error: " + throwable.getMessage());
                            }
                        }
                    }
            );
        }
    }


    private void showToast(String text) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}
