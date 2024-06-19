package elna.torla.tft.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "CHAMPIONS")
public class Champion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private int cout;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "TRAIT_ID_1")
    private Trait trait1;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "TRAIT_ID_2")
    private Trait trait2;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "TRAIT_ID_3")
    private Trait trait3;

    public Champion(){

    }

    public Champion(int id, String nom, int cout, Trait trait1, Trait trait2, Trait trait3) {
        this.id = id;
        this.nom = nom;
        this.cout = cout;
        this.trait1 = trait1;
        this.trait2 = trait2;
        this.trait3 = trait3;
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

    public int getCout() {
        return cout;
    }

    public void setCout(int cout) {
        this.cout = cout;
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
}
