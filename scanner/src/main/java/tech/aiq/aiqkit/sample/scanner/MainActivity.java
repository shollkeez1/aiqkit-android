package tech.aiq.aiqkit.sample.scanner;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import tech.aiq.kit.AIQKit;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnClick(View view) {
        AIQKit.startScanner(this);
    }

}
