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

    public void createTraitLevel(TraitLevel traitLevel) {
        if(traitLevel.getValeur() <= 0) {
            //erreur à gérer j'ai fais une modification ici
        } else if (traitLevel.getColor() == null || Objects.equals(traitLevel.getColor(), "")) {
            //erreur à gérer
        } else if (traitLevel.getTraitId() == null) {
            //erreur à gérer
        } else {
            TraitLevel traitLevelDansLaBdd = this.traitLevelRepository.findTraitLevelByTraitIdAndValeur(traitLevel.getTraitId(),traitLevel.getValeur());
            if (traitLevelDansLaBdd != null) {
                //erreur à gérer
            } else {
                Trait trait = this.traitService.getTrait(traitLevel.getTraitId().getId());
                if (trait != null) {
                    traitLevel.setTraitId(trait);
                    this.traitLevelRepository.save(traitLevel);
                } else {
                    //erreur à gérer
                }
            }
        }
    }

    public void updateTraitLevel(int id, TraitLevel traitLevel) {
        TraitLevel traitLevelAUpdate = this.getTraitLevel(id);
        if (traitLevelAUpdate.getId() == traitLevel.getId()) {
            if (traitLevelAUpdate.getTraitId() == traitLevel.getTraitId()) {
                if(traitLevel.getValeur() <= 0) {
                    //erreur à gérer
                } else if (traitLevel.getColor() == null || Objects.equals(traitLevel.getColor(), "")) {
                    //erreur à gérer
                } else {
                    TraitLevel traitLevelDansLaBdd = this.traitLevelRepository.findTraitLevelByTraitIdAndValeur(traitLevel.getTraitId(),traitLevel.getValeur());
                    if (traitLevelDansLaBdd != null) {
                        traitLevelAUpdate.setValeur(traitLevel.getValeur());
                        traitLevelAUpdate.setColor(traitLevel.getColor());

                        this.traitLevelRepository.save(traitLevelAUpdate);
                    } else {
                        //erreur à gérer
                    }
                }
            } else {
                //erreur à gérer
            }
        } else {
            //erreur à gérer
        }
    }

    public List<TraitLevel> getTraitLevels(Integer traitId) {
        if (traitId != null && traitId>0) {
            Trait traitAChercher = this.traitService.getTrait(traitId);
            return this.traitLevelRepository.findTraitLevelByTraitIdOrderByValeur(traitAChercher);
        } else {
            return this.traitLevelRepository.findAll();
        }
    }

    public TraitLevel getTraitLevel(int id) {
        Optional<TraitLevel> traitLevel = traitLevelRepository.findById(id);
        return traitLevel.orElse(null);
    }

    public void deleteTraitLevel(int id) {
        if(this.traitLevelRepository.existsById(id)){
            this.traitLevelRepository.deleteById(id);
        } else {
            //erreur à gérer
        }
    }
}
