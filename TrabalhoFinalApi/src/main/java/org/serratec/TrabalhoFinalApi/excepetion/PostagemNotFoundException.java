package org.serratec.TrabalhoFinalApi.excepetion;

public class PostagemNotFoundException extends RuntimeException {
    public PostagemNotFoundException(String message) {
        super(message);
    }
}
