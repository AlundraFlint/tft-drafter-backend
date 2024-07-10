package elna.torla.tft.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "CHAMPIONS")
public class Champion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "RIOT_ID")
    private String riotId;

    private String nom;

    @Column(name = "NOM_ANGLAIS")
    private String nomAnglais;

    private int tier;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "TRAIT_ID_1")
    private Trait trait1;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "TRAIT_ID_2")
    private Trait trait2;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "TRAIT_ID_3")
    private Trait trait3;

    @Column(name = "URL_IMAGE")
    private String urlImage;

    @Column(name = "URL_ICON")
    private String urlIcon;

    public Champion(){

    }

    public Champion(int id, String riotId, String nom, String nomAnglais, int tier, Trait trait1, Trait trait2, Trait trait3, String urlImage, String urlIcon) {
        this.id = id;
        this.riotId = riotId;
        this.nom = nom;
        this.nomAnglais = nomAnglais;
        this.tier = tier;
        this.trait1 = trait1;
        this.trait2 = trait2;
        this.trait3 = trait3;
        this.urlImage = urlImage;
        this.urlIcon = urlIcon;
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

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public Trait getTrait1() {
        return trait1;
    }

    public void setTrait1(Trait trait1) {
        this.trait1 = trait1;
    }

    public Trait getTrait2() {
        return trait2;
    }

    public void setTrait2(Trait trait2) {
        this.trait2 = trait2;
    }

    public Trait getTrait3() {
        return trait3;
    }

    public void setTrait3(Trait trait3) {
        this.trait3 = trait3;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getUrlIcon() {
        return urlIcon;
    }

    public void setUrlIcon(String urlIcon) {
        this.urlIcon = urlIcon;
    }
}
