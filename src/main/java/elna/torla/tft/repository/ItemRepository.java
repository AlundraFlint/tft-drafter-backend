package elna.torla.tft.repository;

import elna.torla.tft.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    Item findByNom(String nom);
    Item findByNomAnglais(String nomAnglais);
    List<Item> findByNomContaining(String nom);
    List<Item> findByNomAnglaisContaining(String nomAnglais);
    List<Item> findByNomContainingAndNomAnglaisContains(String nom,String nomAnglais);

}
