package com.harpreet.validate;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

public interface Validator<T> {

    ValidationResult validate(T input);

    @Builder
    @Data
    public static class ValidationResult {
        private String message;
        private boolean isValid;

        public String getMessage() {
            return "{\"msg\":\"" + message + "\"}";
        }
    }
}


