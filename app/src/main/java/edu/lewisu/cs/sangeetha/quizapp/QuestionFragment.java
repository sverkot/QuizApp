package edu.lewisu.cs.sangeetha.quizapp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment {


    ArrayList<String> questions = new ArrayList<String>();
    ArrayList<String> answers = new ArrayList<String>();

    TextView questionView;
    TextView scoreTextView;
    EditText answerBox;
    Button checkButton;
    Button exitButton;

    int questNum = 0;
    OnAnswerListener onAnswerListener;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        questionView = (TextView) getActivity().findViewById(R.id.question_textView);
        answerBox = (EditText) getActivity().findViewById(R.id.answer_field);
        checkButton = (Button) getActivity().findViewById(R.id.check_answer_button);

        scoreTextView = (TextView) getActivity().findViewById(R.id.score_textview);
        Collections.addAll(questions, getResources().getStringArray(R.array.questions));
        Collections.addAll(answers, getResources().getStringArray(R.array.answers));
        int min=0;
        int max = questions.size();
        questNum = min + (int)(Math.random() * max);
        questionView.setText(questions.get(questNum));
        CheckAnswer checkAnswer = new CheckAnswer();
        checkButton.setOnClickListener(checkAnswer);

        exitButton = (Button) getActivity().findViewById(R.id.exit_button);
        ExitApp exitApp = new ExitApp();

        exitButton.setOnClickListener(exitApp);
    }

    public QuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false);
    }

     private class CheckAnswer implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            int score = 0;

            String answerText = answerBox.getText().toString().toLowerCase();
            String correctAnswer = answers.get(questNum).toLowerCase();

            if (answerText.equals(correctAnswer)) {
                score = 1;
            }
            //questNum++;
            questNum = (int)(Math.random() * questions.size());
            if (questNum < questions.size()) {
                questionView.setText(questions.get(questNum));
                answerBox.setText("");
            }
            onAnswerListener.update(score);
        }
    }

     private class ExitApp implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            Toast.makeText(getActivity().getApplicationContext(),scoreTextView.getText() + " points!", Toast.LENGTH_SHORT).show();
            getActivity().finish();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            onAnswerListener = (OnAnswerListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnAnswerListener");

        }
    }


}
