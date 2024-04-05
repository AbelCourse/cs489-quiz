package edu.miu.cs489;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import edu.miu.cs489.model.Address;
import edu.miu.cs489.model.Contact;
import edu.miu.cs489.model.EmailAddress;
import edu.miu.cs489.model.PhoneNumber;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create a contact
        Contact contact = createContact();

        // Convert contact object to JSON string
        String jsonContact = convertToJson(contact);

        // Print JSON contact to console
        System.out.println(jsonContact);
    }

    public static Contact createContact() {
        Contact contact = Contact.builder().firstName("Abel")
                        .lastName("Seyoum")
                        .company("Meta")
                        .jobTitle("Software Engineer")
                .build();

        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(new PhoneNumber("2403987712", "Mobile"));
        contact.setPhoneNumbers(phoneNumbers);

        // Add email addresses
        List<EmailAddress> emailAddresses = new ArrayList<>();
        emailAddresses.add(new EmailAddress("abelseleshi.seyoum@miu.edu", "Work", new Address("1000 N 4th St", "Fairfield", "IA", "52556")));
        contact.setEmailAddresses(emailAddresses);

        return contact;
    }

    public static String convertToJson(Contact contact) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
        try {
            return writer.writeValueAsString(contact);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}