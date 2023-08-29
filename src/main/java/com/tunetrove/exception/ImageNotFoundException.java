package com.tunetrove.exception;

public class ImageNotFoundException extends RuntimeException {
    public ImageNotFoundException(){ super("Image not found: The requested image could not be found.\n");}
}
