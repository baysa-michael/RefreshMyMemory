package com.refreshmymemory.view_presenter.select_quiz;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.refreshmymemory.R;

public class SelectQuizRecyclerViewAdapter extends
        RecyclerView.Adapter<SelectQuizRecyclerViewAdapter.MyViewHolder> {
    private static final String TAG = "SelectQuizRVA";
    private List<String> courses;
    private Context context;
    private OnItemClickListener listener;

    // Public Interface for a Set On Item Click Listener
    public interface OnItemClickListener {
        void onItemClick(String targetCourse);
    }

    // Provide a reference to the views for each data item
    static class MyViewHolder extends RecyclerView.ViewHolder {
        private Button courseName;

        private MyViewHolder(View newView) {
            super(newView);

            // Link Text View
            courseName = newView.findViewById(R.id.selectquizcourserowCourseName);
        }

        public void bind(final String targetCourse, final OnItemClickListener listener) {
            // Set Listener
            courseName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(targetCourse);
                }
            });

        }
    }

    SelectQuizRecyclerViewAdapter(Context newContext, Set<String> courseList,
                                  OnItemClickListener newListener) {
        this.courses = new ArrayList<>(courseList);
        this.context = newContext;
        this.listener = newListener;


    }

    @Override
    @NonNull
    public SelectQuizRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                         int viewType) {
        // Create a New View from the row.xml layout file
        LayoutInflater myInflater = LayoutInflater.from(context);
        View newView = myInflater.inflate(R.layout.select_course_recycler_row, parent,
                false);

        return new MyViewHolder(newView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Retrieve information at a position in the dataset and replace
        // the contents of this view with the retrieved data
        holder.courseName.setText(courses.get(position));
        holder.bind(courses.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public void clearData() {
        courses.clear();
    }
}
