package com.shuvam.customviewplayground;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.shuvam.customviewplayground.views.IndicatorView;

public class MainActivity extends AppCompatActivity {

    private IndicatorView mCustomView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final int [] res={R.drawable.ic_android_black_24dp,R.drawable.ic_archive_black_24dp,R.drawable.ic_audiotrack_black_24dp,R.drawable.ic_backup_black_24dp,R.drawable.ic_bluetooth_audio_black_24dp};
        mCustomView = (IndicatorView) findViewById(R.id.customView);
        mCustomView.setResources(res);




        mCustomView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                float section = (int)Math.floor(event.getX()*(res.length)/mCustomView.getWidth());

                Log.d("Touch event value",""+section);
                return false;
            }
        });
    }
}
