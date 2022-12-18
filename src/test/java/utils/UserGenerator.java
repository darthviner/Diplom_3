package utils;

import models.User;

import java.util.Random;

public class UserGenerator {
    public static User getUniqueUser(){
        Random random = new Random();
        String email = "vinertest"+random.nextInt(1000000000)+"@mail.ru";
        String name = "vinertest"+random.nextInt(1000000000);
        String password = "123456";
        return new User(email,password,name);
    }
}
