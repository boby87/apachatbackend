package iconsoft.ftg.ApAchat.gestionFournisseurs.Metier;

import iconsoft.ftg.ApAchat.gestionFournisseurs.Dto.FournisseursDto;
import iconsoft.ftg.ApAchat.gestionFournisseurs.Entities.Fournisseurs;

import java.util.List;

public interface MetierFournisseur {
    FournisseursDto findByReferenceAndActiveIsTrue(String reference);
    List<FournisseursDto> findByActiveIsTrue();
    FournisseursDto saveFournisseur(FournisseursDto fournisseursDto);
    Fournisseurs localfindByReferenceAndActiveIsTrue(String referencefournisseur);
}
