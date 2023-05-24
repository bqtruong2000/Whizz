package com.quizchic.whizz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class QuestionList extends ArrayList<Question> {
    public QuestionList() {
        Question q1 = new Question();
        q1.setQuestion("1. What is Software?");
        q1.setAnswer("Software is a set of programs and documentation");
        q1.setOption1("Software is documentation and configuration of data");
        q1.setOption2("Software is a set of programs");
        q1.setOption3("None of these");
        this.add(q1);

        Question q2 = new Question();
        q2.setQuestion("2. Project risk factor is considered in which model?");
        q2.setAnswer("Spiral model");
        q2.setOption1("Waterfall model");
        q2.setOption2("RM model");
        q2.setOption3("Prototyping model");
        this.add(q2);

        Question q3 = new Question();
        q3.setQuestion("3. Which of the following is true?");
        q3.setAnswer("Design – Solving problems");
        q3.setOption1("Analysis – Find out solutions");
        q3.setOption2("Analysis – How to do it");
        q3.setOption3("Design – Understanding problems");
        this.add(q3);
    }
}

