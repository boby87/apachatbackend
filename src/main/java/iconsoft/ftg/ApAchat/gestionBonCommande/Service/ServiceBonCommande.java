package iconsoft.ftg.ApAchat.gestionBonCommande.Service;

import iconsoft.ftg.ApAchat.gestionBonCommande.Dao.DaoBonCommande;
import iconsoft.ftg.ApAchat.gestionBonCommande.Dto.BonCommandeDto;
import iconsoft.ftg.ApAchat.gestionBonCommande.Entities.BonCmdStatut;
import iconsoft.ftg.ApAchat.gestionBonCommande.Entities.BonCommande;
import iconsoft.ftg.ApAchat.gestionBonCommande.Metier.MetierBonCommande;
import iconsoft.ftg.ApAchat.gestionUtilisateur.RandomReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

    @Override
    public BonCommande saveLocal(BonCommande bonCommande) {
        BonCommande bo = new BonCommande();
        bo.setDate(new Date());
        bo.setStatut(BonCmdStatut.INITIE.name());
        bo.setActive(Boolean.TRUE);
        bo.setDemandeachat(bonCommande.getDemandeachat());
        bo.setMontant(bonCommande.getDemandeachat().getPrixestimatif());
        bo.setReference(RandomReference.randomString(10));
        return daoBonCommande.save(bo);
    }
}
