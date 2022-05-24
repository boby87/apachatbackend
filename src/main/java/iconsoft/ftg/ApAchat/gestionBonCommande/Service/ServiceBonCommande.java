package iconsoft.ftg.ApAchat.gestionBonCommande.Service;

import iconsoft.ftg.ApAchat.gestionBonCommande.Dao.DaoBonCommande;
import iconsoft.ftg.ApAchat.gestionBonCommande.Dto.BonCommandeDto;
import iconsoft.ftg.ApAchat.gestionBonCommande.Metier.MetierBonCommande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServiceBonCommande implements MetierBonCommande {
    @Autowired
    DaoBonCommande daoBonCommande;


    @Override
    public BonCommandeDto findByReferenceAndActiveIsTrue(String reference) {
        return null;
    }

    @Override
    public List<BonCommandeDto> findByActiveIsTrue() {
        return null;
    }

    @Override
    public BonCommandeDto save(BonCommandeDto bonCommandeDto) {
        return null;
    }
}
