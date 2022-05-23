package iconsoft.ftg.ApAchat.gestionFournisseurs.Service;

import iconsoft.ftg.ApAchat.gestionFournisseurs.Dto.FournisseursDto;
import iconsoft.ftg.ApAchat.gestionFournisseurs.Entities.Fournisseurs;
import iconsoft.ftg.ApAchat.gestionFournisseurs.Metier.MetierFournisseur;

import java.util.List;

public class ServiceFournisseurs implements MetierFournisseur {
    @Override
    public FournisseursDto findByReferenceAndActiveIsTrue(String reference) {
        return null;
    }

    @Override
    public List<FournisseursDto> findByActiveIsTrue() {
        return null;
    }

    @Override
    public FournisseursDto saveFournisseur(FournisseursDto fournisseursDto) {
        return null;
    }
}
