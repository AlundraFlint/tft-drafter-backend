package elna.torla.tft.repository;

import elna.torla.tft.entities.Item;
import elna.torla.tft.entities.Trait;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TraitRepository extends JpaRepository<Trait, Integer> {
    Trait findByNom(String nom);
    Trait findByNomAnglais(String nomAnglais);
    List<Trait> findByNomContaining(String nom);
    List<Trait> findByNomAnglaisContaining(String nomAnglais);
    List<Trait> findByNomContainingAndNomAnglaisContains(String nom, String nomAnglais);
    Trait findByRiotId(String riotId);
}
