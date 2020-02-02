package org.example.konta;

import org.example.konta.model.Accounts;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Application {
    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) {
        try {
            File inputFile = new File("input.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Accounts.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Accounts accounts = (Accounts) unmarshaller.unmarshal(inputFile);

            Manager manager = new Manager();
            manager.manage(accounts);

            File outputFile = new File("output.xml");
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(accounts, outputFile);
        } catch (JAXBException exception) {
            LOGGER.log(Level.SEVERE, exception.toString(), exception);
        }
    }
}
