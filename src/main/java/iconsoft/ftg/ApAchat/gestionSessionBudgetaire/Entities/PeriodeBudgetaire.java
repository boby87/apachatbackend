package iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Entities;

import iconsoft.ftg.ApAchat.gestionUtilisateur.Entities.DirecteurAchat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class PeriodeBudgetaire {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date=new Date();
    private String anneebugetaire;
    private String statut;
    private boolean active;
    private double montant;
    @OneToMany(mappedBy = "periodebudgetaire")
    private List<LigneBudgetaire> lignebudgetaires=new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "iddirecteurachat",nullable = false)
    private DirecteurAchat directeurAchat;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<LigneBudgetaire> getLigneBudgetaires() {
        return lignebudgetaires;
    }

    public void setLigneBudgetaires(List<LigneBudgetaire> ligneBudgetaires) {
        this.lignebudgetaires = ligneBudgetaires;
    }

    public DirecteurAchat getDirecteurAchat() {
        return directeurAchat;
    }

    public void setDirecteurAchat(DirecteurAchat directeurAchat) {
        this.directeurAchat = directeurAchat;
    }
}
