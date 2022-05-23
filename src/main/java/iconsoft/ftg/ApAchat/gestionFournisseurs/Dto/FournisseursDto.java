package iconsoft.ftg.ApAchat.gestionFournisseurs.Dto;

import javax.persistence.*;


public class FournisseursDto {
    private String denomination;
    private String directeur;
    private String directeurcommercial;
    private String localisation;
    private String reference;
    private boolean active=true;


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
}
