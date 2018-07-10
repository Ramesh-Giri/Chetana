package com.chetana.ramesh.chetana.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.orm.SugarRecord;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Faqs extends SugarRecord implements Serializable {
    String question;
    String answer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
