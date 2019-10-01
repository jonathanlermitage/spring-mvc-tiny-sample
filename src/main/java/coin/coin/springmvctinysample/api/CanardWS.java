package coin.coin.springmvctinysample.api;

import coin.coin.springmvctinysample.ex.CanardNotFoundException;
import coin.coin.springmvctinysample.model.Canard;
import coin.coin.springmvctinysample.service.CanardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/v1/canard")
@RequiredArgsConstructor
@Slf4j
public class CanardWS {

    private final CanardService canardService;

    /**
     * API qui cherche un canard.
     *
     * @param name nom du canard.
     * @return le canard, sinon une erreur 404.
     * @throws CanardNotFoundException si canard non trouvé.
     */
    @GetMapping("/{name}")
    public Canard findByName(@PathVariable(name = "name") String name)
        throws CanardNotFoundException {
        return canardService
            .findByName(name)
            .orElseThrow(CanardNotFoundException::new);
    }

    /**
     * API qui retourne tous les canards.
     *
     * @return les canards.
     */
    @GetMapping("/all")
    public Collection<Canard> findAll() {
        return canardService.findAll();
    }
}
