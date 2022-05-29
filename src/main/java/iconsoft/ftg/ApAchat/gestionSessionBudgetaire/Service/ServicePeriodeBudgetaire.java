package iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Service;

import iconsoft.ftg.ApAchat.gestionSessionBudgetaire.ConstateBudget;
import iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Dao.DaoPeriodeBudgetaire;
import iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Dto.PeriodeBudgetaireDto;
import iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Entities.PeriodeBudgetaire;
import iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Metier.MetierLigneBudgetaire;
import iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Metier.MetierPeriodeBudgetaire;
import iconsoft.ftg.ApAchat.gestionUtilisateur.RandomReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ServicePeriodeBudgetaire implements MetierPeriodeBudgetaire {
    @Autowired
    DaoPeriodeBudgetaire daoPeriodeBudgetaire;
    @Autowired
    MetierLigneBudgetaire metierLigneBudgetaire;


    @Override
    public List<PeriodeBudgetaire> findall() {
        return daoPeriodeBudgetaire.findAll();
    }

    @Override
    public List<PeriodeBudgetaireDto> findByActiveIsTrue() {
        List<PeriodeBudgetaireDto> periodeBudgetaireDtos=new ArrayList<>();
        daoPeriodeBudgetaire.findByActiveIsTrue().forEach(p->{
            PeriodeBudgetaireDto periodeBudgetaireDto=new PeriodeBudgetaireDto();
            BeanUtils.copyProperties(p,periodeBudgetaireDto);
            periodeBudgetaireDtos.add(periodeBudgetaireDto);
        });
        return periodeBudgetaireDtos;
    }

    @Override
    public PeriodeBudgetaireDto findByReferenceAndActiveIsTrue(String reference) {
            PeriodeBudgetaireDto periodeBudgetaireDto=new PeriodeBudgetaireDto();
            PeriodeBudgetaire periodeBudgetaire= daoPeriodeBudgetaire.findByReferenceAndActiveIsTrue(reference);
            BeanUtils.copyProperties(periodeBudgetaire,periodeBudgetaireDto);
        return periodeBudgetaireDto;
    }

    @Override
    public PeriodeBudgetaireDto findByAnneebugetaireAndActiveIsTrue(String anneebudgetaire) {
        PeriodeBudgetaireDto periodeBudgetaireDto=new PeriodeBudgetaireDto();
        PeriodeBudgetaire periodeBudgetaire= daoPeriodeBudgetaire.findByAnneebugetaireAndActiveIsTrue(anneebudgetaire);
        BeanUtils.copyProperties(periodeBudgetaire,periodeBudgetaireDto);
        return periodeBudgetaireDto;
    }

    @Override
    public PeriodeBudgetaire findByAnneeAndActiveIsTrue(String anneebudgetaire) {
        return daoPeriodeBudgetaire.findByAnneebugetaireAndActiveIsTrue(anneebudgetaire);
    }

    @Override
    public PeriodeBudgetaireDto saveperiodebudgetaire(PeriodeBudgetaireDto periodeBudgetaireDto) {
        PeriodeBudgetaire periodeBudgetaire=new PeriodeBudgetaire();
        BeanUtils.copyProperties(periodeBudgetaireDto,periodeBudgetaire);
        periodeBudgetaire=daoPeriodeBudgetaire.save(periodeBudgetaire);
        periodeBudgetaire.setReference(RandomReference.randomString(10));
        periodeBudgetaire.setStatut(ConstateBudget.NON_VALIDE);
        metierLigneBudgetaire.saveAllLigneBudgetaire(periodeBudgetaire);
        BeanUtils.copyProperties(periodeBudgetaire,periodeBudgetaireDto);
        return periodeBudgetaireDto;
    }

    @Override
    public PeriodeBudgetaire localSaveperiodebudgetaire(PeriodeBudgetaire periodeBudgetaire) {
        return daoPeriodeBudgetaire.save(periodeBudgetaire);
    }

    @Override
    public String changeStatusPBudget(PeriodeBudgetaireDto periodeBudgetaireDto) {
        if(!periodeBudgetaireDto.getStatut().equalsIgnoreCase(ConstateBudget.NON_VALIDE) ||
            !periodeBudgetaireDto.getStatut().equalsIgnoreCase(ConstateBudget.VALIDE) ||
            !periodeBudgetaireDto.getStatut().equalsIgnoreCase(ConstateBudget.CLOS))
            return "Le statut renseigne est inexistant.";

        PeriodeBudgetaire pbg = daoPeriodeBudgetaire.findByReferenceAndActiveIsTrue(periodeBudgetaireDto.getReference());
        if(pbg==null) return "Aucune periode budgetaire correspondant a cette reference : "+ periodeBudgetaireDto.getReference() + " trouve";

        if(pbg.getStatut().equalsIgnoreCase(ConstateBudget.NON_VALIDE) && periodeBudgetaireDto.getStatut().equalsIgnoreCase(ConstateBudget.VALIDE)){
            pbg.setStatut(periodeBudgetaireDto.getStatut());
            pbg.getLigneBudgetaires().forEach(l->{
                l.setStatut(pbg.getStatut());
                metierLigneBudgetaire.localSaveligne(l);
            });
            //Action a effectuer par la suite
            return pbg.getStatut();
        }  else if (pbg.getStatut().equalsIgnoreCase(ConstateBudget.VALIDE) && periodeBudgetaireDto.getStatut().equalsIgnoreCase(ConstateBudget.CLOS)){
            pbg.setStatut(periodeBudgetaireDto.getStatut());
            pbg.getLigneBudgetaires().forEach(l->{
                l.setStatut(pbg.getStatut());
                metierLigneBudgetaire.localSaveligne(l);
            });
            //Action a effectuer par la suite
            return pbg.getStatut();
        } else return "Impossible d'effectuer le changement.";
    }
}
