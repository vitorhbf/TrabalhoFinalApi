package org.serratec.TrabalhoFinalApi.excepetion;

public class ComentarioNotFoundException extends RuntimeException {
    public ComentarioNotFoundException(String message) {
        super(message);
    }
}
