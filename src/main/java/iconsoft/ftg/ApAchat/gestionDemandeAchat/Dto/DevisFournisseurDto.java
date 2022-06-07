package iconsoft.ftg.ApAchat.gestionDemandeAchat.Dto;

import java.util.Date;

public class DevisFournisseurDto {
    private String statut;
    private Date date;
    private boolean active=true;
    private String imagedevis;
    private String reference;
    private String referencefornisseur;
    private DemandeAchatDto demandeachat;
    private String referencedemandeachat;

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

    public String getReferencefornisseur() {
        return referencefornisseur;
    }

    public void setReferencefornisseur(String referencefornisseur) {
        this.referencefornisseur = referencefornisseur;
    }

    public DemandeAchatDto getDemandeachat() {
        return demandeachat;
    }

    public void setDemandeachat(DemandeAchatDto demandeachat) {
        this.demandeachat = demandeachat;
    }

    public String getReferencedemandeachat() {
        return referencedemandeachat;
    }

    public void setReferencedemandeachat(String referencedemandeachat) {
        this.referencedemandeachat = referencedemandeachat;
    }
}
