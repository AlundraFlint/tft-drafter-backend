package elna.torla.tft.service;

import ch.qos.logback.core.net.server.Client;
import elna.torla.tft.entities.Trait;
import elna.torla.tft.repository.TraitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TraitService {
    private TraitRepository traitRepository;

    public TraitService(TraitRepository traitRepository) {
        this.traitRepository = traitRepository;
    }

    public void createTrait(Trait trait) {
        if(trait.getNom() == null || Objects.equals(trait.getNom(), "")) {
            //erreur à gérer
        } else if (trait.getNomAnglais() == null || Objects.equals(trait.getNomAnglais(), "")) {
            //erreur à gérer
        } else {
            Trait traitDansLaBdd = this.traitRepository.findByNom(trait.getNom());
            Trait traitAngDansLaBdd = this.traitRepository.findByNomAnglais(trait.getNomAnglais());
            if (traitDansLaBdd != null) {
                //erreur à gérer
            } else if (traitAngDansLaBdd != null) {
                //erreur à gérer
            } else {
                this.traitRepository.save(trait);
            }
        }
    }

    public List<Trait> getTraits(String nom, String nomAnglais) {
        if(nom != null)
        {
            if(nomAnglais != null){
                return this.traitRepository.findByNomContainingAndNomAnglaisContains(nom,nomAnglais);
            } else {
                return this.traitRepository.findByNomContaining(nom);
            }
        } else {
            if(nomAnglais != null){
                return this.traitRepository.findByNomAnglaisContaining(nomAnglais);
            } else {
                return this.traitRepository.findAll();
            }
        }
    }

    public Trait getTrait(int id) {
        Optional<Trait> optionalTrait = this.traitRepository.findById(id);
        return optionalTrait.orElse(null);
    }

    public Trait getTraitByRiotId(String riotId) {
        Optional<Trait> optionalTrait = Optional.ofNullable(this.traitRepository.findByRiotId(riotId));
        return optionalTrait.orElse(null);
    }

    public Trait getTraitByNomAnglais(String nomAnglais) {
        Optional<Trait> optionalTrait = Optional.ofNullable(this.traitRepository.findByNomAnglais(nomAnglais));
        return optionalTrait.orElse(null);
    }

    public void updateTrait(int id, Trait trait) {
        Trait traitAUpdate = this.getTrait(id);
        if (traitAUpdate.getId() == trait.getId()) {
            if (trait.getNom() == null || Objects.equals(trait.getNom(), "")) {
                int a = 1;
                //erreur à gérer
            } else if (trait.getNomAnglais() == null || Objects.equals(trait.getNomAnglais(), "")) {
                int b = 1;
                //erreur à gérer
            } else {
                Trait traitDansLaBdd = this.traitRepository.findByNom(trait.getNom());
                Trait traitAngDansLaBdd = this.traitRepository.findByNomAnglais(trait.getNomAnglais());
                if (traitDansLaBdd != null && traitDansLaBdd.getId() != trait.getId()) {
                    int c = 1;
                    //erreur à gérer
                } else if (traitAngDansLaBdd != null && traitAngDansLaBdd.getId() != trait.getId()) {
                    int d = 1;
                    //erreur à gérer
                } else {
                    traitAUpdate.setNom(trait.getNom());
                    traitAUpdate.setNomAnglais(trait.getNomAnglais());
                    this.traitRepository.save(traitAUpdate);
                }
            }
        } else {
            //erreur à gérer
        }
    }

    public void deleteTrait(int id) {
        if(this.traitRepository.existsById(id)){
            this.traitRepository.deleteById(id);
        } else {
            //erreur à gérer
        }
    }
}
