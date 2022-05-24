package iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Service;

import iconsoft.ftg.ApAchat.gestionSessionBudgetaire.ConstateBudget;
import iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Dao.DaoLigneBudgetaire;
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
    public LigneBudgetaire saveligne(LigneBudgetaireDto ligneBudgetaireDto) {
        LigneBudgetaire ligneBudgetaire=new LigneBudgetaire();
        BeanUtils.copyProperties(ligneBudgetaireDto,ligneBudgetaire);
        ligneBudgetaire.setReference(RandomReference.randomString(10));
        return daoLigneBudgetaire.save(ligneBudgetaire);
    }

    @Override
    public boolean updatelignebudgetaire(LigneBudgetaireDto ligneBudgetaireDto) {
        LigneBudgetaire ligneBudgetaire=daoLigneBudgetaire.findByReferenceAndActiveIsTrue(ligneBudgetaireDto.getReference());
        BeanUtils.copyProperties(ligneBudgetaireDto,ligneBudgetaire);
        return true;
    }
    @Override
    public void saveAllLigneBudgetaire(PeriodeBudgetaire periodeBudgetaire){
        saveligne(new LigneBudgetaireDto("Pneus + pare brise", ConstateBudget.NON_VALIDE,RandomReference.randomString(12))).setPeriodebudgetaire(periodeBudgetaire);
        saveligne(new LigneBudgetaireDto("Assurances et prime", ConstateBudget.NON_VALIDE,RandomReference.randomString(12))).setPeriodebudgetaire(periodeBudgetaire);
        saveligne(new LigneBudgetaireDto("Eau/Electricité/internet/téléphone", ConstateBudget.NON_VALIDE,RandomReference.randomString(12))).setPeriodebudgetaire(periodeBudgetaire);
        saveligne(new LigneBudgetaireDto("Travaux et amenagement", ConstateBudget.NON_VALIDE,RandomReference.randomString(12))).setPeriodebudgetaire(periodeBudgetaire);
        saveligne(new LigneBudgetaireDto("Depot de garantie", ConstateBudget.NON_VALIDE,RandomReference.randomString(12))).setPeriodebudgetaire(periodeBudgetaire);
        saveligne(new LigneBudgetaireDto("Honoraires", ConstateBudget.NON_VALIDE,RandomReference.randomString(12))).setPeriodebudgetaire(periodeBudgetaire);
        saveligne(new LigneBudgetaireDto("Marketing", ConstateBudget.NON_VALIDE,RandomReference.randomString(12))).setPeriodebudgetaire(periodeBudgetaire);
        saveligne(new LigneBudgetaireDto("Autres", ConstateBudget.NON_VALIDE,RandomReference.randomString(12))).setPeriodebudgetaire(periodeBudgetaire);
        saveligne(new LigneBudgetaireDto("Loyer", ConstateBudget.NON_VALIDE,RandomReference.randomString(12))).setPeriodebudgetaire(periodeBudgetaire);

    }
}
