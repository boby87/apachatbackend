package iconsoft.ftg.ApAchat.gestionDemandeAchat.Entities;

import iconsoft.ftg.ApAchat.gestionUtilisateur.Entities.AcheteurMetier;
import iconsoft.ftg.ApAchat.gestionUtilisateur.Entities.DirecteurAchat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class DemandeAchat {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String statut;
    private Date date;
    @Column(unique = true,nullable = false)
    private String reference;
    private boolean active=true;
    private double prixestimatif;
    @OneToMany(mappedBy = "demandeachat")
    private List<LigneDemandeAchat> lignedemandeAchats=new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "iddemandeachat",nullable = false)
    private AcheteurMetier acheteurmetier;
    @ManyToOne
    @JoinColumn(name = "iddirecteurachat")
    private DirecteurAchat directeurachat;



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

    public List<LigneDemandeAchat> getLignedemandeAchats() {
        return lignedemandeAchats;
    }

    public void setLignedemandeAchats(List<LigneDemandeAchat> lignedemandeAchats) {
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
