package elna.torla.tft.repository;

import elna.torla.tft.entities.Trait;
import elna.torla.tft.entities.TraitLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TraitLevelRepository extends JpaRepository<TraitLevel, Integer> {
    List<TraitLevel> findTraitLevelByTraitIdOrderByValeur(Trait traitId);
    TraitLevel findTraitLevelByTraitIdAndValeur(Trait traitId, int valeur);
}
