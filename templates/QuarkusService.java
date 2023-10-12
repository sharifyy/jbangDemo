///usr/bin/env jbang "$0" "$@" ; exit $?
//JAVA 17+
// Update the Quarkus version to what you want here or run jbang with
// `-Dquarkus.version=<version>` to override it.
//DEPS io.quarkus:quarkus-bom:${quarkus.version:3.2.6.Final}@pom
//DEPS io.quarkus:quarkus-resteasy
//DEPS io.quarkus:quarkus-resteasy-jsonb
// //DEPS io.quarkus:quarkus-smallrye-openapi
// //DEPS io.quarkus:quarkus-swagger-ui
//JAVAC_OPTIONS -parameters
//FILES application.properties

import io.quarkus.runtime.Quarkus;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import java.util.List;

@Path("/books")
@ApplicationScoped
public class QuarkusService {

    @GET
    public List<Book> books() {
        return List.of(
            new Book("Implementing DDD","Vaughn Vernon"),
            new Book("TDD by example","Kent Beck")
        );
    }

    public record Book(String title,String author){}

}
