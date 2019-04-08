package com.refreshmymemory.view_presenter.select_quiz;

import java.util.Set;
import java.util.TreeSet;

public class SelectQuizPresenter implements SelectQuizContract.Presenter {
    @Override
    public Set<String> getUserCourseList() {
        Set<String> returnCourseList = new TreeSet<>();

        // ADD LOGIC TO GET CALL TO SERVER FOR USER COURSE LIST

        // ************* TEMPORARY *******************
        returnCourseList.add("ACCT 101");
        returnCourseList.add("ACCT 102");
        returnCourseList.add("ACCT 201");
        returnCourseList.add("ACCT 202");
        returnCourseList.add("ACCT 301");

        return returnCourseList;
    }

}
