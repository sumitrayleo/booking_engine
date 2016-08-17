package com.cognizant.orchestration.exception;

public class BookingApplException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public BookingApplException() {
        super();
    }

    public BookingApplException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

    public BookingApplException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public BookingApplException(String arg0) {
        super(arg0);
    }

    public BookingApplException(Throwable arg0) {
        super(arg0);
    }

}
