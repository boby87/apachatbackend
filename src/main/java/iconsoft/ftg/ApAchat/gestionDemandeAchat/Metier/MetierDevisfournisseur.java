package iconsoft.ftg.ApAchat.gestionDemandeAchat.Metier;

import iconsoft.ftg.ApAchat.gestionDemandeAchat.Dto.DevisFournisseurDto;
import iconsoft.ftg.ApAchat.gestionDemandeAchat.Entities.DevisFournisseur;

import java.util.List;

public interface MetierDevisfournisseur {
    DevisFournisseurDto findByReferencedocAndActiveIsTrue(String referencefournisseur);
    DevisFournisseurDto findByReferenceAndActiveIsTrue(String reference);
    List<DevisFournisseurDto> findByActiveIsTrue();
    DevisFournisseurDto uploadDevis(DevisFournisseurDto devisFournisseurDto,String referencefournisseur,String referencedemandeachat);

}
