package parsing;

import com.opencsv.CSVWriter;
import model.Person;
import model.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class UserParsing {
    public static List<User> users = null;
    public static void main(String[] args) {
        PersonsRw personsRw = new PersonsRw();

        personsRw.read();
        List<Person> persons = personsRw.getPersons().getPersons();
        users = persons.stream()
                        .map(p -> new User(p.getSurname(), p.getName(), p.getPatronymic()))
                        .collect(Collectors.toList());
        writeToCsv();
    }

    public static void writeToCsv() {

        File file = new File("src/date/user.csv");
        try (FileWriter outputfile = new FileWriter(file)) {

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // adding header to csv
            String[] header = {"surname", "name", "patronymic", "login", "password"};
            writer.writeNext(header);


            users.stream()
                    .map(p -> new String[]{p.getSurname(), p.getName(), p.getPatronymic(), p.getLogin(), p.getPassword()})
                    .forEach(writer::writeNext);

            // closing writer connection
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
