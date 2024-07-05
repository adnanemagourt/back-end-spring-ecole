package com.example.back.exceptions;

import java.util.List;

public class UnAuthorizedUpdateException extends Exception{
    public UnAuthorizedUpdateException(List<String> fields, String table){
        super("Unauthorized to update fields " + fields.toString() + " for table '" + table + "' !");
    }
}
