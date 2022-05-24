package iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Dao;

import iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Entities.LigneBudgetaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DaoLigneBudgetaire extends JpaRepository<LigneBudgetaire,Long> {
    List<LigneBudgetaire> findByActiveIsTrue();
    LigneBudgetaire findByReferenceAndActiveIsTrue(String reference);

}
