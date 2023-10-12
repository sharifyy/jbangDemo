//usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS org.keycloak:keycloak-admin-client:22.0.3

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.RealmRepresentation;
import org.keycloak.representations.idm.ClientRepresentation;
import java.util.List;

public class CreateRealmAndClient {

    public static void main(String[] args) {
        // Keycloak admin credentials and server URL
      
        String serverUrl = "http://localhost:8080";

        // Create Keycloak admin client
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm("master")
                .grantType("client_credentials")
                .clientSecret("yIkh5wGSky4LqmZjViM806CaMB14Ats8")
                .clientId("admin-cli")
                .build();

        // Create a new realm
        RealmRepresentation realm = new RealmRepresentation();
        realm.setRealm("demo");
        keycloak.realms().create(realm);

        // Create a new client in the realm
        ClientRepresentation client = new ClientRepresentation();
        client.setClientId("java_talks");
        client.setDirectAccessGrantsEnabled(true);
        client.setRedirectUris(List.of("/*")); // Set your redirect URIs here if needed
        keycloak.realm("demo").clients().create(client);

        System.out.println("Realm 'demo' and client 'java_talks' created successfully.");
    }
}
