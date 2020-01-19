package com.bushra.programmingquize;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    Toast toast;

    Button cat1Btn,
            cat2Btn,
            cat3Btn,
            startBtn;
    RadioGroup radioGender;

    EditText nameTv=null;

    QuestionModel questionText[];

     boolean genderVal,checked=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cat1Btn=findViewById(R.id.cat1_btn);
        cat2Btn=findViewById(R.id.cat2_btn);
        cat3Btn=findViewById(R.id.cat3_btn);
        startBtn=findViewById(R.id.start_btn);

        radioGender=findViewById(R.id.radio_gender);


        nameTv=findViewById(R.id.name_tv);



        cat1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cat2Btn.setEnabled(false);
                cat3Btn.setEnabled(false);
                checked=true;
                questionText= new QuestionModel[]
                        {
                                new QuestionModel(R.string.pq1_txt,false,1),
                                new QuestionModel(R.string.pq2_txt,false,2),
                                new QuestionModel(R.string.pq3_txt,true,3),
                                new QuestionModel(R.string.pq4_txt,false,1),
                                new QuestionModel(R.string.pq5_txt,true,2),
                                new QuestionModel(R.string.pq6_txt,true,3),
                                new QuestionModel(R.string.pq7_txt,true,1),
                                new QuestionModel(R.string.pq8_txt,true,2),
                                new QuestionModel(R.string.pq9_txt,true,3),

                        };
            }
        });

        cat2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cat1Btn.setEnabled(false);
                cat3Btn.setEnabled(false);
                checked=true;
                questionText= new QuestionModel[]
                        {
                                new QuestionModel(R.string.dq1_txt,false,1),
                                new QuestionModel(R.string.dq2_txt,false,2),
                                new QuestionModel(R.string.dq3_txt,true,3),
                                new QuestionModel(R.string.dq4_txt,true,1),
                                new QuestionModel(R.string.dq5_txt,true,2),
                                new QuestionModel(R.string.dq6_txt,true,3),
                                new QuestionModel(R.string.dq7_txt,false,1),
                                new QuestionModel(R.string.dq8_txt,true,2),
                                new QuestionModel(R.string.dq9_txt,false,3),

                        };
            }
        });

        cat3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cat1Btn.setEnabled(false);
                cat2Btn.setEnabled(false);
                checked=true;
                questionText= new QuestionModel[]
                        {
                                new QuestionModel(R.string.nq1_txt,false,1),
                                new QuestionModel(R.string.nq2_txt,true,2),
                                new QuestionModel(R.string.nq3_txt,false,3),
                                new QuestionModel(R.string.nq4_txt,false,1),
                                new QuestionModel(R.string.nq5_txt,false,2),
                                new QuestionModel(R.string.nq6_txt,true,3),
                                new QuestionModel(R.string.nq7_txt,true,1),
                                new QuestionModel(R.string.nq8_txt,true,2),
                                new QuestionModel(R.string.nq9_txt,false,3),

                        };
            }
        });




        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                if(nameTv.getText().toString().matches("") )
                {
                    toast= Toast.makeText(getApplicationContext(),
                            "أدخل اسمك ", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP, 0, 0);
                    toast.show();

                }
                else
                {
                    if(checked==false)
                    {
                        toast= Toast.makeText(getApplicationContext(),
                                "قم باختيار نوع الاختبار ", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP, 0, 0);
                        toast.show();


                    }
                    else if (nameTv!=null && checked ==true)
                    {
                        int selectedId = radioGender.getCheckedRadioButtonId();
                        if(selectedId==(R.id.radioMale))
                        {
                            genderVal=true;
                        }

                        else if(selectedId==(R.id.radioFemale))
                        {
                            genderVal=false;
                        }

                        Intent i = new Intent(Home.this,Question.class);
                        i.putExtra("name",nameTv.getText().toString());
                        i.putExtra("gender",genderVal);
                        i.putExtra("questionBank",questionText);

                        startActivity(i);

                    }
                }



            }
        });
    }
}
