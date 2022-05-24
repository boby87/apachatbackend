package iconsoft.ftg.ApAchat.gestionDemandeAchat.Entities;

import iconsoft.ftg.ApAchat.gestionFournisseurs.Entities.Fournisseurs;

import javax.persistence.*;
import java.util.Date;

@Entity
public class DevisFournisseur {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String statut;
    private Date date;
    private boolean active=true;
    @Column(columnDefinition = "text")
    private String imagedevis;
    @Column(nullable = false,unique = true)
    private String reference;
    private String referencedoc;
    @ManyToOne
    @JoinColumn(name = "iddemandeachat",unique = true)
    private DemandeAchat demandeachat;
    @ManyToOne
    @JoinColumn(name = "idfournisseur")
    private Fournisseurs fournisseurs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getImagedevis() {
        return imagedevis;
    }

    public void setImagedevis(String imagedevis) {
        this.imagedevis = imagedevis;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getReferencedoc() {
        return referencedoc;
    }

    public void setReferencedoc(String referencefornisseur) {
        this.referencedoc = referencefornisseur;
    }

    public DemandeAchat getDemandeachat() {
        return demandeachat;
    }

    public void setDemandeachat(DemandeAchat demandeachat) {
        this.demandeachat = demandeachat;
    }

    public Fournisseurs getFournisseurs() {
        return fournisseurs;
    }

    public void setFournisseurs(Fournisseurs fournisseurs) {
        this.fournisseurs = fournisseurs;
    }
}
