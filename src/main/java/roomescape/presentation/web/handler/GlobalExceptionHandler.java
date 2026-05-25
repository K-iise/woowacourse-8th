package roomescape.presentation.web.handler;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import roomescape.presentation.web.dto.ErrorResponse;
import roomescape.presentation.web.dto.FieldErrorResponse;
import roomescape.domain.exception.ConflictException;
import roomescape.domain.exception.DomainValidationException;
import roomescape.domain.exception.ErrorCode;
import roomescape.domain.exception.NotFoundException;
import roomescape.domain.exception.UnprocessableEntityException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                "INVALID_PARAMETER_TYPE", request.getRequestURI(), "요청 파라미터의 타입이 올바르지 않습니다."
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e, HttpServletRequest request) {
        ErrorCode error = e.getErrorCode();
        ErrorResponse errorResponse = new ErrorResponse(
                error.getCode(), request.getRequestURI(), error.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> handleConflictException(ConflictException e, HttpServletRequest request) {
        ErrorCode error = e.getErrorCode();
        ErrorResponse errorResponse = new ErrorResponse(
                error.getCode(), request.getRequestURI(), error.getMessage()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<ErrorResponse> handleUnprocessableEntityException(UnprocessableEntityException e,
                                                                            HttpServletRequest request) {
        ErrorCode error = e.getErrorCode();
        ErrorResponse errorResponse = new ErrorResponse(
                error.getCode(), request.getRequestURI(), error.getMessage()
        );
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
    }

    @ExceptionHandler(DomainValidationException.class)
    public ResponseEntity<ErrorResponse> handleDomainValidationException(DomainValidationException e,
                                                                         HttpServletRequest request) {
        ErrorCode error = e.getErrorCode();
        ErrorResponse errorResponse = new ErrorResponse(
                error.getCode(), request.getRequestURI(), error.getMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @Nullable
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        List<FieldErrorResponse> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> new FieldErrorResponse(fe.getField(), fe.getDefaultMessage()))
                .toList();

        ErrorResponse errorResponse = new ErrorResponse(
                "INVALID_CONSTRAINT", extractRequestURI(request), "요청 값이 유효하지 않습니다.", fieldErrors
        );

        return ResponseEntity.status(status).body(errorResponse);
    }

    @Nullable
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                "INVALID_REQUEST", extractRequestURI(request), "요청 본문의 형식이 올바르지 않습니다."
        );
        return ResponseEntity.status(status).body(errorResponse);
    }

    @Nullable
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers, HttpStatusCode status,
                                                                          WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                "INVALID_PARAMETER", extractRequestURI(request), "필수 요청 파라미터가 누락되었습니다."
        );
        return ResponseEntity.status(status).body(errorResponse);
    }

    @Nullable
    @Override
    protected ResponseEntity<Object> handleHandlerMethodValidationException(HandlerMethodValidationException ex,
                                                                            HttpHeaders headers, HttpStatusCode status,
                                                                            WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                "INVALID_PARAMETER_CONSTRAINT", extractRequestURI(request), "요청 파라미터의 형식이 유효하지 않습니다."
        );
        return ResponseEntity.status(status).body(errorResponse);
    }

    private String extractRequestURI(WebRequest request) {
        if (request instanceof ServletWebRequest servletWebRequest) {
            return servletWebRequest.getRequest().getRequestURI();
        }
        return request.getDescription(false);
    }
}