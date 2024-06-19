package elna.torla.tft.repository;

import elna.torla.tft.entities.Champion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampionRepository extends JpaRepository<Champion, Integer> {
    public Champion findByNom(String nom);
}
