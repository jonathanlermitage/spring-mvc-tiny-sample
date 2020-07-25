package demo.springmvctinysample;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.springmvctinysample.service.PersonService;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.IOException;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(
    classes = SpringMvcTinySampleApplication.class,
    webEnvironment = RANDOM_PORT
)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AbstracBaseIT {

    @LocalServerPort
    protected int port;

    private final ObjectMapper jsonMapper = new ObjectMapper();

    @SpyBean
    protected PersonService personService;

    @BeforeAll
    public final void beforeClass() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    /**
     * Convertit une réponse d'API (résultat en JSON) en objet.
     * @param content contenu JSON.
     * @param valueType type de l'objet que l'on veut.
     * @return objet obtenu après tranformation.
     */
    @SneakyThrows(IOException.class)
    public final <T> T readValue(Response content, Class<T> valueType) {
        return jsonMapper.readValue(content.asString(), valueType);
    }
}
