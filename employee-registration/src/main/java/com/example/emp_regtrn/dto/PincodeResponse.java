package com.example.emp_regtrn.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class PincodeResponse {
	

    @JsonProperty("Message")
    private String message;

    @JsonProperty("Status")
    private String status;

    @JsonProperty("PostOffice")
    private List<PostOfficeResponse> postOffice;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PostOfficeResponse> getPostOffice() {
        return postOffice;
    }

    public void setPostOffice(List<PostOfficeResponse> postOffice) {
        this.postOffice = postOffice;
    }

    @Override
    public String toString() {
        return "PincodeResponse{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", postOffice=" + postOffice +
                '}';
    }
}