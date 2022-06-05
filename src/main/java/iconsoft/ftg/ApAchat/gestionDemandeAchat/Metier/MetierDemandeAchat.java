package iconsoft.ftg.ApAchat.gestionDemandeAchat.Metier;

import iconsoft.ftg.ApAchat.gestionDemandeAchat.Dto.DemandeAchatDto;
import iconsoft.ftg.ApAchat.gestionDemandeAchat.Entities.DemandeAchat;

import java.util.List;

public interface MetierDemandeAchat {
    DemandeAchatDto findByReferenceAndActiveIsTrue(String reference);
    List<DemandeAchatDto> findByActiveIsTrue();
    DemandeAchatDto save(DemandeAchatDto demandeAchatDto);
    DemandeAchatDto saveArticles(DemandeAchatDto demandeAchatDto);
    DemandeAchatDto saveDemandeDtoWithLigneDemandeAchatDto(DemandeAchatDto demandeAchatDto);
}
