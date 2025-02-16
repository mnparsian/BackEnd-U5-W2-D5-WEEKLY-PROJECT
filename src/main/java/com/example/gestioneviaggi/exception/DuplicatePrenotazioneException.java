package com.example.gestioneviaggi.exception;

public class DuplicatePrenotazioneException extends RuntimeException{
    public DuplicatePrenotazioneException(String message) {
        super(message);
    }
}
