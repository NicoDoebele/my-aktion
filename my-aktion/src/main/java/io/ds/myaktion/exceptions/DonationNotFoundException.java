package io.ds.myaktion.exceptions;

public class DonationNotFoundException extends RuntimeException {

    public DonationNotFoundException() {
    }

    public DonationNotFoundException(String message) {
        super(message);
    }

    public DonationNotFoundException(Throwable cause) {
        super(cause);
    }

    public DonationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DonationNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
