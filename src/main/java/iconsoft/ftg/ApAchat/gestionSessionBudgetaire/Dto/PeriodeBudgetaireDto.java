package iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Dto;

import iconsoft.ftg.ApAchat.gestionUtilisateur.Entities.DirecteurAchat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PeriodeBudgetaireDto {

    private Date date=new Date();
    private String anneebugetaire;
    private String statut;
    private String reference;
    private boolean active;
    private double montant;

    private DirecteurAchat directeurAchat;


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAnneebugetaire() {
        return anneebugetaire;
    }

    public void setAnneebugetaire(String anneebugetaire) {
        this.anneebugetaire = anneebugetaire;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
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

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }


    public DirecteurAchat getDirecteurAchat() {
        return directeurAchat;
    }

    public void setDirecteurAchat(DirecteurAchat directeurAchat) {
        this.directeurAchat = directeurAchat;
    }
}
