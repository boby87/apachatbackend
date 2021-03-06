package iconsoft.ftg.ApAchat.gestionUtilisateur.Entities;

import iconsoft.ftg.ApAchat.gestionDemandeAchat.Entities.DemandeAchat;
import iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Entities.PeriodeBudgetaire;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class DirecteurAchat extends Utilisateur{

    @OneToMany(mappedBy = "directeurAchat")
    List<PeriodeBudgetaire> periodeBudgetaires;
    @OneToMany(mappedBy = "acheteurmetier")
    List<DemandeAchat> demandeachats;

    public List<DemandeAchat> getDemandeAchats() {
        return demandeachats;
    }

    public void setDemandeAchats(List<DemandeAchat> demandeAchats) {
        this.demandeachats = demandeAchats;
    }
    public List<PeriodeBudgetaire> getPeriodeBudgetaires() {
        return periodeBudgetaires;
    }

    public void setPeriodeBudgetaires(List<PeriodeBudgetaire> periodeBudgetaires) {
        this.periodeBudgetaires = periodeBudgetaires;
    }
}
