package coin.coin.springmvctinysample.api;

import coin.coin.springmvctinysample.AbstracBaseIT;
import coin.coin.springmvctinysample.model.Canard;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;

public class CanardWSIT extends AbstracBaseIT {

    private static final String CANARD_API = "/api/v1/canard"; // juste pour éviter de se répéter

    /**
     * Un premier exemple de test d'intégration : récup d'un canard.
     * Un test tout simple : on demande un canard, et on vérifie 1/ qu'on a le bon code HTTP, 2/ qu'on
     * a le bon canard.
     */
    @Test
    public void should_find_one_canard_by_name() {
        Response response = RestAssured
            .get(CANARD_API + "/Sariyah");

        response.then().statusCode(200);
        Canard canard = readValue(response, Canard.class);
        Assertions.assertThat(canard).isEqualTo(new Canard("Sariyah", 123));
    }

    /**
     * Un test un peu spécial qui utilise la fonctionnalité des Data Providers. Un data provider, c'est une
     * source de données (ici dataProviderCanards). Cela permet de lancer un test avec des parmètres.
     * Ici la data provider définit trois données, ce qui va laner le test trois fois : la première fois
     * avec "Sariyah" dans askedName et new Canard("Sariyah", 123) dans expectedCanard, etc.
     * Ca permet de tester plein de cas en écrivant un seul test, plutôt qu'écrire 36000 tests. Encore une fois,
     * c'est une fonctionnalité copiée de TestNG ;-). Utilisez là, c'est super utile.
     * Nota : tous les cas de tests sont exécutés, donc si deux cas sont en erreur alors JUnit vous dira lesquels. Il
     * ne s'arrête pas au premier échec.
     * Nota : en général on déplace les dapa providers dans des classes à part, pour les réutiliser dans plusieurs
     * tests. Comme ici, on les apelle avec leur nom.
     */
    @ParameterizedTest
    @MethodSource("dataProviderCanards")
    public void should_find_every_canard_by_name(String askedName, Canard expectedCanard) {
        Response response = RestAssured
            .get(CANARD_API + "/" + askedName);

        response.then().statusCode(200);
        Canard canard = readValue(response, Canard.class);
        Assertions.assertThat(canard)
            .isEqualTo(expectedCanard);
    }

    public Object[][] dataProviderCanards() {
        return new Object[][]{
            {"Sariyah", new Canard("Sariyah", 123)},
            {"Greatshot", new Canard("Greatshot", -2)},
            {"Dexter", new Canard("Dexter", 1_000_000)}
        };
    }

    /**
     * On vérifie que chercher un canard absent retourne une 404.
     */
    @Test
    public void should_not_find_unknown_canard_by_name() {
        Response response = RestAssured
            .get(CANARD_API + "/lePapeSaintMichel");

        response.then().statusCode(404);
    }

    /**
     * On vérifie que l'on récupère tous les canards.
     */
    @Test
    public void should_find_all_canards() {
        Response response = RestAssured
            .get(CANARD_API + "/all");

        response.then().statusCode(200);
        Collection<Canard> canards = Arrays.asList(readValue(response, Canard[].class));
        Assertions.assertThat(canards)
            .containsExactlyInAnyOrder(
                new Canard("Sariyah", 123),
                new Canard("Greatshot", -2),
                new Canard("Dexter", 1_000_000)
            );
    }
}
