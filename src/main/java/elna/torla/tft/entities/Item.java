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

    @Column(name = "URL_IMAGE")
    private String urlImage;

    public Item() {

    }

    public Item(int id, String riotId, String nom, String nomAnglais, Trait traitBonus, String urlImage) {
        this.id = id;
        this.riotId = riotId;
        this.nom = nom;
        this.nomAnglais = nomAnglais;
        this.traitBonus = traitBonus;
        this.urlImage = urlImage;
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

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
