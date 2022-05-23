package iconsoft.ftg.ApAchat.gestionFournisseurs.Entities;

import iconsoft.ftg.ApAchat.gestionDemandeAchat.Entities.DevisFournisseur;

import javax.persistence.*;
import java.util.List;

@Entity
public class Fournisseurs {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String denomination;
    private String directeur;
    private String directeurcommercial;
    private String localisation;
    @Column(unique = true,nullable = false)
    private String reference;
    private boolean active=true;
    @OneToMany(mappedBy = "fournisseurs")
    private List<DevisFournisseur> devisfournisseurs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getDirecteur() {
        return directeur;
    }

    public void setDirecteur(String directeur) {
        this.directeur = directeur;
    }

    public String getDirecteurcommercial() {
        return directeurcommercial;
    }

    public void setDirecteurcommercial(String directeurcommercial) {
        this.directeurcommercial = directeurcommercial;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<DevisFournisseur> getDevisfournisseurs() {
        return devisfournisseurs;
    }

    public void setDevisfournisseurs(List<DevisFournisseur> devisfournisseurs) {
        this.devisfournisseurs = devisfournisseurs;
    }
}
