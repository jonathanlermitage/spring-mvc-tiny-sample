package coin.coin.springmvctinysample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Ouais bon heu, bah regardez un peu la doc de Lombok ;-). Ca fait plein de trucs sympas.
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Canard {

    private String name;
    private int borderlandsThreeLevel;
}
