package ru.ners.grpc.exception;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;

public class AlreadyExistsException extends StatusRuntimeException {
    public AlreadyExistsException(String message) {
        super(Status.ALREADY_EXISTS.withDescription(message));
    }
}
