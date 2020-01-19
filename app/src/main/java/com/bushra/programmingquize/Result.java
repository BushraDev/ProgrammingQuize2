package com.bushra.programmingquize;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Result extends AppCompatActivity {
    TextView finalScore,finalTA,finalFA;

    Button home;
    ImageView star;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        finalScore=findViewById(R.id.final_score);
        finalTA=findViewById(R.id.final_t_a);
        finalFA=findViewById(R.id.final_f_a);
        home=findViewById(R.id.home_btn);
        star=findViewById(R.id.winner);

        finalScore.setText("نتيجتك هي : "+getIntent().getStringExtra("score"));
        finalTA.setText("لقد نجحت في إجابة "+getIntent().getStringExtra("tA")+" سؤال");
        finalFA.setText(" لقد أخفقت في إجابة "+getIntent().getStringExtra("fA")+" سؤال");

        if((getIntent().getIntExtra("wof",1))==6)
        star.setImageResource(R.drawable.star);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Result.this,Home.class);
                startActivity(i);
            }
        });
    }
}
