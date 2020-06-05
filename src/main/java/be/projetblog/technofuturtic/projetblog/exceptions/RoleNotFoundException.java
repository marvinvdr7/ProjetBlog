package be.projetblog.technofuturtic.projetblog.exceptions;

public class RoleNotFoundException extends Exception {
    public RoleNotFoundException(String message) {
        super(message);
    }

    public RoleNotFoundException() {
        super("Ce role n'existe pas");
    }
}
