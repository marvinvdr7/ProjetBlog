package be.projetblog.technofuturtic.projetblog.exceptions;

public class CommentNotFoundException extends Exception {
    public CommentNotFoundException(String message) {
        super(message);
    }

    public CommentNotFoundException() {
        super("Ce commentaire n'existe pas");
    }
}
