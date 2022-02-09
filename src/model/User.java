package model;

import com.ibm.icu.text.Transliterator;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User extends Person{
    private String login;
    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public User(String surname, String name, String patronymic) {
        super(surname, name, patronymic);
        generateLoginPass();
    }
    public void generateLoginPass (){
        String CYRILLIC_TO_LATIN = "Russian-Latin/BGN";
        Transliterator russianToLatin = Transliterator.getInstance(CYRILLIC_TO_LATIN);
        StringBuilder sb = new StringBuilder();
        sb.append(getSurname());
        sb.append(getName().substring(0, 1));
        sb.append(getPatronymic().substring(0, 1));
        login = russianToLatin.transliterate(sb.toString().toLowerCase());

        String myPassword = "user";

        MessageDigest md = null;

        try {
            md= MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(myPassword.getBytes());
        byte[] digest = md.digest();
        this.password = DatatypeConverter.printHexBinary(digest).toUpperCase();
    }
    public boolean isEquals(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(password.getBytes());
        byte[] digest = md.digest();

        return DatatypeConverter.printHexBinary(digest).toUpperCase().equals(getPassword());
    }
}
