package coin.coin.springmvctinysample.service.impl;

import coin.coin.springmvctinysample.model.Canard;
import coin.coin.springmvctinysample.service.CanardService;
import com.google.common.collect.ImmutableMap;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

// "@NoArgsConstructor" va générer un constructeur sans paramètres, ici le seul de la classe. Par défaut Spring
// ajoute "@Autowired" sur ce constructeur s'il est le seul constructeur de la classe. On pourrai très bien déclarer
// le constructur à la main. Si l'on créer plusieurs constructeurs, il faura ajouter une annotation "@Autowired",
// sans quoi Spring ne saura pas comment instancier le présent service.
@NoArgsConstructor
// "@Service" : c'est juste un bean ("@Bean" ou "@Component"), mais Spring définit des alias pour qu'on s'y retrouve.
// Ainsi on note les services "@Service", les repositories (accçs aux bases de données) "@Repository", et la
// configuratiob "@Configuration". Regardez leur doc ou source, cette notion est généralement rappelée.
@Service
// Helper de Lombok. Ca va créer un logger pour la classe. Ca évite de le déclarer à la main.
// Ca crée une variable "log."
@Slf4j
public class CanardServiceImpl implements CanardService {

    /**
     * Une liste en dur de canards. Idéalement on aurait une base de données avec plein de coincoins, mais
     * là on la code en dur.
     */
    private static final Map<String, Canard> allTheCoincoins = ImmutableMap.of(
        "Sariyah", new Canard("Sariyah", 123),
        "Greatshot", new Canard("Greatshot", -2),
        "Dexter", new Canard("Dexter", 1_000_000)
    );

    @Override
    public Optional<Canard> findByName(String name) {
        if (allTheCoincoins.containsKey(name)) {
            return Optional.of(allTheCoincoins.get(name));
        }
        return Optional.empty();
    }

    @Override
    public Collection<Canard> findAll() {
        log.info("houla le bourrin, tu veux tous les canards ! Tiens les v'la...");
        return allTheCoincoins.values();
    }
}
