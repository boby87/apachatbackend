package iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Metier;

import iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Entities.LigneBudgetaire;

import java.util.List;

public interface MetierLigneBudgetaire {
    List<LigneBudgetaire> findByActiveIsTrue();
    LigneBudgetaire findByReferenceAndActiveIsTrue(String reference);

}
