package be.projetblog.technofuturtic.projetblog.exceptions;

public class PermissionNotFoundException extends Exception {
    public PermissionNotFoundException(String message) {
        super(message);
    }

    public PermissionNotFoundException() {
        super("Cete permission n'existe pas");
    }
}