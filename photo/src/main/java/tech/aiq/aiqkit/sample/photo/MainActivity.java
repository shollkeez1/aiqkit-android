package tech.aiq.aiqkit.sample.photo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import rx.Observable;
import tech.aiq.kit.AIQKit;

public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnClick(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, getString(R.string.pick_image)), 0);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            if (uri == null) {
                Log.e(TAG, "No uri returned, cannot search");
                return;
            }

            Observable<AIQKit.MatchResult> result = AIQKit.matchImage(uri);
            // use blocking mode to wait for the result
            try {
                AIQKit.MatchResult matchResult = result.toBlocking().first();
                if (matchResult != null) {
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(matchResult.getPayload()));
                    startActivity(myIntent);
                }
            } catch (Throwable t) {
                showToast("No match found");

            }
        }
    }

    private void showToast(String text) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}
