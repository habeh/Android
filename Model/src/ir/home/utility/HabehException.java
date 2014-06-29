
package ir.home.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HabehException extends Exception {

    private static final long serialVersionUID = 4876920264893851805L;

    public HabehException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {

        String msg = super.getMessage();
        
        return msg;
    }
}
