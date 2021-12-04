package dto;

import java.io.Serializable;

public class RegisterResponse implements Serializable {

    private boolean success = true;

    private boolean emailSent = true;

    private String message = "Registration successful";

    public RegisterResponse() {};

    public RegisterResponse(boolean emailSent) {
        this.emailSent = emailSent;
    }

    public RegisterResponse(boolean success, boolean emailSent, String message) {
        this.success = success;
        this.message = message;
        this.emailSent = emailSent;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isEmailSent() {
        return emailSent;
    }

    public void setEmailSent(boolean emailSent) {
        this.emailSent = emailSent;
    }
}

