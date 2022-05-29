package iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Metier;

import iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Dto.PeriodeBudgetaireDto;
import iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Entities.PeriodeBudgetaire;

import java.util.List;

public interface MetierPeriodeBudgetaire {
    List<PeriodeBudgetaire> findall();
    List<PeriodeBudgetaireDto> findByActiveIsTrue();
    PeriodeBudgetaireDto findByReferenceAndActiveIsTrue(String reference);
    PeriodeBudgetaireDto findByAnneebugetaireAndActiveIsTrue(String anneebudgetaire);
    PeriodeBudgetaire findByAnneeAndActiveIsTrue(String anneebudgetaire);
    PeriodeBudgetaireDto saveperiodebudgetaire(PeriodeBudgetaireDto periodeBudgetaireDto);
    PeriodeBudgetaire localSaveperiodebudgetaire(PeriodeBudgetaire periodeBudgetaire);
    String changeStatusPBudget(PeriodeBudgetaireDto periodeBudgetaireDto);
}
