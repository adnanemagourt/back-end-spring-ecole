package com.example.back.exceptions;

import java.util.List;

public class EmptyFieldsException extends Exception{
    public EmptyFieldsException(List<String> fields, String table){
        super("Important Fields " + fields.toString());
    }
}
