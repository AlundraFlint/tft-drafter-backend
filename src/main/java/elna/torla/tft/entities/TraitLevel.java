package elna.torla.tft.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "TRAITS_LEVEL")
public class TraitLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "TRAIT_ID")
    private Trait traitId;
    private int valeur;
    private String color;

    public TraitLevel() {

    }

    public TraitLevel(int id, Trait traitId, int valeur, String color) {
        this.id = id;
        this.traitId = traitId;
        this.valeur = valeur;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Trait getTraitId() {
        return traitId;
    }

    public void setTraitId(Trait traitId) {
        this.traitId = traitId;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
