package com.bushra.programmingquize;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Parcelable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Question extends AppCompatActivity
{

    Toast toast;
    ImageView img;
    TextView name,score,question,tATv,fATv;
    Button tABtn,fABtn,shBtn;
    QuestionModel questionText[]= new QuestionModel[]{};
    QuestionModel questions[]= new QuestionModel[]{};
    int random ;
    boolean genderVal=true, easy=false, mid = false , dif=false,add,sheat=false;
    int scoreVal=0 , qurrentIndex=0,ta=0,fa=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);


        img=findViewById(R.id.img);
        name=findViewById(R.id.name_tv);
        score=findViewById(R.id.grade_tv);
        question=findViewById(R.id.question_tv);
        tATv=findViewById(R.id.t_ans_tv);
        fATv=findViewById(R.id.f_ans_tv);
        tABtn=findViewById(R.id.t_btn);
        fABtn=findViewById(R.id.f_btn);
        shBtn=findViewById(R.id.sheating_btn);
        final MediaPlayer m_p = MediaPlayer.create(this,R.raw.answering);
        m_p.start();


        genderVal =getIntent().getBooleanExtra("gender",true);
        if(genderVal == true)
        {
            img.setImageResource(R.drawable.m);
        }
        if(genderVal == false)
        {
            img.setImageResource(R.drawable.f);
        }

        name.setText(getIntent().getStringExtra("name"));

        Parcelable[] parcelables = getIntent().getParcelableArrayExtra("questionBank");
        questions= new QuestionModel[parcelables.length];
        for(int i = 0; i < parcelables.length; i++) {
            questions[i] = (QuestionModel) parcelables[i];
        }
        questionText= new QuestionModel[3];

        for(int i = 0; i < 3; i++) {
            add=false;
            random = new Random().nextInt((8 - 0) + 1) + 0;
            if (questions[random].getType() == 1 && easy==false) {
                questionText[i] = questions[random];
                add=true;
                easy=true;
            }
            if(questions[random].getType()==2 && mid==false)
            {
                questionText[i] = questions[random];
                add=true;
                mid=true;
            }

            if(questions[random].getType()==3 && dif==false)
            {
                questionText[i] = questions[random];
                add=true;
                dif=true;
            }
            if(add==false)
                i--;
        }

        question.setText(questionText[qurrentIndex].getmTextResId());

        tABtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });

        fABtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        shBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sheat=true;
                Intent in = new Intent(Question.this,Sheating.class);
                in.putExtra("t_a",questionText[qurrentIndex].ismAnswerTrue());
                startActivity(in);
            }
        });

    }

    private void checkAnswer(boolean userAnswerTrue) {
        boolean mAnswerTrue=questionText[qurrentIndex].ismAnswerTrue();
        int messageResId;
        if(userAnswerTrue==mAnswerTrue)
        {
            final MediaPlayer mp = MediaPlayer.create(this,R.raw.correct);
            messageResId=R.string.t_res;
            mp.start();
            if(sheat==false)
            {

                if(questionText[qurrentIndex].getType()==1)
                    scoreVal++;
                if(questionText[qurrentIndex].getType()==2)
                    scoreVal=scoreVal+2;
                if(questionText[qurrentIndex].getType()==3)
                    scoreVal=scoreVal+3;
                score.setText("النتيجة : "+scoreVal);
                ta++;
                tATv.setText("    عدد الإجابات الصحيحة : "+ta);
                updateQuestion();
            }
            else
            updateQuestion();
        }
        else
        {
            final MediaPlayer mp = MediaPlayer.create(this,R.raw.wrong);
            mp.start();
            messageResId=R.string.f_res;
            fa++;
            fATv.setText("    عدد الإجابات الخاطئة : "+fa);
            updateQuestion();
        }

        toast= Toast.makeText(getApplicationContext(),
                messageResId, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();


    }
    private void updateQuestion() {
        if(qurrentIndex==questionText.length-1)
        {
            Intent i = new Intent(Question.this,Result.class);
            i.putExtra("score",scoreVal+"");
            i.putExtra("tA",ta+"");
            i.putExtra("fA",fa+"");
            i.putExtra("wof",scoreVal);
            startActivity(i);

        }
        else
        {
            qurrentIndex++;
            question.setText(questionText[qurrentIndex].getmTextResId());
            sheat=false;
        }

    }

}
