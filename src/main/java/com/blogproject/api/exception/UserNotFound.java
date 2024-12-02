package com.blogproject.api.exception;

import com.blogproject.api.exception.BlogException;

/**
 * status -> 404
 */
public class UserNotFound extends BlogException {

    private static final String MESSAGE = "존재하지 않는 사용자입니다.";

    public UserNotFound(String s) {
        super(MESSAGE);
    }

    public UserNotFound() {
        super(MESSAGE);
    }


    @Override
    public int getStatusCode() {
        return 404;
    }
}
