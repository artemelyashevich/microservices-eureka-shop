package com.elyashevich.category_service.exeption;

public class ServerException extends RuntimeException{
    public ServerException(String message) {
        super(message);
    }
}
