package com.refreshmymemory.view_presenter.quiz;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private int correctAnswerIndex;
    private int userAnswerIndex;

    private OnFragmentInteractionListener mListener;

    public MCQuizQuestionFragment() {
        // Required empty public constructor
    }

    public static MCQuizQuestionFragment newInstance(MultipleChoiceQuestion question) {
        // Load question components into a Bundle of arguments to pass through the fragment
        MCQuizQuestionFragment fragment = new MCQuizQuestionFragment();
        Bundle args = new Bundle();
        args.putString("question", question.getQuestion());
        args.putString("answer1", question.getAnswerList().get(0));
        args.putString("answer2", question.getAnswerList().get(1));
        args.putString("answer3", question.getAnswerList().get(2));
        args.putString("answer4", question.getAnswerList().get(3));
        args.putInt("correctAnswerIndex", question.getCorrectAnswer());
        args.putInt("userAnswerIndex", question.getUserAnswer());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.question = getArguments().getString("question");
            this.answer1 = getArguments().getString("answer1");
            this.answer2 = getArguments().getString("answer2");
            this.answer3 = getArguments().getString("answer3");
            this.answer4 = getArguments().getString("answer4");
            this.correctAnswerIndex = getArguments().getInt("correctAnswerIndex");
            this.userAnswerIndex = getArguments().getInt("userAnswerIndex");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mc_quiz_question, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
