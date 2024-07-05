package com.example.back.exceptions;

import java.util.List;

public class AlreadyExistsException extends Exception{
    public AlreadyExistsException(List<String> fields, String table){
        super("Already existing having the same values for the fields: " + fields.toString());
    }
}
