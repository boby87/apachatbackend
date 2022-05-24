package iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Dao;

import iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Entities.PeriodeBudgetaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DaoPeriodeBudgetaire extends JpaRepository<PeriodeBudgetaire,Long> {
    List<PeriodeBudgetaire> findByActiveIsTrue();
    PeriodeBudgetaire findByReferenceAndActiveIsTrue(String reference);
    PeriodeBudgetaire findByAnneebugetaireAndActiveIsTrue(String anneebudgetaire);
}
