package iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Metier;

import iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Dto.LigneBudgetaireDto;
import iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Entities.LigneBudgetaire;
import iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Entities.PeriodeBudgetaire;

import java.util.List;

public interface MetierLigneBudgetaire {
    List<LigneBudgetaire> findall();
    List<LigneBudgetaire> findByActiveIsTrue();
    LigneBudgetaire findByReferenceAndActiveIsTrue(String reference);
    LigneBudgetaire saveligne(LigneBudgetaireDto ligneBudgetaireDto,PeriodeBudgetaire periodeBudgetaire);
    LigneBudgetaire localSaveligne(LigneBudgetaire ligneBudgetaire);
    boolean updatelignebudgetaire(LigneBudgetaireDto ligneBudgetaireDto);
    void saveAllLigneBudgetaire(String referenceperiode);
    boolean deleteLigneBudgetaire(String reference);
}
