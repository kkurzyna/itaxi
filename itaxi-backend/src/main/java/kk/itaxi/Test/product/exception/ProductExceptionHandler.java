package kk.itaxi.Test.product.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ProductExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ ProductException.class })
    protected ResponseEntity<ProductExceptionResponse> handle(ProductException ex) {
        return new ResponseEntity<>(new ProductExceptionResponse(ex.getDetails()), new HttpHeaders(), getHttpStatus(ex.getErrorType()));
    }

    private HttpStatus getHttpStatus(ProductExceptionErrorType errorType) {
        if (errorType == ProductExceptionErrorType.VALIDATION) {
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
