package ir.home.utility;

import java.util.List;

public class MethodResult<T> {

    private boolean hasError;
    private String message;    
    private List<T> data;
    
    public MethodResult()
    {

    }
    public MethodResult(boolean hasError, String msg, List<T> data)
    {
        this.hasError = hasError;
        this.message = msg;
        this.data = data;
    }
    
    public boolean isHasError() {
        return hasError;
    }
    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public List<T> getData() {
        return data;
    }
    public void setData(List<T> data) {
        this.data = data;
    }
       
}