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

    @Column(name = "NOM_IMAGE")
    private String nomImage;

    @Column(name = "NOM_ICONE")
    private String nomIcone;

    public Champion(){

    }

    public Champion(int id, String riotId, String nom, String nomAnglais, int cout, Trait trait1, Trait trait2, Trait trait3, String nomImage, String nomIcone) {
        this.id = id;
        this.riotId = riotId;
        this.nom = nom;
        this.nomAnglais = nomAnglais;
        this.cout = cout;
        this.trait1 = trait1;
        this.trait2 = trait2;
        this.trait3 = trait3;
        this.nomImage = nomImage;
        this.nomIcone = nomIcone;
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

    public String getNomImage() {
        return nomImage;
    }

    public void setNomImage(String nomImage) {
        this.nomImage = nomImage;
    }

    public String getNomIcone() {
        return nomIcone;
    }

    public void setNomIcone(String nomIcone) {
        this.nomIcone = nomIcone;
    }
}
