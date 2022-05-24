package iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Metier;

import iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Entities.PeriodeBudgetaire;

import java.util.List;

public interface MetierPeriodeBudgetaire {
    List<PeriodeBudgetaire> findByActiveIsTrue();
    PeriodeBudgetaire findByReferenceAndActiveIsTrue(String reference);
    PeriodeBudgetaire findByAnneebugetaireAndActiveIsTrue(String anneebudgetaire);

}
