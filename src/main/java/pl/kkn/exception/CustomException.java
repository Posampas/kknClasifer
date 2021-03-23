package pl.kkn.exception;

public class CustomException extends RuntimeException {
    private final  String message;

    public CustomException(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
