package kz.fintech.starter.bpm2.exceptions;

public class CamundaServerNotFoundExceptionNew extends RuntimeException {

    private static final long serialVersionUID = 7494642256031648606L;

    public CamundaServerNotFoundExceptionNew(String message) {
        super(message);
    }
}
