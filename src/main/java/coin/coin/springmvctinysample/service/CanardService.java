package coin.coin.springmvctinysample.service;

import coin.coin.springmvctinysample.model.Canard;

import java.util.Collection;
import java.util.Optional;

/**
 * Service de gestion des canards. C'est lui qui cherche les canards, mais encore mieux qu'un Jacques Pradel.
 */
public interface CanardService {

    /**
     * Recherche un canard par son nom.
     *
     * @param name nom du canard.
     * @return éventuellement le canard recherché.
     */
    Optional<Canard> findByName(String name);

    /**
     * Retourne tous les canards du monde de la terre.
     *
     * @return Tous les canards, d'un seul coup.
     */
    Collection<Canard> findAll();
}
