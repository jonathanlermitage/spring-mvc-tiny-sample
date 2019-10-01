package coin.coin.springmvctinysample.ex;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Erreur 404, canard non trouvé. Mais il est passé où ce nigaud ?
 * Le "@ResponseStatus" permet à Spring, lorsqu'une API lance cette exception, de configurer le conde
 * de retour HTTP. Ca permet de lancer simplement des exceptions, plutôt que construire la réponse
 * à la main en passant un statut.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CanardNotFoundException extends Exception {
}
