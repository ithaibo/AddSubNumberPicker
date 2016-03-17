package com.ithaibo.addsubnumberpicker;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.ithaibo.library.AddSubNumberPicker;

public class MainActivity extends Activity {

    private AddSubNumberPicker picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        picker = (AddSubNumberPicker)findViewById(R.id.picker);
        picker.setCountChangeListner(new AddSubNumberPicker.CountChangeListner() {
            @Override
            public void onCountChanged(int count) {
                Log.d("MainActivity", "count changed, count = "+count);
            }
        });
        picker.setTypeCountLimitAndToasLimittStr(AddSubNumberPicker.TYPE_UNSIGN_NUMBER, "不能小于0");
    }

}
