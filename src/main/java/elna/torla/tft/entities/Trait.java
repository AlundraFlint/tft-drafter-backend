package elna.torla.tft.entities;

import jakarta.persistence.*;

@Entity
@Table(name="TRAITS")
public class Trait {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    @Column(name = "NOM_ANGLAIS")
    private String nomAnglais;

    public Trait() {
    }

    public Trait(int id, String nom, String nomAnglais) {
        this.id = id;
        this.nom = nom;
        this.nomAnglais = nomAnglais;
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
}
