package iconsoft.ftg.ApAchat.gestionDemandeAchat.Metier;

import iconsoft.ftg.ApAchat.gestionDemandeAchat.Dto.DevisFournisseurDto;
import iconsoft.ftg.ApAchat.gestionDemandeAchat.Entities.DevisFournisseur;

import java.util.List;

public interface MetierDevisfournisseur {
    DevisFournisseurDto findByReferencedocAndActiveIsTrue(String referencefournisseur);
    DevisFournisseurDto findByReferenceAndActiveIsTrue(String reference);
    List<DevisFournisseurDto> findByActiveIsTrue();
    List<DevisFournisseurDto> findByDemandeAchatAndActiveIsTrue(String reference);
    List<DevisFournisseurDto> uploadDevis(List<DevisFournisseurDto> devisFournisseurDto,String referencedemandeachat);
    DevisFournisseurDto chooseDevis(DevisFournisseurDto dto, String rDa);
    DevisFournisseurDto findDevisValideByReferenceDA(String reference);
}
