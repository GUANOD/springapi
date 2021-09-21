package com.example.springapi.models;

public class ErrorMessage {
    private String error;
    private int code;

    public String getError(){
        return error;
    }

    public int getCode(){
        return code;
    }

    //setters

    public void setError(String error) {
        this.error = error;
    }

    public void setCode(int code) {
        this.code = code;
    }
//CONSTRUCT

    public ErrorMessage(){
    }

    ErrorMessage(String error){
        this.error= error;
    }

    public ErrorMessage(String error, int code){
        this.error=error;
        this.code=code;
    }

};


