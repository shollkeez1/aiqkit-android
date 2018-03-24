package tech.aiq.aiqkit.sample.bitmap;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import tech.aiq.kit.AIQKit;

public class BitmapApplication extends Application {
    private static final String TAG = BitmapApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "BitmapApplication Started");

        String serverUrl = BuildConfig.AIQ_APP_SERVER;

        if (TextUtils.isEmpty(serverUrl)) {
            serverUrl = AIQKit.getServiceEndPoint(AIQKit.ServiceType.STAG);
            Log.d(TAG, "Using AIQ staging server");
        }

        //initialize aiqkit
        AIQKit.init(this, BuildConfig.AIQ_APP_ID, BuildConfig.AIQ_APP_SECRET, serverUrl);
        Log.d(TAG, "Initialized AIQKit SDK");
    }
}
