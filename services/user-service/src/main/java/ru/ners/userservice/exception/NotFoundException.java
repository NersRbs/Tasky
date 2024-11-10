package ru.ners.userservice.exception;

import io.grpc.StatusRuntimeException;

public class NotFoundException extends StatusRuntimeException {
    public NotFoundException(String message) {
        super(io.grpc.Status.NOT_FOUND.withDescription(message));
    }
}
