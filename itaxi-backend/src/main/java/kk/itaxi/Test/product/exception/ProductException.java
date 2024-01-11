package kk.itaxi.Test.product.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductException extends Exception {

    private final String details;
    private final ProductExceptionErrorType errorType;
}
