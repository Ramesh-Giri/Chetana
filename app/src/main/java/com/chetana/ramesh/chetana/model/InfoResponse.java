package com.chetana.ramesh.chetana.model;

import com.chetana.ramesh.chetana.api.model.BaseResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public class InfoResponse  extends BaseResponse {

    private List<Info> data;

    public List<Info> getData() {
        return data;
    }

    public void setData(List<Info> data) {
        this.data = data;
    }

}
