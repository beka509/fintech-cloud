package kz.fintech.starter.bpm.exceptions;

public class CamundaServerNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 7494642256031648606L;

    public CamundaServerNotFoundException(String message) {
        super(message);
    }
}
