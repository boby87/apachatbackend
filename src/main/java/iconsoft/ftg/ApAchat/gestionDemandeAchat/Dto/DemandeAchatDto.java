package iconsoft.ftg.ApAchat.gestionDemandeAchat.Dto;

import iconsoft.ftg.ApAchat.gestionUtilisateur.Entities.AcheteurMetier;
import iconsoft.ftg.ApAchat.gestionUtilisateur.Entities.DirecteurAchat;

import java.util.Date;
import java.util.List;

public class DemandeAchatDto {
    private String statut;
    private Date date;
    private String reference;
    private boolean active=true;
    private double prixestimatif;
    private List<LigneDemandeAchatDto> lignedemandeAchats;
    private AcheteurMetier acheteurmetier;
    private DirecteurAchat directeurachat;

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

    public double getPrixestimatif() {
        return prixestimatif;
    }

    public void setPrixestimatif(double prixestimatif) {
        this.prixestimatif = prixestimatif;
    }

    public List<LigneDemandeAchatDto> getLignedemandeAchats() {
        return lignedemandeAchats;
    }

    public void setLignedemandeAchats(List<LigneDemandeAchatDto> lignedemandeAchats) {
        this.lignedemandeAchats = lignedemandeAchats;
    }

    public AcheteurMetier getAcheteurmetier() {
        return acheteurmetier;
    }

    public void setAcheteurmetier(AcheteurMetier acheteurmetier) {
        this.acheteurmetier = acheteurmetier;
    }

    public DirecteurAchat getDirecteurachat() {
        return directeurachat;
    }

    public void setDirecteurachat(DirecteurAchat directeurachat) {
        this.directeurachat = directeurachat;
    }
}
