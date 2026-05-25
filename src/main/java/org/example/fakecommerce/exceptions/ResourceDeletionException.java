package org.example.fakecommerce.exceptions;

public class ResourceDeletionException extends RuntimeException{

    public ResourceDeletionException(String message){
        super(message);
    }
}
