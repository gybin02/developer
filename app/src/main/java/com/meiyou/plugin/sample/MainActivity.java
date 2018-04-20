package com.meiyou.plugin.sample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.meiyou.jet.annotation.JOnClick;
import com.meiyou.jet.process.Jet;


public class MainActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

//    @JFindViewOnClick(R.id.button)
//    Button button;
//
//    @JFindViewOnClick(R.id.button)
//    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Jet.bind(this);

//        Rocket.init(this)
//              .inject(AppRocketConfig.class);

//           .setBeeSize(100)
//           .setBeePosition(Gravity.CENTER)
//           .setBeeMargin(0, 0, 0, 200)


//        BeeLog.d("MainActivity", "onCreate");
//        BeeLog.d("MainActivity", "user logged in");
//        BeeLog.d("MainActivity", "user logged out");
//        BeeLog.d("MainActivity", "onDestroyed");
    }

    @JOnClick(all = {R.id.button, R.id.btn_addview})
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.button:
                Log.d(TAG, "onClickButton: ");
//                Intent intent = new Intent(this, DetailActivity.class);
//                startActivity(intent);

//                Intent intent = new Intent(this, Main2Activity.class);
//                startActivity(intent);
                break;
            case R.id.btn_addview:
                addView();
                break;
            default:
                break;
        }
    }


    private void addView() {
        final ViewGroup rootView = (ViewGroup) this.getWindow().getDecorView();
//        LayoutInflater inflater = LayoutInflater.from(context);
        View view = View.inflate(this, com.meiyou.jet.developer.R.layout.container, null);

        view.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e
                (TAG, "onClick: ");
                rootView.removeView(v);
            }
        });
        rootView.addView(view);
    }
}
