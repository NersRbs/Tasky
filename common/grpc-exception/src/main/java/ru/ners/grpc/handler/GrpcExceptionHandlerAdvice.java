package ru.ners.grpc.handler;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;
import org.springframework.stereotype.Component;
import ru.ners.grpc.exception.AlreadyExistsException;
import ru.ners.grpc.exception.NotFoundException;

@GrpcAdvice
public class GrpcExceptionHandlerAdvice {
    @GrpcExceptionHandler
    public StatusRuntimeException handleNotFoundException(NotFoundException exception) {
        return Status.NOT_FOUND
                .withDescription(exception.getMessage())
                .asRuntimeException();
    }

    @GrpcExceptionHandler
    public StatusRuntimeException handleAlreadyExistsException(AlreadyExistsException exception) {
        return Status.ALREADY_EXISTS
                .withDescription(exception.getMessage())
                .asRuntimeException();
    }
}
