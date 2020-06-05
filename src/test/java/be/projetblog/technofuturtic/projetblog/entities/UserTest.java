package be.projetblog.technofuturtic.projetblog.entities;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getFirstname() {
        User user = new User();
        user.setFirstname("Marvin");
        assertTrue(user.getFirstname().length() > 3,"Le prénom doit au moins avoir 4 char");
        assertTrue(user.getFirstname().length() < 29,"Le prénom ne peut pas dépasser 30 char");
        assertTrue(user.getFirstname().chars().allMatch(Character::isLetter),"Le prénom ne peut contenir que des lettres");
    }

    @Test
    void setFirstName() {
        User user = new User();
        user.setFirstname("Jean");
        assertTrue(user.getFirstname().length() > 3,"Le prénom doit au moins avoir 4 char");
        assertTrue(user.getFirstname().length() < 29,"Le prénom ne peut pas dépasser 30 char");
        assertTrue(user.getFirstname().chars().allMatch(Character::isLetter),"Le prénom ne peut contenir que des lettres");
    }

    @Test
    void getBirthdate() {
        User user = new User();
        LocalDate date = LocalDate.of(2000, 1, 8);
        LocalDate dateMin = LocalDate.of(1900, 1, 8);
        LocalDate dateNow = LocalDate.now();
        user.setBirthdate(date);
        assertTrue(dateNow.isAfter(date),"Date de naissance > qu'aujourd'hui !");
        assertTrue(date.isAfter(dateMin),"Vous avez plus de 120 ans !");
    }

    @Test
    void setBirthdate() {
        User user = new User();
        LocalDate date = LocalDate.of(2000, 1, 8);
        LocalDate dateMin = LocalDate.of(1900, 1, 8);
        LocalDate dateNow = LocalDate.now();
        user.setBirthdate(date);
        assertTrue(dateNow.isAfter(date),"Date de naissance > qu'aujourd'hui !");
        assertTrue(date.isAfter(dateMin),"Vous avez plus de 120 ans !");
    }

}