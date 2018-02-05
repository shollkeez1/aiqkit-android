package tech.aiq.example;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import tech.aiq.kit.AIQKit;

public class AIQKitExampleApplication extends Application {

    private static final String TAG = AIQKitExampleApplication.class.getSimpleName();


    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "application started");

        String serverUrl = BuildConfig.AIQ_APP_SERVER;

        if (TextUtils.isEmpty(serverUrl)) {
            serverUrl = AIQKit.getServiceEndPoint(AIQKit.ServiceType.PROD);
            Log.d(TAG, "using default server");
        }
        // initialize the image match service
        AIQKit.init(this, BuildConfig.AIQ_APP_ID, BuildConfig.AIQ_APP_SECRET, serverUrl);
        Log.d(TAG, "instantiated image match sdk ");
    }

}
