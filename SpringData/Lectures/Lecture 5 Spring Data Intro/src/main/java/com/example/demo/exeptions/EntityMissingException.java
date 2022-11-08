package com.example.demo.exeptions;

public class EntityMissingException extends RuntimeException{
    public EntityMissingException(String msg) {
        super(msg);
    }
}
