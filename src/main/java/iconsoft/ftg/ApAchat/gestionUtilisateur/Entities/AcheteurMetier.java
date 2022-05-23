package iconsoft.ftg.ApAchat.gestionUtilisateur.Entities;

import iconsoft.ftg.ApAchat.gestionDemandeAchat.Entities.DemandeAchat;
import iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Entities.PeriodeBudgetaire;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class AcheteurMetier extends Utilisateur{

    @OneToMany(mappedBy = "acheteurmetier")
    List<DemandeAchat> demandeachats;

    public List<DemandeAchat> getDemandeAchats() {
        return demandeachats;
    }

    public void setDemandeAchats(List<DemandeAchat> demandeAchats) {
        this.demandeachats = demandeAchats;
    }
}
