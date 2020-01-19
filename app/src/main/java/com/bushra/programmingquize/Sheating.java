package com.bushra.programmingquize;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Sheating extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheating);

        TextView t1;
        t1=findViewById(R.id.sh_a);
        t1.setText(getIntent().getBooleanExtra("t_a",true)+"");
    }
}
