package elna.torla.tft.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="TRAITS")
public class Trait {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "RIOT_ID")
    private String riotId;

    private String nom;
    @Column(name = "NOM_ANGLAIS")
    private String nomAnglais;

    @Column(name = "URL_IMAGE")
    private String urlImage;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "traitId", cascade = CascadeType.ALL)
    private List<TraitLevel> traitLevels;

    public Trait() {
    }

    public Trait(int id, String riotId, String nom, String nomAnglais, String urlImage, List<TraitLevel> traitLevels) {
        this.id = id;
        this.riotId = riotId;
        this.nom = nom;
        this.nomAnglais = nomAnglais;
        this.urlImage = urlImage;
        this.traitLevels = traitLevels;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRiotId() {
        return riotId;
    }

    public void setRiotId(String riotId) {
        this.riotId = riotId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomAnglais() {
        return nomAnglais;
    }

    public void setNomAnglais(String nomAnglais) {
        this.nomAnglais = nomAnglais;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public List<TraitLevel> getTraitLevels() {
        return traitLevels;
    }

    public void setTraitLevels(List<TraitLevel> traitLevels) {
        this.traitLevels = traitLevels;
    }
}
