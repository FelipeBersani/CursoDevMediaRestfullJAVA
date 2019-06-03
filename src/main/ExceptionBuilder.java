package main;

import java.util.List;

public class ExceptionBuilder {

    private String statusCode;
    private List<ErrorMessageResponse> errorList;

    public ExceptionBuilder(){

    }


    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public List<ErrorMessageResponse> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<ErrorMessageResponse> errorList) {
        this.errorList = errorList;
    }
}
