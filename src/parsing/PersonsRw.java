package parsing;

import model.Persons;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;


//Импортировать javax.xml.bind, javax.activation, org.glassfish.jaxb
public class PersonsRw {
    private Persons persons = null;
    // private Person person = new Person("1","2","3");
    private final String path = "src/date/persons.xml";

    public void write() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Persons.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML

            //Print XML String to file
            jaxbMarshaller.marshal(persons, new File(path));

        } catch (JAXBException ex) {
            ex.printStackTrace();

//System.err.println("Cannot to write to file " + path);
        }
    }

    public void read() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Persons.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            persons = (Persons) jaxbUnmarshaller.unmarshal(new File(path));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    public Persons getPersons() {
        return persons;
    }

    public void setPersons(Persons persons) {
        this.persons = persons;
    }
}
