package org.itstep;

import model.User;

public class Main {

    public static void main(String[] args) {
        User user = new User("Chalov","Vlad","Al");
        System.out.println(user.getLogin());
        System.out.println(user.getPassword());
        System.out.println(user.isEquals("user"));
    }
}
