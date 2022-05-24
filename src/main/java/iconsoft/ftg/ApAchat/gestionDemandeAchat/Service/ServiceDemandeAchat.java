package iconsoft.ftg.ApAchat.gestionDemandeAchat.Service;

import iconsoft.ftg.ApAchat.gestionDemandeAchat.Dto.DemandeAchatDto;
import iconsoft.ftg.ApAchat.gestionDemandeAchat.Metier.MetierDemandeAchat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ServiceDemandeAchat implements MetierDemandeAchat {
    @Override
    public DemandeAchatDto findByReferenceAndActiveIsTrue(String reference) {
        return null;
    }

    @Override
    public List<DemandeAchatDto> findByActiveIsTrue() {
        return null;
    }

    @Override
    public DemandeAchatDto save(DemandeAchatDto demandeAchatDto) {
        return null;
    }
}
