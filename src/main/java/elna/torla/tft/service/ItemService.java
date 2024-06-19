package elna.torla.tft.service;

import elna.torla.tft.entities.Item;
import elna.torla.tft.entities.Trait;
import elna.torla.tft.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ItemService {
    private ItemRepository itemRepository;
    private TraitService traitService;
    public ItemService(ItemRepository itemRepository, TraitService traitService) {
        this.itemRepository = itemRepository;
        this.traitService = traitService;
    }

    public void createItem(Item item) {
        if(item.getNom() == null || Objects.equals(item.getNom(), "")) {
            //erreur à gérer
        } else if (item.getNomAnglais() == null || Objects.equals(item.getNomAnglais(), "")) {
            //erreur à gérer
        } else {
            Item itemDansLaBdd = this.itemRepository.findByNom(item.getNom());
            Item itemAngDansLaBdd = this.itemRepository.findByNomAnglais(item.getNomAnglais());
            if (itemDansLaBdd != null) {
                //erreur à gérer
            } else if (itemAngDansLaBdd != null) {
                //erreur à gérer
            } else {
                Trait trait = this.traitService.getTrait(item.getTraitBonus().getId());
                item.setTraitBonus(trait);
                this.itemRepository.save(item);
            }
        }
    }

    public List<Item> getItems(String nom, String nomAnglais) {
        if(nom != null)
        {
            if(nomAnglais != null){
                return this.itemRepository.findByNomContainingAndNomAnglaisContains(nom,nomAnglais);
            } else {
                return this.itemRepository.findByNomContaining(nom);
            }
        } else {
            if(nomAnglais != null){
                return this.itemRepository.findByNomAnglaisContaining(nomAnglais);
            } else {
                return this.itemRepository.findAll();
            }
        }
    }

    public Item getItem(int id) {
        Optional<Item> optionalItem = this.itemRepository.findById(id);
        return optionalItem.orElse(null);
    }

    public void updateItem(int id, Item item) {
        Item itemAUpdate = this.getItem(id);
        if (itemAUpdate.getId() == item.getId()) {
            if (item.getNom() == null || Objects.equals(item.getNom(), "")) {
                //erreur à gérer
            } else if (item.getNomAnglais() == null || Objects.equals(item.getNomAnglais(), "")) {
                //erreur à gérer
            } else {
                Item itemDansLaBdd = this.itemRepository.findByNom(item.getNom());
                Item itemAngDansLaBdd = this.itemRepository.findByNomAnglais(item.getNomAnglais());
                if (itemDansLaBdd != null && itemDansLaBdd.getId() != item.getId()) {
                    //erreur à gérer
                } else if (itemAngDansLaBdd != null && itemAngDansLaBdd.getId() != item.getId()) {
                    //erreur à gérer
                } else {
                    itemAUpdate.setNom(item.getNom());
                    itemAUpdate.setNomAnglais(item.getNomAnglais());
                    Trait trait = this.traitService.getTrait(item.getTraitBonus().getId());
                    itemAUpdate.setTraitBonus(trait);

                    this.itemRepository.save(itemAUpdate);
                }
            }
        } else {
            //erreur à gérer
        }
    }

    public void deleteItem(int id) {
        if(this.itemRepository.existsById(id)){
            this.itemRepository.deleteById(id);
        } else {
            //erreur à gérer
        }
    }
}
