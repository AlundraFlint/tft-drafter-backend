package elna.torla.tft.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ITEMS")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "RIOT_ID")
    private String riotId;

    private String nom;
    @Column(name = "NOM_ANGLAIS")
    private String nomAnglais;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "TRAIT_BONUS")
    private Trait traitBonus;

    @Column(name = "NOM_IMAGE")
    private String nomImage;

    public Item() {

    }

    public Item(int id, String riotId, String nom, String nomAnglais, Trait traitBonus, String nomImage) {
        this.id = id;
        this.riotId = riotId;
        this.nom = nom;
        this.nomAnglais = nomAnglais;
        this.traitBonus = traitBonus;
        this.nomImage = nomImage;
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

    public Trait getTraitBonus() {
        return traitBonus;
    }

    public void setTraitBonus(Trait traitBonus) {
        this.traitBonus = traitBonus;
    }

    public String getNomImage() {
        return nomImage;
    }

    public void setNomImage(String nomImage) {
        this.nomImage = nomImage;
    }
}
