package iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class LigneBudgetaire {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dénomination;
    private double montantinitial;
    private double montantconsomme;
    private double montantprovisionne;
    private double montantreel;
    private double statut;
    private boolean active;
    private String reference;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "idperiodebuggetaire")
    private PeriodeBudgetaire periodebudgetaire;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDénomination() {
        return dénomination;
    }

    public void setDénomination(String dénomination) {
        this.dénomination = dénomination;
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

    public double getStatut() {
        return statut;
    }

    public void setStatut(double statut) {
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
