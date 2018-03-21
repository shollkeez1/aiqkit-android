package tech.aiq.aiqkit.sample.scanner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import tech.aiq.kit.AIQKit;

public class MainActivity extends AppCompatActivity {
    static private final String TAG=MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeAiqKit();
    }


    private void initializeAiqKit()
    {
        String serverUrl = BuildConfig.AIQ_APP_SERVER;

        if (TextUtils.isEmpty(serverUrl)) {
            serverUrl = AIQKit.getServiceEndPoint(AIQKit.ServiceType.STAG);
            Log.d(TAG, "using default server");
        }
        // initialize the image match service
        AIQKit.init(this, BuildConfig.AIQ_APP_ID, BuildConfig.AIQ_APP_SECRET, serverUrl);

    }
    public void OnClick(View view) {
        AIQKit.startScanner(this);
    }

}
