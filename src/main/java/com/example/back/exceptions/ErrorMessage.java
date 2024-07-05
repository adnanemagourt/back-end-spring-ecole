package com.example.back.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class ErrorMessage {
    Integer status;
    String message;
    Date timestamp;
    String description;
}
