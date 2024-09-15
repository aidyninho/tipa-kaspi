package kz.aidyninho.tipakaspi.exception;

public class FailedToGetCurrencyFromSourceException extends RuntimeException {
    public FailedToGetCurrencyFromSourceException(String message, Throwable cause) {
        super(message, cause);
    }
}
