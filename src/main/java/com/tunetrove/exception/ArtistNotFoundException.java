package com.tunetrove.exception;

public class ArtistNotFoundException extends RuntimeException {
    public ArtistNotFoundException(String artistId) {
        super("Artist with  ID " + artistId + " not found");
    }
}
