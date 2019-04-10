package com.refreshmymemory.view_presenter.quiz;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.refreshmymemory.R;
import com.refreshmymemory.model.MultipleChoiceQuestion;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MCQuizQuestionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MCQuizQuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MCQuizQuestionFragment extends Fragment {
    private static final String TAG = "MCQuizQuestionFragment*";
    private int questionIndex;
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private int userAnswerIndex;

    private TextView userAnswerContainer;

    private OnFragmentInteractionListener listener;

    public MCQuizQuestionFragment() {
        // Required empty public constructor
    }

    public static MCQuizQuestionFragment newInstance(MultipleChoiceQuestion question) {
        // Load question components into a Bundle of arguments to pass through the fragment
        MCQuizQuestionFragment fragment = new MCQuizQuestionFragment();
        Bundle args = new Bundle();
        args.putInt("questionIndex", question.getQuestionIndex());
        args.putString("question", question.getQuestion());
        args.putString("answer1", question.getAnswerList().get(0));
        args.putString("answer2", question.getAnswerList().get(1));
        args.putString("answer3", question.getAnswerList().get(2));
        args.putString("answer4", question.getAnswerList().get(3));
        args.putInt("userAnswerIndex", question.getUserAnswer());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            this.questionIndex = getArguments().getInt("questionIndex");
            this.question = getArguments().getString("question");
            this.answer1 = getArguments().getString("answer1");
            this.answer2 = getArguments().getString("answer2");
            this.answer3 = getArguments().getString("answer3");
            this.answer4 = getArguments().getString("answer4");
            this.userAnswerIndex = getArguments().getInt("userAnswerIndex");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mc_quiz_question, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize Elements Requiring Completed View
        String index = "Question #: " + Integer.toString(questionIndex + 1);
         ((TextView) view.findViewById(R.id.quizfragmentQuestionNumber)).setText(index);
        ((TextView) view.findViewById(R.id.quizfragmentQuestion)).setText(question);
        ((TextView) view.findViewById(R.id.quizfragmentAnswer1Label)).setText(answer1);
        ((TextView) view.findViewById(R.id.quizfragmentAnswer2Label)).setText(answer2);
        ((TextView) view.findViewById(R.id.quizfragmentAnswer3Label)).setText(answer3);
        ((TextView) view.findViewById(R.id.quizfragmentAnswer4Label)).setText(answer4);

        userAnswerContainer = view.findViewById(R.id.quizfragmentSelectedAnswerDisplay);
        userAnswerContainer.setText(indexToLetter(userAnswerIndex));

        // Initialize Listeners for Buttons
        view.findViewById(R.id.quizfragmentAnswer1Button).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onButtonPressed(0);
                    }
                });

        view.findViewById(R.id.quizfragmentAnswer2Button).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onButtonPressed(1);
                    }
                });

        view.findViewById(R.id.quizfragmentAnswer3Button).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onButtonPressed(2);
                    }
                });

        view.findViewById(R.id.quizfragmentAnswer4Button).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onButtonPressed(3);
                    }
                });

    }

    @Override
    public void onStop() {
        super.onStop();

        // Update the fragment in the activity
        listener.onChangeFragment(questionIndex);
    }

    @Override
    public void onDetach() {
        super.onDetach();

        listener = null;
    }

    public void onButtonPressed(int response) {
        if (listener != null) {
            // Update the User Answer Index
            userAnswerIndex = response;

            // Update the users response displayed in the fragment
            userAnswerContainer.setText(indexToLetter(userAnswerIndex));

            // Call the listener function to pass information back to the activity
            listener.onAnswerSelection(questionIndex, userAnswerIndex);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onAnswerSelection(int questionIndex, int responseIndex);
        void onChangeFragment(int questionIndex);
    }

    private String indexToLetter(int userAnswer) {
        switch (userAnswer) {
            case 0:
                return "A";
            case 1:
                return "B";
            case 2:
                return "C";
            case 3:
                return "D";
            default:
                return "N/A";
        }
    }
}
