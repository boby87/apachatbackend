package iconsoft.ftg.ApAchat.gestionBonCommande.Entities;

import iconsoft.ftg.ApAchat.gestionDemandeAchat.Entities.DemandeAchat;
import iconsoft.ftg.ApAchat.gestionDemandeAchat.Entities.DevisFournisseur;

import javax.persistence.*;
import java.util.Date;

@Entity
public class BonCommande {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double montant;
    private String statut;
    private boolean active=true;
    private Date date=new Date();
    @Column(nullable = false,unique = true)
    private String reference;
    @OneToOne
    private DemandeAchat demandeachat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public DemandeAchat getDemandeachat() {
        return demandeachat;
    }

    public void setDemandeachat(DemandeAchat demandeachat) {
        this.demandeachat = demandeachat;
    }
}
