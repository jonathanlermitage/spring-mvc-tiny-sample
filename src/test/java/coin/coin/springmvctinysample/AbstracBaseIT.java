package coin.coin.springmvctinysample;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.io.IOException;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(
    // On va lancer l'appli avant de tester, on rappelle juste la classe qui contient la méthode "main".
    classes = SpringMvcTinySampleApplication.class,
    // On lance l'appli non pas sur le port 8080 mais un port aléatoire, comme ça aucun risque de
    // collision avec une autre appli ! Voir la variable "port" plus bas.
    webEnvironment = RANDOM_PORT
)
// On dit à JUnit comment se déroulent les tests. Ici, on permet au "@BeforeAll" de s'exécuter une seule fois
// au chargement de la classe, et sans être statique. En mode PER_METHOD, il faudrait que "beforeClass" soit statique,
// ce qui nous poserait problème pour récupérer le port. C'est une astuce de JUnit à connaître, et une fonctionnalité
// inspirée de TestNG (concurrent de JUnit) qui, lui, a le bon comportement par défaut.
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AbstracBaseIT {

    /**
     * Forcément, si on lance l'appli sur un port aléatoire, il faut bien le récupérer pour savoir quel port cibler
     * quand on fera nos appels HTTTP :-). C'est le rôle de "@LocalServerPort", elle met ce port dans la variable.
     */
    @LocalServerPort
    protected int port;

    /** Un objet utilitaire qui nous permet de transformer du JSON en objet. */
    private final ObjectMapper jsonMapper = new ObjectMapper();

    /**
     * Une fois avant de lancer les cas de test de cette classe (enfin... de la classe fille), on configure RestAssured
     * en lui disant quelle est l'url de base de l'appli ainsi que le port.
     */
    @BeforeAll
    public final void beforeClass() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    /**
     * Convertit du JSON en objet.
     * @param content contenu JSON.
     * @param valueType type de l'objet que l'on veut.
     * @return objet obtenu après tranformation.
     */
    @SneakyThrows(IOException.class)
    public final <T> T readValue(Response content, Class<T> valueType) {
        // Tip : le "<T>" fait référence à un type paramétré, ec qui permet de lancer
        // cette méthode en passant un type, et en retournant ce même type, par exemple Canard.class.
        return jsonMapper.readValue(content.asString(), valueType);
    }
}
