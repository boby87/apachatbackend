package iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Dto;

import javax.persistence.*;
import java.util.Date;

public class LigneBudgetaireDto {
    private String denomination;
    private double montantinitial;
    private double montantconsomme;
    private double montantprovisionne;
    private double montantreel;
    private String statut;
    private boolean active=true;
    private String reference;
    private Date date;


    public LigneBudgetaireDto() {
    }

    public LigneBudgetaireDto(String denomination,  String statut, String reference) {
        this.denomination = denomination;
        this.montantinitial = montantinitial;
        this.montantconsomme = montantconsomme;
        this.montantprovisionne = montantprovisionne;
        this.montantreel = montantreel;
        this.statut = statut;
        this.reference = reference;
        this.active=true;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public double getMontantinitial() {
        return montantinitial;
    }

    public void setMontantinitial(double montantinitial) {
        this.montantinitial = montantinitial;
    }

    public double getMontantconsomme() {
        return montantconsomme;
    }

    public void setMontantconsomme(double montantconsomme) {
        this.montantconsomme = montantconsomme;
    }

    public double getMontantprovisionne() {
        return montantprovisionne;
    }

    public void setMontantprovisionne(double montantprovisionne) {
        this.montantprovisionne = montantprovisionne;
    }

    public double getMontantreel() {
        return montantreel;
    }

    public void setMontantreel(double montantreel) {
        this.montantreel = montantreel;
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

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
