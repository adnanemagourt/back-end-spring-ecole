package com.example.back.exceptions;

public class WrongPasswordException extends Exception{
    public WrongPasswordException(){
        super("Wrong Password");
    }
}
