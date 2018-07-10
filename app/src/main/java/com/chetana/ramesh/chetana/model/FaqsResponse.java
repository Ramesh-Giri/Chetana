package com.chetana.ramesh.chetana.model;

import com.chetana.ramesh.chetana.api.model.BaseResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public class FaqsResponse  extends BaseResponse {

    private List<Faqs> data;

    public List<Faqs> getData() {
        return data;
    }

    public void setData(List<Faqs> data) {
        this.data = data;
    }

}
