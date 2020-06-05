package be.projetblog.technofuturtic.projetblog.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException() {
        super("Cet utilisateur n'existe pas");
    }
}
