package iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Service;

import iconsoft.ftg.ApAchat.gestionSessionBudgetaire.ConstateBudget;
import iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Dao.DaoLigneBudgetaire;
import iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Dao.DaoPeriodeBudgetaire;
import iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Dto.LigneBudgetaireDto;
import iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Dto.PeriodeBudgetaireDto;
import iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Entities.LigneBudgetaire;
import iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Entities.PeriodeBudgetaire;
import iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Metier.MetierLigneBudgetaire;
import iconsoft.ftg.ApAchat.gestionUtilisateur.RandomReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServiceLigneBudgetaire implements MetierLigneBudgetaire {
    @Autowired
    DaoLigneBudgetaire daoLigneBudgetaire;
    @Autowired
    DaoPeriodeBudgetaire daoPeriodeBudgetaire;

    @Override
    public List<LigneBudgetaire> findall() {
        return daoLigneBudgetaire.findAll();
    }

    @Override
    public List<LigneBudgetaire> findByActiveIsTrue() {
        return daoLigneBudgetaire.findByActiveIsTrue();
    }

    @Override
    public LigneBudgetaire findByReferenceAndActiveIsTrue(String reference) {
        return daoLigneBudgetaire.findByReferenceAndActiveIsTrue(reference);
    }

    @Override
    public LigneBudgetaire saveligne(LigneBudgetaireDto ligneBudgetaireDto,PeriodeBudgetaire periodeBudgetaire) {
        LigneBudgetaire ligneBudgetaire=new LigneBudgetaire();
        BeanUtils.copyProperties(ligneBudgetaireDto,ligneBudgetaire);
        ligneBudgetaire.setReference(RandomReference.randomString(10));
        ligneBudgetaire=daoLigneBudgetaire.save(ligneBudgetaire);
        ligneBudgetaire.setPeriodebudgetaire(periodeBudgetaire);
        return ligneBudgetaire;
    }

    @Override
    public LigneBudgetaire localSaveligne(LigneBudgetaire ligneBudgetaire) {
        return daoLigneBudgetaire.save(ligneBudgetaire);
    }

    @Override
    public boolean updatelignebudgetaire(LigneBudgetaireDto ligneBudgetaireDto) {
        LigneBudgetaire ligneBudgetaire=daoLigneBudgetaire.findByReferenceAndActiveIsTrue(ligneBudgetaireDto.getReference());
        BeanUtils.copyProperties(ligneBudgetaireDto,ligneBudgetaire);
        if (ligneBudgetaire.getMontantinitial()>0){
            ligneBudgetaire.setStatut(ConstateBudget.VALIDE);
        }
        return true;
    }

    @Override
    public void saveAllLigneBudgetaire(String referenceperiode){
        PeriodeBudgetaire periodeBudgetaire=daoPeriodeBudgetaire.findByReferenceAndActiveIsTrue(referenceperiode);
        saveligne(new LigneBudgetaireDto("Pneus + pare brise", ConstateBudget.NON_VALIDE,RandomReference.randomString(12)),periodeBudgetaire);
        saveligne(new LigneBudgetaireDto("Assurances et prime", ConstateBudget.NON_VALIDE,RandomReference.randomString(12)),periodeBudgetaire);
        saveligne(new LigneBudgetaireDto("Eau/Electricit??/internet/t??l??phone", ConstateBudget.NON_VALIDE,RandomReference.randomString(12)),periodeBudgetaire);
        saveligne(new LigneBudgetaireDto("Travaux et amenagement", ConstateBudget.NON_VALIDE,RandomReference.randomString(12)),periodeBudgetaire);
        saveligne(new LigneBudgetaireDto("Depot de garantie", ConstateBudget.NON_VALIDE,RandomReference.randomString(12)),periodeBudgetaire);
        saveligne(new LigneBudgetaireDto("Honoraires", ConstateBudget.NON_VALIDE,RandomReference.randomString(12)),periodeBudgetaire);
        saveligne(new LigneBudgetaireDto("Marketing", ConstateBudget.NON_VALIDE,RandomReference.randomString(12)),periodeBudgetaire);
        saveligne(new LigneBudgetaireDto("Autres", ConstateBudget.NON_VALIDE,RandomReference.randomString(12)),periodeBudgetaire);
        saveligne(new LigneBudgetaireDto("Loyer", ConstateBudget.NON_VALIDE,RandomReference.randomString(12)),periodeBudgetaire);
    }

    @Override
    public boolean deleteLigneBudgetaire(String reference) {
        LigneBudgetaire lb = this.findByReferenceAndActiveIsTrue(reference);
        if(lb==null) return false;

        lb.getPeriodeBudgetaire().getLigneBudgetaires().remove(lb);
        lb.setPeriodebudgetaire(null);
        daoLigneBudgetaire.delete(lb);
        return true;
    }
}
