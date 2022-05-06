package com.example.restproject.exception;

import java.util.Date;

/**
 * @author Vagharshak Grigoryan
 */

public class ErrorResponse {

    private Date timestamp;
    private String status;
    private String message;
    private String details;


    public ErrorResponse(Date timestamp, String status, String message, String details) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.details = details;
    }

    public ErrorResponse() {
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "timestamp=" + timestamp +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", details='" + details + '\'' +
                '}';
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
