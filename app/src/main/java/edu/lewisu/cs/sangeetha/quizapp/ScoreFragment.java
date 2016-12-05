package edu.lewisu.cs.sangeetha.quizapp;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScoreFragment extends Fragment {


    int score = 0;
    TextView correctTextView;
    TextView scoreTextView;

    public ScoreFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        correctTextView = (TextView)getActivity().findViewById(R.id.correct_textview);
        scoreTextView = (TextView) getActivity().findViewById(R.id.score_textview);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_score, container, false);
    }

    public void updateScore(int questionScore)  {
        Resources res = getActivity().getResources();
        if(questionScore == 0)   {
            correctTextView.setText(R.string.not_correct);
        } else {
            correctTextView.setText(R.string.correct_textview);
            score += questionScore;
        }
        scoreTextView.setText(res.getString(R.string.score_label)+" "+score);
    }

}
