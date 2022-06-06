package iconsoft.ftg.ApAchat.gestionBonCommande.Metier;

import iconsoft.ftg.ApAchat.gestionBonCommande.Dto.BonCommandeDto;
import iconsoft.ftg.ApAchat.gestionBonCommande.Entities.BonCommande;

import java.util.List;

public interface MetierBonCommande {
    BonCommandeDto findByReferenceAndActiveIsTrue(String reference);
    List<BonCommandeDto> findByActiveIsTrue();
    BonCommandeDto save(BonCommandeDto bonCommandeDto);
    BonCommande saveLocal(BonCommande bonCommande);
}
