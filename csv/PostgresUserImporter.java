//usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS org.postgresql:postgresql:42.2.24

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostgresUserImporter {

    public static void main(String[] args) throws IOException, SQLException {


        String filePath = new File("").getAbsolutePath().concat("/output.csv");
        try (BufferedReader br = new BufferedReader(new FileReader(filePath));
         Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/java_talks", "sharifyy", "secret")
        ) {
            String line;
            String insertQuery = "INSERT INTO users (first_name, last_name, email, username) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            // skip first line
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String firstName = values[0];
                String lastName = values[1];
                String email = values[2];
                String username = values[3];

                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, username);

                preparedStatement.executeUpdate();
                System.out.println("importing %s %s %s %s into database".formatted(firstName,lastName,email,username));
            }
            System.out.println("finish");
        }
    }
}
