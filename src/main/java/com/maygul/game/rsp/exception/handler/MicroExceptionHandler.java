package com.maygul.game.rsp.exception.handler;


import com.maygul.game.rsp.exception.constant.MicroErrorType;
import com.maygul.game.rsp.exception.data.ExceptionData;
import com.maygul.game.rsp.exception.type.DataConflictException;
import com.maygul.game.rsp.exception.type.DataNotFoundException;
import com.maygul.game.rsp.exception.type.InvalidRequestException;
import com.maygul.game.rsp.exception.type.MethodNotAllowedException;
import com.maygul.game.rsp.exception.type.MicroException;
import com.maygul.game.rsp.exception.type.ServiceCallException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Locale;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class MicroExceptionHandler extends ResponseEntityExceptionHandler {

  @Value("${spring.application.name}")
  public String applicationName;

  @Autowired
  private MessageSource messageSource;

  private static final String GENERIC_ERROR_MESSAGE_KEY = "com.maygul.game.rsp.exception.general";

  public MicroExceptionHandler() {
  }

  @ResponseStatus(value = HttpStatus.CONFLICT)
  @ExceptionHandler(value = DataConflictException.class)
  public ExceptionData handleDataConflictException(DataConflictException ex, Locale locale) {
    return errorResponse(ex, locale);
  }

  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = InvalidRequestException.class)
  public ExceptionData handleInvalidRequestException(InvalidRequestException ex, Locale locale) {
    return errorResponse(ex, locale);
  }

  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ExceptionHandler(value = DataNotFoundException.class)
  public ExceptionData handleDataNotFoundException(DataNotFoundException ex, Locale locale) {
    return errorResponse(ex, locale);
  }

  @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
  @ExceptionHandler(value = MethodNotAllowedException.class)
  public ExceptionData handleMethodNotAllowedException(MethodNotAllowedException ex,
                                                       Locale locale) {
    return errorResponse(ex, locale);
  }

  @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
  @ExceptionHandler(value = ServiceCallException.class)
  public ExceptionData handleServiceCallException(ServiceCallException ex, Locale locale) {
    return errorResponse(ex, locale);
  }

  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(value = MicroException.class)
  public ExceptionData handleMicroException(MicroException ex, Locale locale) {
    return errorResponse(ex, locale);
  }

  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(value = UndeclaredThrowableException.class)
  public ExceptionData handleUndeclaredThrowableException(UndeclaredThrowableException ex,
                                                          Locale locale) {
    return handleException(ex, locale);
  }

  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(value = Exception.class)
  public ExceptionData handleException(Exception ex, Locale locale) {
    log.error(ExceptionUtils.getStackTrace(ex));
    ExceptionData exceptionData =
        new ExceptionData(MicroErrorType.INTERNAL_ERROR.getCode(), ex.getMessage());
    MicroException exception = new MicroException(exceptionData);
    return errorResponse(exception, locale);
  }

  private ExceptionData errorResponse(MicroException ex, Locale locale) {
    ExceptionData exceptionData = ex.getData();
    if (exceptionData != null) {
      if (StringUtils.isEmpty(exceptionData.getApplicationName())) {
        exceptionData.setApplicationName(applicationName);
      }
      if (!StringUtils.isEmpty(exceptionData.getErrorMessage())) {
        String formattedMessage = null;
        try {
          formattedMessage =
              messageSource.getMessage(exceptionData.getErrorMessage(), null, locale);
        } catch (NoSuchMessageException ex2) {
        }
        if (formattedMessage == null) {
          formattedMessage = messageSource.getMessage(GENERIC_ERROR_MESSAGE_KEY, null, locale);
        }
        exceptionData.setErrorMessage(formattedMessage);
      }
    }
    return exceptionData;
  }

  @Override
  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
      HttpRequestMethodNotSupportedException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    StringBuilder builder = new StringBuilder();
    builder.append(ex.getMethod());
    builder.append(" method is not supported for this request. Supported methods are ");
    if (ex.getSupportedHttpMethods() != null) {
      ex.getSupportedHttpMethods().forEach(t -> builder.append(t).append(" "));
    }
    ExceptionData exceptionData =
        new ExceptionData(MicroErrorType.METHOD_NOT_ALLOWED.getCode(), builder.toString());
    return handleExceptionInternal(ex, exceptionData, headers, HttpStatus.METHOD_NOT_ALLOWED,
        request);
  }

  @Override
  protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                 HttpHeaders headers,
                                                                 HttpStatus status,
                                                                 WebRequest request) {

    String errorMessage =
        new StringBuilder("No handler found for ").append(ex.getHttpMethod()).append(" ")
            .append(ex.getRequestURL()).toString();
    ExceptionData exceptionData =
        new ExceptionData(MicroErrorType.HANDLER_NOT_FOUND.getCode(), errorMessage);
    return handleExceptionInternal(ex, exceptionData, headers, HttpStatus.NOT_FOUND, request);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                HttpHeaders headers,
                                                                HttpStatus status,
                                                                WebRequest request) {
    String errorMessage = new StringBuilder("Invalid request: ").append(ex.getMessage()).toString();
    ExceptionData exceptionData =
        new ExceptionData(MicroErrorType.INVALID_REQUEST.getCode(), errorMessage);
    return handleExceptionInternal(ex, exceptionData, headers, HttpStatus.BAD_REQUEST, request);
  }
}

