package be.projetblog.technofuturtic.projetblog.exceptions;

public class PostNotFoundException extends Exception {
    public PostNotFoundException(String message) {
        super(message);
    }

    public PostNotFoundException() {
        super("Cet article n'existe pas");
    }
}