package com.refreshmymemory.view_presenter.landing;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Set;

import com.refreshmymemory.R;

public class SelectQuizRecyclerViewAdapter extends
        RecyclerView.Adapter<SelectQuizRecyclerViewAdapter.MyViewHolder> {
    private Set<String> courses;
    private Context context;


    // Provide a reference to the views for each data item
    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView courseName;

        private MyViewHolder(View view) {
            super(view);

            // Link Text View
            courseName = view.findViewById(R.id.selectquizcourserowCourseName);
        }
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

    SelectQuizRecyclerViewAdapter(Context newContext, Set<String> courseList) {
        this.courses = courseList;
        this.context = newContext;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Formatting Strings
        DateTimeFormatter formatDate = DateTimeFormat.forPattern("yyyy-MM-dd, EEEE");
        DateTimeFormatter formatTime = DateTimeFormat.forPattern("HH:mm");

        // Set Time String
        String appointmentTime = courses.get(position).getStart().
                toString(formatTime) + " - " +
                courses.get(position).getEnd().toString(formatTime);

        // Retrieve information at a position in the dataset and replace
        // the contents of this view with that data
        holder.myDate.setText(courses.get(position).getDate().toString(formatDate));
        holder.myTime.setText(appointmentTime);
        holder.myTitle.setText(courses.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public void clearData() {
        courses.clear();
    }
}
