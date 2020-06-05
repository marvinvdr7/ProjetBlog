package be.projetblog.technofuturtic.projetblog.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PostTest {

    @Test
    void getContent() {
        Post post = new Post();
        post.setContent("Le Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis les années 1500, quand un imprimeur anonyme assembla ensemble des morceaux");
        assertTrue(post.getContent().length() > 99,"Le contenu doit au moins avoir 100 char");
        assertTrue(post.getContent().length() < 4999,"Le contenu ne peut pas dépasser 5000 char");
    }

    @Test
    void setContent() {
        Post post = new Post();
        post.setContent("Le Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis les années 1500, quand un imprimeur anonyme assembla ensemble des morceaux");
        assertTrue(post.getContent().length() > 99,"Le contenu doit au moins avoir 100 char");
        assertTrue(post.getContent().length() < 4999,"Le contenu ne peut pas dépasser 5000 char");
    }

    @Test
    void getImage() {
        Post post = new Post();
        post.setImage("post_image.pngm");
        String extension = getExtensionByStringHandling(post.getImage());
        assertTrue(containsWords(extension,"png", "jpg"),"Le contenu doit au moins avoir 100 char");
    }

    @Test
    void setImage() {
        Post post = new Post();
        post.setImage("post_image.pngm");
        String extension = getExtensionByStringHandling(post.getImage());
        assertTrue(containsWords(extension,"png", "jpg"),"Le contenu doit au moins avoir 100 char");
    }

    public String getExtensionByStringHandling(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1);
    }

    public boolean containsWords(String inputString, String... items) {
        for (String item : items) {
            if (inputString.contains(item)) {
                return true;
            }
        }
        return false;
    }
}