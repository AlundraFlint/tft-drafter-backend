package elna.torla.tft.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ITEMS")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    @Column(name = "NOM_ANGLAIS")
    private String nomAnglais;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "TRAIT_BONUS")
    private Trait traitBonus;

    public Item() {

    }

    public Item(int id, String nom, String nomAnglais, Trait traitBonus) {
        this.id = id;
        this.nom = nom;
        this.nomAnglais = nomAnglais;
        this.traitBonus = traitBonus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
