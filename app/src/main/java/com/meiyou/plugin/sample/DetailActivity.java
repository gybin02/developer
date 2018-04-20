package com.meiyou.plugin.sample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class DetailActivity extends Activity {

    private static final String TAG = "DetailActivity";
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container);
        View viewById = findViewById(R.id.close);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: ");
//                DetailActivity.this.finish();
            }
        });
    }
}
