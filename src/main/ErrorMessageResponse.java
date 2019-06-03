package main;

public class ErrorMessageResponse {

    private String message;
    private String field;

    public ErrorMessageResponse(){

    }

    public static ErrorMessageResponse build(String message, String field){
        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse();
        errorMessageResponse.setMessage(message);
        errorMessageResponse.setField(field);

        return errorMessageResponse;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "ErrorMessageResponse{" +
                "message='" + message + '\'' +
                ", field='" + field + '\'' +
                '}';
    }
}
