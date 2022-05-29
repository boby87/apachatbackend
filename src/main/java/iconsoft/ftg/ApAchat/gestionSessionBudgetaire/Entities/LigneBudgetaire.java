package iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import iconsoft.ftg.ApAchat.gestionUtilisateur.RandomReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class LigneBudgetaire implements Serializable {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String denomination;
    private double montantinitial;
    private double montantconsomme;
    private double montantprovisionne;
    private double montantreel;
    private String statut;
    private boolean active=true;
    private String reference;
    private Date date=new Date();
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idperiodebuggetaire")
    private PeriodeBudgetaire periodebudgetaire;

    public LigneBudgetaire() {
    }

    public LigneBudgetaire(String dénomination, double montantinitial, String statut, PeriodeBudgetaire periodebudgetaire) {
        this.denomination = dénomination;
        this.montantinitial = montantinitial;
        this.montantconsomme = 0.0;
        this.montantprovisionne = 0.0;
        this.montantreel = 0.0;
        this.statut = statut;
        this.active = Boolean.TRUE;
        this.reference = RandomReference.randomString(10);
        this.date = new Date();
        this.periodebudgetaire = periodebudgetaire;
    }

    public PeriodeBudgetaire getPeriodebudgetaire() {
        return periodebudgetaire;
    }

    public void setPeriodebudgetaire(PeriodeBudgetaire periodebudgetaire) {
        this.periodebudgetaire = periodebudgetaire;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String dénomination) {
        this.denomination = dénomination;
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

    public PeriodeBudgetaire getPeriodeBudgetaire() {
        return periodebudgetaire;
    }

    public void setPeriodeBudgetaire(PeriodeBudgetaire periodeBudgetaire) {
        this.periodebudgetaire = periodeBudgetaire;
    }
}
