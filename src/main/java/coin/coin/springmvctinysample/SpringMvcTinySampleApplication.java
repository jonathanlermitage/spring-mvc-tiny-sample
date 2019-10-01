package coin.coin.springmvctinysample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principale. On prend l'habitude de la placer un cran "au-dessus" du reste du projet car
 * grace à "@SpringBootApplication", on va automatiquement scanner tout ce ui se trouve à ce niveau et
 * plus bas pour trover des beans à charger. Ca évite de déclarer des packages de base à scanner.
 */
@SpringBootApplication
public class SpringMvcTinySampleApplication {

    /**
     * Le point d'entrée du projet, avec la bien connue méthode "main".
     * Généralement on ne fait rien de plus dans cette méthode. Eventuellement un peu de config, mais on
     * peut très souvent faire cela via l'application.properties/.yaml. On peut aussi gérer des paramètres
     * de ligne de commande, mais on peut aussi les gérer autrement. Bref, ne vous prennez pas la tête.
     * <p>
     * Ha j'allais oublier : sous IntelliJ vous verrez une flèche verte à gauche de la méthode. Cliquez dessus
     * pour lancer l'appli (par défaut elle écoute sur le port 8080, donc si vous avez déjà un truc qui écoute
     * sur ce port, l'appli va planter). Elle devrait se lancer en quelques secondes.
     * Elle est pas belle la vie ?
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringMvcTinySampleApplication.class, args);
    }
}
