package elna.torla.tft.service;

import elna.torla.tft.entities.Champion;
import elna.torla.tft.entities.Item;
import elna.torla.tft.entities.Trait;
import elna.torla.tft.repository.ChampionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ChampionService {
    private ChampionRepository championRepository;
    private TraitService traitService;

    public ChampionService(ChampionRepository championRepository, TraitService traitService) {
        this.championRepository = championRepository;
        this.traitService = traitService;
    }

    public void createChampion(Champion champion) {
        if(champion.getNom() == null || champion.getNom().isEmpty()) {
            // erreur à gérer
        } else if (champion.getCout() < 1 || champion.getCout() > 5) {
            // erreur à gérer
        } else if (champion.getTrait1() == null) {
            //erreur à gérer
        } else if (champion.getTrait2() == null) {
            //erreur à gérer
        } else {
            Champion championDansLaBdd = this.championRepository.findByNom(champion.getNom());
            if(championDansLaBdd == null) {
                Trait trait1bis = this.traitService.getTrait(champion.getTrait1().getId());
                Trait trait2bis = this.traitService.getTrait(champion.getTrait2().getId());
                Trait trait3bis;
                if(champion.getTrait3()==null) {
                    trait3bis = null;
                } else {
                    trait3bis = this.traitService.getTrait(champion.getTrait3().getId());
                }
                if (trait1bis == null) {
                    //erreur à gérer
                } else if (trait2bis == null) {
                    //erreur à gérer
                } else if (trait1bis == trait2bis || trait2bis == trait3bis || trait1bis == trait3bis) {
                    //erreur à gérer
                } else {
                    champion.setTrait1(trait1bis);
                    champion.setTrait2(trait2bis);
                    champion.setTrait3(trait3bis);
                    this.championRepository.save(champion);
                }
            } else {
                // erreur à gérer
            }
        }
    }

    public List<Champion> getChampions(String nom, Integer cout, String trait) {
        return this.championRepository.findAll();
    }

    public Champion getChampion(int id) {
        Optional<Champion> champion = championRepository.findById(id);
        return champion.orElse(null);
    }

    public Champion getChampionByRiotId(String riotId) {
        Optional<Champion> optionalChampion = Optional.ofNullable(this.championRepository.findByRiotId(riotId));
        return optionalChampion.orElse(null);
    }

    public void updateChampion(int id, Champion champion) {
        Champion championAUpdate = this.getChampion(id);
        if (championAUpdate.getId() == champion.getId()) {
            if (champion.getNom() == null || Objects.equals(champion.getNom(), "")) {
                //erreur à gérer
            } else if (champion.getTrait1() == null) {
                //erreur à gérer
            } else if (champion.getTrait2() == null) {
                //erreur à gérer
            } else {
                Champion championDansLaBdd = this.championRepository.findByNom(champion.getNom());
                if (championDansLaBdd != null && championDansLaBdd.getId() != champion.getId()) {
                    //erreur à gérer
                } else {
                    Trait trait1bis = this.traitService.getTrait(champion.getTrait1().getId());
                    Trait trait2bis = this.traitService.getTrait(champion.getTrait2().getId());
                    Trait trait3bis = this.traitService.getTrait(champion.getTrait3().getId());
                    if (trait1bis == null) {
                        //erreur à gérer
                    } else if (trait2bis == null) {
                        //erreur à gérer
                    } else if (trait1bis == trait2bis || trait2bis == trait3bis || trait1bis == trait3bis) {
                        //erreur à gérer
                    } else {

                        championAUpdate.setNom(champion.getNom());
                        championAUpdate.setCout(champion.getCout());
                        championAUpdate.setTrait1(trait1bis);
                        championAUpdate.setTrait2(trait2bis);
                        championAUpdate.setTrait3(trait3bis);
                        this.championRepository.save(championAUpdate);
                    }
                }
            }
        } else {
            //erreur à gérer
        }
    }

    public void deleteChampion(int id) {
        Champion champion = getChampion(id);
        if (champion != null) {
            this.championRepository.delete(champion);
        } else {
            // erreur à gérer
        }
    }
}
