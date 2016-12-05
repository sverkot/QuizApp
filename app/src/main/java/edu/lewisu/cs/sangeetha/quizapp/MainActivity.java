package edu.lewisu.cs.sangeetha.quizapp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements OnAnswerListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ScoreFragment scoreFragment = new ScoreFragment();
        QuestionFragment questionFragment = new QuestionFragment();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.activity_main, scoreFragment, "score_fragment");
        ft.add(R.id.activity_main, questionFragment, "question_fragment");
        ft.commit();

    }

    @Override
    public void update(int score) {
        FragmentManager fm = getSupportFragmentManager();
        ScoreFragment sf = (ScoreFragment) fm.findFragmentByTag("score_fragment");
        sf.updateScore(score);
    }

}
