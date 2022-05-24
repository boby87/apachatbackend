package iconsoft.ftg.ApAchat.gestionDemandeAchat.Service;

import iconsoft.ftg.ApAchat.gestionDemandeAchat.Dto.DevisFournisseurDto;
import iconsoft.ftg.ApAchat.gestionDemandeAchat.Metier.MetierDevisfournisseur;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class ServiceDevisFournisseur implements MetierDevisfournisseur {
    @Override
    public DevisFournisseurDto findByReferencedocAndActiveIsTrue(String referencefournisseur) {
        return null;
    }

    @Override
    public DevisFournisseurDto findByReferenceAndActiveIsTrue(String reference) {
        return null;
    }

    @Override
    public List<DevisFournisseurDto> findByActiveIsTrue() {
        return null;
    }

    @Override
    public DevisFournisseurDto uploadDevis(DevisFournisseurDto devisFournisseurDto, String referencefournisseur, String referencedemandeachat) {
        return null;
    }
}
