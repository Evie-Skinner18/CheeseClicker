package com.eviesexcellentapps.cheeseclicker;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        set up the var that will be the cheese
        ImageView cheese = findViewById(R.id.imageCheese);

        // create onclick listener for the cheese and tell Java what to do when the cheese gets clicked
        cheese.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "hello!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
