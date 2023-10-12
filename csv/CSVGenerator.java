//usr/bin/env jbang "$0" "$@" ; exit $?  
//DEPS com.github.javafaker:javafaker:1.0.2
//DEPS org.apache.commons:commons-csv:1.8

import com.github.javafaker.Faker;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;

public class CSVGenerator {

    public static void main(String[] args) throws IOException {
        Faker faker = new Faker();
        String csvFilePath = "output.csv";

        try (FileWriter fileWriter = new FileWriter(csvFilePath);
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT
                     .withHeader("first_name", "last_name", "email", "username"))) {

            for (int i = 0; i < 20; i++) {
                String firstName = faker.name().firstName();
                String lastName = faker.name().lastName();
                String email = faker.internet().emailAddress();
                String username = faker.name().username();

                csvPrinter.printRecord(firstName, lastName, email, username);
            }

            System.out.println("CSV file generated successfully at: " + csvFilePath);
        }
    }
}
