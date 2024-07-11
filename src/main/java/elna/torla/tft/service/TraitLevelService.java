package elna.torla.tft.service;

import elna.torla.tft.entities.Trait;
import elna.torla.tft.entities.TraitLevel;
import elna.torla.tft.repository.TraitLevelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TraitLevelService {
    private TraitLevelRepository traitLevelRepository;
    private TraitService traitService;

    public TraitLevelService(TraitLevelRepository traitLevelRepository, TraitService traitService) {
        this.traitLevelRepository = traitLevelRepository;
        this.traitService = traitService;
    }

    public void createTraitLevel(int traitId, TraitLevel traitLevel) {
        Trait trait = traitService.getTrait(traitId);
        if (trait != null) {
            traitLevel.setTraitId(trait);
            if (traitLevel.getValeur() <= 0) {
                //erreur à gérer
            } else if (traitLevel.getColor() == null || Objects.equals(traitLevel.getColor(), "")) {
                //erreur à gérer
            } else if (traitLevel.getTraitId() == null) {
                //erreur à gérer
            } else {
                TraitLevel traitLevelDansLaBdd = this.traitLevelRepository.findTraitLevelByTraitIdAndValeur(traitLevel.getTraitId(), traitLevel.getValeur());
                if (traitLevelDansLaBdd != null) {
                    //erreur à gérer
                } else {
                    this.traitLevelRepository.save(traitLevel);
                }
            }
        } else {
            //erreur à gérer
        }
    }

    public void updateTraitLevel(int traitId, int id, TraitLevel traitLevel) {
        TraitLevel traitLevelAUpdate = this.getTraitLevel(traitId, id);
        if(traitLevelAUpdate!=null)
        {
            if (traitLevelAUpdate.getId() == id) {
                if (traitLevelAUpdate.getTraitId() == traitService.getTrait(traitId)) {
                    if(traitLevel.getValeur() <= 0) {
                        //erreur à gérer
                    } else if (traitLevel.getColor() == null || Objects.equals(traitLevel.getColor(), "")) {
                        //erreur à gérer
                    } else {
                        TraitLevel traitLevelDansLaBdd = this.traitLevelRepository.findTraitLevelByTraitIdAndValeur(traitLevel.getTraitId(),traitLevel.getValeur());
                        if (traitLevelDansLaBdd != null && traitLevelDansLaBdd.getId() != id) {
                            //erreur à gérer
                        } else {
                            traitLevelAUpdate.setValeur(traitLevel.getValeur());
                            traitLevelAUpdate.setColor(traitLevel.getColor());

                            this.traitLevelRepository.save(traitLevelAUpdate);
                        }
                    }
                } else {
                    //erreur à gérer
                }
            } else {
                //erreur à gérer
            }
        } else {
            //erreur à gérer
        }
    }

    public List<TraitLevel> getTraitLevels(int traitId) {
        if (traitId>0) {
            Trait trait = this.traitService.getTrait(traitId);
            return this.traitLevelRepository.findTraitLevelByTraitIdOrderByValeur(trait);
        } else {
            return null;
        }
    }

    public TraitLevel getTraitLevel(int traitId, int id) {
        Optional<TraitLevel> traitLevel = traitLevelRepository.findById(id);
        return traitLevel.orElse(null);
    }

    public void deleteTraitLevel(int traitId, int id) {
        if(this.traitLevelRepository.existsById(id)){
            this.traitLevelRepository.deleteById(id);
        } else {
            //erreur à gérer
        }
    }
}
