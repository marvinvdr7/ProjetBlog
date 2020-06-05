package be.projetblog.technofuturtic.projetblog.exceptions;

public class CategoryNotFoundException extends Exception {
    public CategoryNotFoundException(String message) {
        super(message);
    }

    public CategoryNotFoundException() {
        super("Cette catégorie n'existe pas");
    }
}
