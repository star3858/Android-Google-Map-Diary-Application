package com.example.star3.takehomeexam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements Test1Fragment.OnListViewClickedListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button write = (Button) findViewById(R.id.write);
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WritingActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onListViewClick(View v, double x_value, double x_value2) {
        Test2Fragment fragment = (Test2Fragment) getFragmentManager().findFragmentById(R.id.test2_fragment);
        if (fragment != null) {
            fragment.showMessage(x_value, x_value2);
        }
    }
}
