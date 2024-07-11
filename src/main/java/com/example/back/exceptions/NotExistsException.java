package com.example.back.exceptions;

import java.util.List;

public class NotExistsException extends Exception {
    public NotExistsException() {
        super("No existing entry");
    }
}
