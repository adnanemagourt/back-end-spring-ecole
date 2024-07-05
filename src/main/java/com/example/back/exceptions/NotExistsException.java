package com.example.back.exceptions;

import java.util.List;

public class NotExistsException extends Exception{
    public NotExistsException(List<String> fields, String table){
        super("No existing entry");
    }
}
