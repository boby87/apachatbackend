package iconsoft.ftg.ApAchat.gestionBonCommande.Dto;

import iconsoft.ftg.ApAchat.gestionDemandeAchat.Entities.DemandeAchat;

import javax.persistence.*;
import java.util.Date;

public class BonCommandeDto {
    private double montant;
    private String statut;
    private boolean active=true;
    private Date date=new Date();
    private String reference;

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
}
